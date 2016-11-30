package com.app.amakenchatui.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jai on 20/02/16.
 */

public class UserCard {
    @SerializedName("MessageTextBody")
    @Expose
    private String messageTextBody;
    @SerializedName("MessageTextColor")
    @Expose
    private String messageTextColor;
    @SerializedName("MessageTextAlign")
    @Expose
    private String messageTextAlign;
    @SerializedName("MessageTextBold")
    @Expose
    private Boolean messageTextBold;
    @SerializedName("AddedDate")
    @Expose
    private String addedDate;
    @SerializedName("AddedDateColor")
    @Expose
    private String addedDateColor;

    /**
     * @return The messageTextBody
     */
    public String getMessageTextBody() {
        return messageTextBody;
    }

    /**
     * @param messageTextBody The MessageTextBody
     */
    public void setMessageTextBody(String messageTextBody) {
        this.messageTextBody = messageTextBody;
    }

    /**
     * @return The messageTextColor
     */
    public String getMessageTextColor() {
        return messageTextColor;
    }

    /**
     * @param messageTextColor The MessageTextColor
     */
    public void setMessageTextColor(String messageTextColor) {
        this.messageTextColor = messageTextColor;
    }

    /**
     * @return The messageTextAlign
     */
    public String getMessageTextAlign() {
        return messageTextAlign;
    }

    /**
     * @param messageTextAlign The MessageTextAlign
     */
    public void setMessageTextAlign(String messageTextAlign) {
        this.messageTextAlign = messageTextAlign;
    }

    /**
     * @return The messageTextBold
     */
    public Boolean getMessageTextBold() {
        return messageTextBold;
    }

    /**
     * @param messageTextBold The MessageTextBold
     */
    public void setMessageTextBold(Boolean messageTextBold) {
        this.messageTextBold = messageTextBold;
    }

    /**
     * @return The addedDate
     */
    public String getAddedDate() {
        return addedDate;
    }

    /**
     * @param addedDate The AddedDate
     */
    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    /**
     * @return The addedDateColor
     */
    public String getAddedDateColor() {
        return addedDateColor;
    }

    /**
     * @param addedDateColor The AddedDateColor
     */
    public void setAddedDateColor(String addedDateColor) {
        this.addedDateColor = addedDateColor;
    }

}
