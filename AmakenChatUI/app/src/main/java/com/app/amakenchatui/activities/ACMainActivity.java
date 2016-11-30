package com.app.amakenchatui.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.amakenchatui.ACAppConstants;
import com.app.amakenchatui.ACApplication;
import com.app.amakenchatui.R;
import com.app.amakenchatui.adapters.ACChatAdapter;
import com.app.amakenchatui.datamodel.ACChatResponse;
import com.app.amakenchatui.requestComponents.ACChatRequestBody;
import com.app.amakenchatui.requestComponents.ACChatRequestData;
import com.app.amakenchatui.requestComponents.ACChatRequestEnvelope;
import com.app.amakenchatui.response.ACChatResponseEnvelope;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jai on 20/02/16.
 */
public class ACMainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private RecyclerView rvChats;
    private ProgressDialog progressDialog;
    private ImageView ivNetworkIcon;
    private IntentFilter networkIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private BroadcastReceiver networkBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                callApi();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_activity_main);
        rvChats = (RecyclerView) findViewById(R.id.rv_chats);
        ivNetworkIcon = (ImageView) findViewById(R.id.img_network_icon);

        progressDialog = new ProgressDialog(ACMainActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
        setRecyclerViewAdapter();

        //Method to check the network connection.
        if (checkNetworkConnection(ACMainActivity.this)) {
            callApi();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkBroadcastReceiver, networkIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkBroadcastReceiver);
    }

    /**
     * Method to callAPI.
     */
    private void callApi() {
        //Creation of the envelope and the message.
        ACChatRequestEnvelope envelope = new ACChatRequestEnvelope();

        ACChatRequestBody body = new ACChatRequestBody();

        ACChatRequestData data = new ACChatRequestData();

        data.setLastRecordId("0");
        data.setTotalRecord("20");

        body.setACChatRequestData(data);
        envelope.setBody(body);

        Call<ACChatResponseEnvelope> call = ACApplication.getRestClient().getChatApiInstance().requestChat(envelope);

        call.enqueue(new retrofit2.Callback<ACChatResponseEnvelope>() {
            @Override
            public void onResponse(Call<ACChatResponseEnvelope> call, final Response<ACChatResponseEnvelope> response) {
                String responseString = response.body().getBody().getData().getData();
                Log.d(TAG, "Success: response: " + response.message() + ", response body: " + responseString);
                byte[] data = Base64.decode(responseString, Base64.DEFAULT);
                try {
                    String responseJson = new String(data, "UTF-8");
                    try {
                        Gson gson = new Gson();
                        //Now we only parse the objects in 0, 4, 9
                        JSONArray responseArray = new JSONArray(responseJson);
                        if (responseArray != null) {
                            for (int i = 0; i < responseArray.length(); i++) {
                                if (i == 0 || i == 4 || i == 9) {
                                    Log.d(TAG, "ResponseArray string: " + responseArray.get(i).toString());
                                    ACChatResponse chatResponse = gson.fromJson(responseArray.get(i).toString(), ACChatResponse.class);
                                    ACApplication.getInstance().getDataBaseHelper().insertChat(chatResponse);
                                }
                            }
                        }
                        //Update the UI once receive the value
                        setRecyclerViewAdapter();
                    } catch (JsonSyntaxException e) {
                        Log.e(TAG, "Caught JsonSyntaxException: " + e.getMessage());
                    } catch (JSONException e) {
                        Log.e(TAG, "Caught JsonException: " + e.getMessage());
                    }
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "Caught UnsupportedEncodingException: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ACChatResponseEnvelope> call, Throwable t) {
                hideProgressDialog();
                Log.d(TAG, "Failure block called");
            }
        });
    }

    /**
     * Method to set the view
     */
    private void setRecyclerViewAdapter() {
        try {
            ArrayList<ACChatResponse> chatResponses = new ArrayList<>();
            ArrayList<Integer> cardIds = ACApplication.getInstance().getDataBaseHelper().getCardIds();
            Log.d(TAG, "setRecyclerViewAdapter: called: cardIds size: " + cardIds.size());

            if (cardIds != null) {
                for (Integer cardId : cardIds) {
                    if (cardId == ACAppConstants.WELCOME_CARD_TYPE_ID) {
                        chatResponses.add(ACApplication.getInstance().getDataBaseHelper().getWelcomeCard(cardId));
                    } else if (cardId == ACAppConstants.USER_CARD_TYPE_ID) {
                        chatResponses.add(ACApplication.getInstance().getDataBaseHelper().getUserCard(cardId));
                    } else if (cardId == ACAppConstants.PROMOTION_CARD_TYPE_ID) {
                        chatResponses.add(ACApplication.getInstance().getDataBaseHelper().getPromotionCard(cardId));

                    }
                }
            }
            hideProgressDialog();
            Log.d(TAG, "setRecyclerViewAdapter: called: chatresponses size: " + chatResponses.size());

            if (chatResponses.size() == 0) {
                ivNetworkIcon.setVisibility(View.VISIBLE);
            } else {
                ivNetworkIcon.setVisibility(View.INVISIBLE);
            }

            ACChatAdapter acChatAdapter = new ACChatAdapter(chatResponses, ACMainActivity.this);
            rvChats.setAdapter(acChatAdapter);
            rvChats.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e) {
            Log.e(TAG, "Caught Exception: " + e.getMessage());
        }
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Method to check the network connection
     *
     * @return Return the connection status
     */
    private Boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo wifiInfo, mobileInfo;
        try {
            if (context != null) {
                connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (wifiInfo.getState() == NetworkInfo.State.CONNECTED || mobileInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            } else {
                Log.d(TAG, "Context is null");
            }
        } catch (Exception exception) {
            Log.e(TAG, "CheckConnectivity Exception: " + exception.getMessage(), exception);
        }

        return false;
    }
}
