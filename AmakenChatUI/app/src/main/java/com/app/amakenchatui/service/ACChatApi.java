package com.app.amakenchatui.service;

import com.app.amakenchatui.requestComponents.ACChatRequestEnvelope;
import com.app.amakenchatui.response.ACChatResponseEnvelope;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by jai on 20/02/16.
 */
public interface ACChatApi {
    @Headers({
            "Content-Type: text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("testservice.asmx?wsdl")
    Call<ACChatResponseEnvelope> requestChat(@Body ACChatRequestEnvelope body);
}
