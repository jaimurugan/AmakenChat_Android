package com.app.amakenchatui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.amakenchatui.ACAppConstants;
import com.app.amakenchatui.ACApplication;
import com.app.amakenchatui.R;
import com.app.amakenchatui.datamodel.ACChatResponse;
import com.app.amakenchatui.datamodel.PromotionCard;
import com.app.amakenchatui.datamodel.UserCard;
import com.app.amakenchatui.datamodel.WelcomeCard;
import com.app.amakenchatui.helper.ACDate;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by jai on 20/02/16.
 */
public class ACChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ACChatAdapter";
    //Types
    private final int WELCOME = 0, PROMOTION = 1, USER = 2;
    //List of data
    private ArrayList<ACChatResponse> chats;
    private Context context;
    private int index = 0;
    private int promotionIndex = 0;

    public ACChatAdapter(ArrayList<ACChatResponse> chats, Context context) {
        this.chats = chats;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case WELCOME:
                View welcomeView = inflater.inflate(R.layout.ac_chat_list_welcome, parent, false);
                viewHolder = new WelComeViewHolder(welcomeView);
                break;
            case PROMOTION:
                View promotionView = inflater.inflate(R.layout.ac_chat_list_promotion, parent, false);
                viewHolder = new PromotionViewHolder(promotionView);
                break;
            case USER:
                View userView = inflater.inflate(R.layout.ac_chat_bubble, parent, false);
                viewHolder = new UserViewHolder(userView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case WELCOME:
                WelComeViewHolder welComeViewHolder = (WelComeViewHolder) viewHolder;
                configureWelComeViewHolder(welComeViewHolder, position);
                break;
            case PROMOTION:
                PromotionViewHolder promotionViewHolder = (PromotionViewHolder) viewHolder;
                configurePromotionViewHolder(promotionViewHolder, position);
                break;
            case USER:
                UserViewHolder userViewHolder = (UserViewHolder) viewHolder;
                configureUserViewHolder(userViewHolder, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return chats != null ? chats.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        switch (chats.get(position).getCardTypeId()) {
            case ACAppConstants.WELCOME_CARD_TYPE_ID: //Represents a welcome card
                return WELCOME;
            case ACAppConstants.USER_CARD_TYPE_ID: //Represents a user card
                return USER;
            case ACAppConstants.PROMOTION_CARD_TYPE_ID://Represents a promotion card
                return PROMOTION;
        }
        return -1;
    }

    private void configureWelComeViewHolder(final WelComeViewHolder welComeViewHolder, int position) {

        try {
            final WelcomeCard welcomeCard = chats.get(position).getWelcomeCard();
            ImageLoader.getInstance().displayImage(welcomeCard.getCardLogo(), welComeViewHolder.ivLogo);

            welComeViewHolder.tvChannelName.setText(welcomeCard.getChannelName());
            welComeViewHolder.tvChannelName.setTextColor(Color.parseColor(welcomeCard.getChannelNameColor()));
            setViewAlignment(welComeViewHolder.tvChannelName, welcomeCard.getChannelNameAlign());

            welComeViewHolder.tvChannelNo.setText("Channel No. : " + welcomeCard.getChannelNo());
            welComeViewHolder.tvChannelNo.setTextColor(Color.parseColor(welcomeCard.getChannelNoColor()));
            setViewAlignment(welComeViewHolder.tvChannelNo, welcomeCard.getChannelNoAlign());

            welComeViewHolder.tvDate.setText(ACDate.getFormattedDateString(welcomeCard.getAddedDate(), ACDate.WELCOME_USER_CARD_FORMAT_INDEX));
            welComeViewHolder.tvDate.setTextColor(Color.parseColor(welcomeCard.getAddedDateColor()));

            welComeViewHolder.tvWelcomeHeader.setText(welcomeCard.getHeadText());
            welComeViewHolder.tvWelcomeHeader.setTextColor(Color.parseColor(welcomeCard.getHeadTextColor()));
            setViewAlignment(welComeViewHolder.tvWelcomeHeader, welcomeCard.getHeadTextAlign());
            setViewStyle(welComeViewHolder.tvWelcomeHeader, welcomeCard.getHeadTextBold());


            welComeViewHolder.tvCost.setText(welcomeCard.getBodyText().replace("\\n ", "\n"));
            welComeViewHolder.tvCost.setTextColor(Color.parseColor(welcomeCard.getBodyTextColor()));
            setViewAlignment(welComeViewHolder.tvCost, welcomeCard.getBodyTextAlign());
            setViewStyle(welComeViewHolder.tvCost, welcomeCard.getBodyTextBold());

            //For image switcher
            final ArrayList<String> imageURLone = new ArrayList<>();

            for (WelcomeCard.Content content : welcomeCard.getContents()) {
                imageURLone.add(content.getFileUrl());
            }

            ImageLoader.getInstance().displayImage(imageURLone.get(0), welComeViewHolder.isWelcome, ACApplication.getUserDisplayOptions());

            welComeViewHolder.ivWelcomeLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index != 0) {
                        index--;
                        ImageLoader.getInstance().displayImage(imageURLone.get(index), welComeViewHolder.isWelcome, ACApplication.getUserDisplayOptions());
                    }
                }
            });

            welComeViewHolder.ivWelcomeRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index != imageURLone.size() - 1) {
                        index++;
                        ImageLoader.getInstance().displayImage(imageURLone.get(index), welComeViewHolder.isWelcome, ACApplication.getUserDisplayOptions());
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Caught Exception: " + e.getMessage());
        }

    }

    private void configurePromotionViewHolder(final PromotionViewHolder promotionViewHolder, int position) {
        try {
            PromotionCard promotionCard = chats.get(position).getPromotionCard();
            ImageLoader.getInstance().displayImage(promotionCard.getCardLogo(), promotionViewHolder.ivLogo);
            promotionViewHolder.tvChannelName.setText(promotionCard.getChannelName());
            promotionViewHolder.tvChannelName.setTextColor(Color.parseColor(promotionCard.getChannelNameColor()));
            setViewAlignment(promotionViewHolder.tvChannelName, promotionCard.getChannelNameAlign());

            promotionViewHolder.tvChannelNo.setText("Channel No.:" + String.valueOf(promotionCard.getChannelNo()));
            promotionViewHolder.tvChannelNo.setTextColor(Color.parseColor(promotionCard.getChannelNoColor()));
            setViewAlignment(promotionViewHolder.tvChannelNo, promotionCard.getChannelNoAlign());
            promotionViewHolder.tvDate.setText(ACDate.getFormattedDateString(promotionCard.getAddedDate(), ACDate.PROMOTIONS_FORMAT_INDEX));
            promotionViewHolder.tvDate.setTextColor(Color.parseColor(promotionCard.getAddedDateColor()));

            promotionViewHolder.tvPromotion.setText(promotionCard.getBodyText());
            promotionViewHolder.tvPromotion.setTextColor(Color.parseColor(promotionCard.getBodyTextColor()));
            setViewAlignment(promotionViewHolder.tvPromotion, promotionCard.getBodyTextAlign());
            setViewStyle(promotionViewHolder.tvPromotion, promotionCard.getBodyTextBold());

            promotionViewHolder.tvCommentsValue.setText(promotionCard.getCommentsCount());
            promotionViewHolder.tvLikesValue.setText(promotionCard.getLikeCount());
            promotionViewHolder.tvViewValue.setText(promotionCard.getViewersCount());

            promotionViewHolder.btnStartChat.setText(promotionCard.getActionText());
            Log.d(TAG, "configurePromotionViewHolder: promotionCard.getActionColor(): " + promotionCard.getActionColor());
            promotionViewHolder.btnStartChat.setTextColor(Color.parseColor("#" + promotionCard.getActionColor()));

            promotionViewHolder.btnViewMore.setText(promotionCard.getMoreText());
            promotionViewHolder.btnViewMore.setTextColor(Color.parseColor("#" + promotionCard.getMoreColor()));

            //For image view switcher
            final ArrayList<String> imageURLtwo = new ArrayList<>();

            for (PromotionCard.Content content : promotionCard.getContents()) {
                imageURLtwo.add(content.getFileUrl());
            }
            ImageLoader.getInstance().displayImage(imageURLtwo.get(0), promotionViewHolder.isPromotion, ACApplication.getUserDisplayOptions());

            promotionViewHolder.ivPromotionLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (promotionIndex != 0) {
                        promotionIndex--;
                        ImageLoader.getInstance().displayImage(imageURLtwo.get(promotionIndex), promotionViewHolder.isPromotion, ACApplication.getUserDisplayOptions());
                    }
                }
            });

            promotionViewHolder.ivPromotionRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (promotionIndex != imageURLtwo.size() - 1) {
                        promotionIndex++;
                        ImageLoader.getInstance().displayImage(imageURLtwo.get(promotionIndex), promotionViewHolder.isPromotion, ACApplication.getUserDisplayOptions());
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Caught Exception: " + e.getMessage());
        }
    }

    private void configureUserViewHolder(UserViewHolder userViewHolder, int position) {
        try {
            UserCard userCard = chats.get(position).getUserCard();
            userViewHolder.tvMessage.setText(userCard.getMessageTextBody());
            String messageTextColor = userCard.getMessageTextColor();
            if (!TextUtils.isEmpty(messageTextColor)) {
                userViewHolder.tvMessage.setTextColor(Color.parseColor(messageTextColor));
            }
            String textAlignment = userCard.getMessageTextAlign();
            setViewAlignment(userViewHolder.tvMessage, textAlignment);
            setViewStyle(userViewHolder.tvMessage, userCard.getMessageTextBold());

            userViewHolder.tvDateTime.setText(ACDate.getFormattedDateString(userCard.getAddedDate(), ACDate.WELCOME_USER_CARD_FORMAT_INDEX));
            userViewHolder.tvDateTime.setTextColor(Color.parseColor(userCard.getAddedDateColor()));
        } catch (Exception e) {
            Log.e(TAG, "Caught Exception: " + e.getMessage());
        }
    }

    private void setViewAlignment(TextView textView, String textAlignment) {
        if (!TextUtils.isEmpty(textAlignment)) {
            if (textAlignment.equalsIgnoreCase("right")) {
                textView.setGravity(Gravity.RIGHT);
            } else if (textAlignment.equalsIgnoreCase("left")) {
                textView.setGravity(Gravity.LEFT);
            } else if (textAlignment.equalsIgnoreCase("center")) {
                textView.setGravity(Gravity.CENTER);
            }
        }
    }

    private void setViewStyle(TextView textView, boolean isBold) {
        if (isBold) {
            textView.setTypeface(null, Typeface.BOLD);
        } else {
            textView.setTypeface(null, Typeface.NORMAL);
        }
    }

    //View holders
    public static class WelComeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivLogo, ivWelcomeLeft, ivWelcomeRight;
        private TextView tvChannelName, tvChannelNo, tvDate, tvWelcomeHeader, tvCost;
        private ImageView isWelcome;

        public WelComeViewHolder(View itemView) {
            super(itemView);
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_logo_image);
            tvChannelName = (TextView) itemView.findViewById(R.id.tv_channel_name);
            tvChannelNo = (TextView) itemView.findViewById(R.id.tv_channel_no);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvWelcomeHeader = (TextView) itemView.findViewById(R.id.tv_welcome_header);
            tvCost = (TextView) itemView.findViewById(R.id.tv_cost);
            isWelcome = (ImageView) itemView.findViewById(R.id.is_header_content);
            ivWelcomeLeft = (ImageView) itemView.findViewById(R.id.iv_left_one);
            ivWelcomeRight = (ImageView) itemView.findViewById(R.id.iv_right_one);
        }
    }

    public static class PromotionViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPromotionLeft, ivPromotionRight, ivLogo;
        private TextView tvChannelName, tvChannelNo, tvDate, tvPromotion;
        private TextView tvCommentsValue, tvLikesValue, tvViewValue;

        private ImageView isPromotion;
        private Button btnViewMore, btnStartChat;

        public PromotionViewHolder(View itemView) {
            super(itemView);
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_logo_image);
            tvChannelName = (TextView) itemView.findViewById(R.id.tv_channel_name);
            tvChannelNo = (TextView) itemView.findViewById(R.id.tv_channel_no);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvPromotion = (TextView) itemView.findViewById(R.id.tv_promotion_text);
            isPromotion = (ImageView) itemView.findViewById(R.id.is_promotion_content);
            tvCommentsValue = (TextView) itemView.findViewById(R.id.tv_comment_value);
            tvLikesValue = (TextView) itemView.findViewById(R.id.tv_like_value);
            tvViewValue = (TextView) itemView.findViewById(R.id.tv_view_value);
            btnStartChat = (Button) itemView.findViewById(R.id.btn_start_chat);
            btnViewMore = (Button) itemView.findViewById(R.id.btn_view_more);
            ivPromotionLeft = (ImageView) itemView.findViewById(R.id.iv_left_two);
            ivPromotionRight = (ImageView) itemView.findViewById(R.id.iv_right_two);
        }
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMessage, tvDateTime;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_message);
            tvDateTime = (TextView) itemView.findViewById(R.id.tv_date_time);
        }
    }
}
