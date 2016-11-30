package com.app.amakenchatui.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by jai on 20/02/16.
 */
@Root(name = "GetTvChatCardsResponse", strict = false)
@Namespace(reference = "http://tempuri.org/")
public class GetTvChatCardsResponse {
    @Element(name = "GetTvChatCardsResult", required = false)
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
