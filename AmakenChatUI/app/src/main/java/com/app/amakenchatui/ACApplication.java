package com.app.amakenchatui;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import com.app.amakenchatui.helper.ACSqliteHelper;
import com.app.amakenchatui.service.ACRestClient;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by jai on 20/02/16.
 */

public class ACApplication extends Application {
    private static ACRestClient restClient;
    private static DisplayImageOptions userDisplayImageOptions;
    private static ACApplication m_instance;
    private final String TAG = this.getClass().getSimpleName();
    private ACSqliteHelper databaseHelper;

    public static ACApplication getInstance() {
        return m_instance;
    }

    public static ACRestClient getRestClient() {
        return restClient;
    }

    /**
     * Method to get the display options
     *
     * @return Return the options
     */
    public static DisplayImageOptions getUserDisplayOptions() {
        return userDisplayImageOptions;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        m_instance = this;
        restClient = new ACRestClient();
        initImageLoader();
        initDatabaseHelper();
    }

    private void initDatabaseHelper() {
        try {
            databaseHelper = new ACSqliteHelper(this);
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        databaseHelper.openDataBase();

    }

    /**
     * Method to get the database instance
     *
     * @return return the database instance
     */
    public ACSqliteHelper getDataBaseHelper() {
        return databaseHelper;
    }

    /**
     * Initialise universal image loader
     */
    private void initImageLoader() {
        userDisplayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_default_placeholder) // resource or drawable
                .showImageForEmptyUri(R.drawable.ic_default_placeholder) // resource or drawable
                .showImageOnFail(R.drawable.ic_default_placeholder) // resource or drawable
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(userDisplayImageOptions)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
