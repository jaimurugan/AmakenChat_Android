package com.app.amakenchatui.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by jai on 20/02/16.
 */
@Root(name = "soap:Envelope")
@NamespaceList({
        @Namespace( prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
        @Namespace( prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema")
})
public class ACChatResponseEnvelope {
    @Element(required = false, name = "Body")
    private ACChatResponseBody body;

    public ACChatResponseBody getBody() {
        return body;
    }

    public void setBody(ACChatResponseBody body) {
        this.body = body;
    }
}
