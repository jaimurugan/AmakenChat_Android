package com.app.amakenchatui.requestComponents;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by jai on 20/02/16.
 */

@Root(name = "SOAP-ENV:Envelope")
@NamespaceList({
        @Namespace( prefix = "SOAP-ENV", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "ns1", reference = "http://tempuri.org/"),
})
public class ACChatRequestEnvelope {
    @Element(name = "SOAP-ENV:Body", required = false)
    private ACChatRequestBody body;

    public ACChatRequestBody getBody() {
        return body;
    }

    public void setBody(ACChatRequestBody body) {
        this.body = body;
    }

}
