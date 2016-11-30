package com.app.amakenchatui.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by jai on 20/02/16.
 */
@Root(name = "Body", strict = false)
public class ACChatResponseBody {
    @Element(name = "GetTvChatCardsResponse", required = false)
    private GetTvChatCardsResponse data;

    public GetTvChatCardsResponse getData() {
        return data;
    }

    public void setData(GetTvChatCardsResponse data) {
        this.data = data;
    }
}
