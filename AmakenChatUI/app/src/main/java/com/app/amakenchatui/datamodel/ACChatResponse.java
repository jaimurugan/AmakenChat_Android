package com.app.amakenchatui.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jai on 20/02/16.
 */

public class ACChatResponse {
    @SerializedName("CardTypeId")
    @Expose
    private Integer cardTypeId;
    @SerializedName("Id")
    @Expose
    private Double id;
    @SerializedName("WelcomeCard")
    @Expose
    private WelcomeCard welcomeCard;
    @SerializedName("PromotionCard")
    @Expose
    private PromotionCard promotionCard;

    @SerializedName("UserCard")
    @Expose
    private UserCard userCard;

    /**
     * @return The cardTypeId
     */
    public Integer getCardTypeId() {
        return cardTypeId;
    }

    /**
     * @param cardTypeId The CardTypeId
     */
    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    /**
     * @return The id
     */
    public Double getId() {
        return id;
    }

    /**
     * @param id The Id
     */
    public void setId(Double id) {
        this.id = id;
    }

    /**
     * @return The welcomeCard
     */
    public WelcomeCard getWelcomeCard() {
        return welcomeCard;
    }

    /**
     * @param welcomeCard The WelcomeCard
     */
    public void setWelcomeCard(WelcomeCard welcomeCard) {
        this.welcomeCard = welcomeCard;
    }


    /**
     * @return The promotionCard
     */
    public PromotionCard getPromotionCard() {
        return promotionCard;
    }

    /**
     * @param promotionCard The PromotionCard
     */
    public void setPromotionCard(PromotionCard promotionCard) {
        this.promotionCard = promotionCard;
    }


    /**
     * @return The userCard
     */
    public UserCard getUserCard() {
        return userCard;
    }

    /**
     * @param userCard The UserCard
     */
    public void setUserCard(UserCard userCard) {
        this.userCard = userCard;
    }


}
