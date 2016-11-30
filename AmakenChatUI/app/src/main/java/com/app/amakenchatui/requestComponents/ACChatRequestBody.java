package com.app.amakenchatui.requestComponents;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by jai on 20/02/16.
 */
@Root(name = "SOAP-ENV:Body", strict = false)
public class ACChatRequestBody {
    @Element(name = "ns1:GetTvChatCards",required = false)
    private ACChatRequestData acChatRequestData;

    public ACChatRequestData getACChatRequestData() {
        return acChatRequestData;
    }

    public void setACChatRequestData(ACChatRequestData usStatesRequestData) {
        this.acChatRequestData = usStatesRequestData;
    }
}
