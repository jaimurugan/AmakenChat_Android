package com.app.amakenchatui.service;

import com.app.amakenchatui.ACAppConstants;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by jai on 20/02/16.
 */
public class ACRestClient {
    private ACChatApi chatApiInstance;
    public ACRestClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Strategy strategy = new AnnotationStrategy();

        Serializer serializer = new Persister(strategy);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .baseUrl(ACAppConstants.BASE_URL)
                .client(okHttpClient)
                .build();

        chatApiInstance = retrofit.create(ACChatApi.class);
    }

    public ACChatApi getChatApiInstance() {
        return chatApiInstance;
    }

}
