package com.app.amakenchatui.requestComponents;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by jai on 20/02/16.
 */
@Root(name = "GetTvChatCards", strict = false)
public class ACChatRequestData {
    @Element(name = "ns1:LastRecordId", required = false)
    private String lastRecordId;
    @Element(name = "ns1:TotalRecords", required = false)
    private String totalRecord;

    public String getLastRecordId() {
        return lastRecordId;
    }

    public void setLastRecordId(String lastRecordId) {
        this.lastRecordId = lastRecordId;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }
}
