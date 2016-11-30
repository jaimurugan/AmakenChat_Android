package com.app.amakenchatui.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jai on 20/02/16.
 */

public class PromotionCard {
    @SerializedName("ServiceTypeID")
    @Expose
    private Integer serviceTypeID;
    @SerializedName("ServiceTypeCategoryID")
    @Expose
    private Integer serviceTypeCategoryID;
    @SerializedName("CardLogo")
    @Expose
    private String cardLogo;
    @SerializedName("ChannelName")
    @Expose
    private String channelName;
    @SerializedName("ChannelNameColor")
    @Expose
    private String channelNameColor;
    @SerializedName("ChannelNameAlign")
    @Expose
    private String channelNameAlign;
    @SerializedName("ChannelNo")
    @Expose
    private Integer channelNo;
    @SerializedName("ChannelNoColor")
    @Expose
    private String channelNoColor;
    @SerializedName("ChannelNoAlign")
    @Expose
    private String channelNoAlign;
    @SerializedName("AddedDate")
    @Expose
    private String addedDate;
    @SerializedName("AddedDateColor")
    @Expose
    private String addedDateColor;
    @SerializedName("HeadText")
    @Expose
    private String headText;
    @SerializedName("HeadTextColor")
    @Expose
    private String headTextColor;
    @SerializedName("HeadTextAlign")
    @Expose
    private String headTextAlign;
    @SerializedName("HeadTextBold")
    @Expose
    private Boolean headTextBold;
    @SerializedName("BodyText")
    @Expose
    private String bodyText;
    @SerializedName("BodyTextColor")
    @Expose
    private String bodyTextColor;
    @SerializedName("BodyTextAlign")
    @Expose
    private String bodyTextAlign;
    @SerializedName("BodyTextBold")
    @Expose
    private Boolean bodyTextBold;
    @SerializedName("CommentsCount")
    @Expose
    private String commentsCount;
    @SerializedName("LikeCount")
    @Expose
    private String likeCount;
    @SerializedName("ViewersCount")
    @Expose
    private String viewersCount;
    @SerializedName("CanComment")
    @Expose
    private Boolean canComment;
    @SerializedName("CanLike")
    @Expose
    private Boolean canLike;
    @SerializedName("CanView")
    @Expose
    private Boolean canView;
    @SerializedName("ShowComments")
    @Expose
    private Boolean showComments;
    @SerializedName("ShowLikes")
    @Expose
    private Boolean showLikes;
    @SerializedName("ShowViews")
    @Expose
    private Boolean showViews;
    @SerializedName("ShowMore")
    @Expose
    private Boolean showMore;
    @SerializedName("MoreText")
    @Expose
    private String moreText;
    @SerializedName("MoreColor")
    @Expose
    private String moreColor;
    @SerializedName("ActionType")
    @Expose
    private Integer actionType;
    @SerializedName("ShowAction")
    @Expose
    private Boolean showAction;
    @SerializedName("ActionText")
    @Expose
    private String actionText;
    @SerializedName("ActionColor")
    @Expose
    private String actionColor;
    @SerializedName("PromotionId")
    @Expose
    private Integer promotionId;
    @SerializedName("OtherId")
    @Expose
    private Integer otherId;
    @SerializedName("ShowFooter")
    @Expose
    private Boolean showFooter;
    @SerializedName("ShowHelpIcon")
    @Expose
    private Boolean showHelpIcon;
    @SerializedName("FooterHelpCommand")
    @Expose
    private String footerHelpCommand;
    @SerializedName("Contents")
    @Expose
    private ArrayList<Content> contents = new ArrayList<Content>();

    /**
     * @return The serviceTypeID
     */
    public Integer getServiceTypeID() {
        return serviceTypeID;
    }

    /**
     * @param serviceTypeID The ServiceTypeID
     */
    public void setServiceTypeID(Integer serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    /**
     * @return The serviceTypeCategoryID
     */
    public Integer getServiceTypeCategoryID() {
        return serviceTypeCategoryID;
    }

    /**
     * @param serviceTypeCategoryID The ServiceTypeCategoryID
     */
    public void setServiceTypeCategoryID(Integer serviceTypeCategoryID) {
        this.serviceTypeCategoryID = serviceTypeCategoryID;
    }

    /**
     * @return The cardLogo
     */
    public String getCardLogo() {
        return cardLogo;
    }

    /**
     * @param cardLogo The CardLogo
     */
    public void setCardLogo(String cardLogo) {
        this.cardLogo = cardLogo;
    }

    /**
     * @return The channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * @param channelName The ChannelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * @return The channelNameColor
     */
    public String getChannelNameColor() {
        return channelNameColor;
    }

    /**
     * @param channelNameColor The ChannelNameColor
     */
    public void setChannelNameColor(String channelNameColor) {
        this.channelNameColor = channelNameColor;
    }

    /**
     * @return The channelNameAlign
     */
    public String getChannelNameAlign() {
        return channelNameAlign;
    }

    /**
     * @param channelNameAlign The ChannelNameAlign
     */
    public void setChannelNameAlign(String channelNameAlign) {
        this.channelNameAlign = channelNameAlign;
    }

    /**
     * @return The channelNo
     */
    public Integer getChannelNo() {
        return channelNo;
    }

    /**
     * @param channelNo The ChannelNo
     */
    public void setChannelNo(Integer channelNo) {
        this.channelNo = channelNo;
    }

    /**
     * @return The channelNoColor
     */
    public String getChannelNoColor() {
        return channelNoColor;
    }

    /**
     * @param channelNoColor The ChannelNoColor
     */
    public void setChannelNoColor(String channelNoColor) {
        this.channelNoColor = channelNoColor;
    }

    /**
     * @return The channelNoAlign
     */
    public String getChannelNoAlign() {
        return channelNoAlign;
    }

    /**
     * @param channelNoAlign The ChannelNoAlign
     */
    public void setChannelNoAlign(String channelNoAlign) {
        this.channelNoAlign = channelNoAlign;
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

    /**
     * @return The headText
     */
    public String getHeadText() {
        return headText;
    }

    /**
     * @param headText The HeadText
     */
    public void setHeadText(String headText) {
        this.headText = headText;
    }

    /**
     * @return The headTextColor
     */
    public String getHeadTextColor() {
        return headTextColor;
    }

    /**
     * @param headTextColor The HeadTextColor
     */
    public void setHeadTextColor(String headTextColor) {
        this.headTextColor = headTextColor;
    }

    /**
     * @return The headTextAlign
     */
    public String getHeadTextAlign() {
        return headTextAlign;
    }

    /**
     * @param headTextAlign The HeadTextAlign
     */
    public void setHeadTextAlign(String headTextAlign) {
        this.headTextAlign = headTextAlign;
    }

    /**
     * @return The headTextBold
     */
    public Boolean getHeadTextBold() {
        return headTextBold;
    }

    /**
     * @param headTextBold The HeadTextBold
     */
    public void setHeadTextBold(Boolean headTextBold) {
        this.headTextBold = headTextBold;
    }

    /**
     * @return The bodyText
     */
    public String getBodyText() {
        return bodyText;
    }

    /**
     * @param bodyText The BodyText
     */
    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    /**
     * @return The bodyTextColor
     */
    public String getBodyTextColor() {
        return bodyTextColor;
    }

    /**
     * @param bodyTextColor The BodyTextColor
     */
    public void setBodyTextColor(String bodyTextColor) {
        this.bodyTextColor = bodyTextColor;
    }

    /**
     * @return The bodyTextAlign
     */
    public String getBodyTextAlign() {
        return bodyTextAlign;
    }

    /**
     * @param bodyTextAlign The BodyTextAlign
     */
    public void setBodyTextAlign(String bodyTextAlign) {
        this.bodyTextAlign = bodyTextAlign;
    }

    /**
     * @return The bodyTextBold
     */
    public Boolean getBodyTextBold() {
        return bodyTextBold;
    }

    /**
     * @param bodyTextBold The BodyTextBold
     */
    public void setBodyTextBold(Boolean bodyTextBold) {
        this.bodyTextBold = bodyTextBold;
    }

    /**
     * @return The commentsCount
     */
    public String getCommentsCount() {
        return commentsCount;
    }

    /**
     * @param commentsCount The CommentsCount
     */
    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    /**
     * @return The likeCount
     */
    public String getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount The LikeCount
     */
    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return The viewersCount
     */
    public String getViewersCount() {
        return viewersCount;
    }

    /**
     * @param viewersCount The ViewersCount
     */
    public void setViewersCount(String viewersCount) {
        this.viewersCount = viewersCount;
    }

    /**
     * @return The canComment
     */
    public Boolean getCanComment() {
        return canComment;
    }

    /**
     * @param canComment The CanComment
     */
    public void setCanComment(Boolean canComment) {
        this.canComment = canComment;
    }

    /**
     * @return The canLike
     */
    public Boolean getCanLike() {
        return canLike;
    }

    /**
     * @param canLike The CanLike
     */
    public void setCanLike(Boolean canLike) {
        this.canLike = canLike;
    }

    /**
     * @return The canView
     */
    public Boolean getCanView() {
        return canView;
    }

    /**
     * @param canView The CanView
     */
    public void setCanView(Boolean canView) {
        this.canView = canView;
    }

    /**
     * @return The showComments
     */
    public Boolean getShowComments() {
        return showComments;
    }

    /**
     * @param showComments The ShowComments
     */
    public void setShowComments(Boolean showComments) {
        this.showComments = showComments;
    }

    /**
     * @return The showLikes
     */
    public Boolean getShowLikes() {
        return showLikes;
    }

    /**
     * @param showLikes The ShowLikes
     */
    public void setShowLikes(Boolean showLikes) {
        this.showLikes = showLikes;
    }

    /**
     * @return The showViews
     */
    public Boolean getShowViews() {
        return showViews;
    }

    /**
     * @param showViews The ShowViews
     */
    public void setShowViews(Boolean showViews) {
        this.showViews = showViews;
    }

    /**
     * @return The showMore
     */
    public Boolean getShowMore() {
        return showMore;
    }

    /**
     * @param showMore The ShowMore
     */
    public void setShowMore(Boolean showMore) {
        this.showMore = showMore;
    }

    /**
     * @return The moreText
     */
    public String getMoreText() {
        return moreText;
    }

    /**
     * @param moreText The MoreText
     */
    public void setMoreText(String moreText) {
        this.moreText = moreText;
    }

    /**
     * @return The moreColor
     */
    public String getMoreColor() {
        return moreColor;
    }

    /**
     * @param moreColor The MoreColor
     */
    public void setMoreColor(String moreColor) {
        this.moreColor = moreColor;
    }

    /**
     * @return The actionType
     */
    public Integer getActionType() {
        return actionType;
    }

    /**
     * @param actionType The ActionType
     */
    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    /**
     * @return The showAction
     */
    public Boolean getShowAction() {
        return showAction;
    }

    /**
     * @param showAction The ShowAction
     */
    public void setShowAction(Boolean showAction) {
        this.showAction = showAction;
    }

    /**
     * @return The actionText
     */
    public String getActionText() {
        return actionText;
    }

    /**
     * @param actionText The ActionText
     */
    public void setActionText(String actionText) {
        this.actionText = actionText;
    }

    /**
     * @return The actionColor
     */
    public String getActionColor() {
        return actionColor;
    }

    /**
     * @param actionColor The ActionColor
     */
    public void setActionColor(String actionColor) {
        this.actionColor = actionColor;
    }

    /**
     * @return The promotionId
     */
    public Integer getPromotionId() {
        return promotionId;
    }

    /**
     * @param promotionId The PromotionId
     */
    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    /**
     * @return The otherId
     */
    public Integer getOtherId() {
        return otherId;
    }

    /**
     * @param otherId The OtherId
     */
    public void setOtherId(Integer otherId) {
        this.otherId = otherId;
    }

    /**
     * @return The showFooter
     */
    public Boolean getShowFooter() {
        return showFooter;
    }

    /**
     * @param showFooter The ShowFooter
     */
    public void setShowFooter(Boolean showFooter) {
        this.showFooter = showFooter;
    }

    /**
     * @return The showHelpIcon
     */
    public Boolean getShowHelpIcon() {
        return showHelpIcon;
    }

    /**
     * @param showHelpIcon The ShowHelpIcon
     */
    public void setShowHelpIcon(Boolean showHelpIcon) {
        this.showHelpIcon = showHelpIcon;
    }

    /**
     * @return The footerHelpCommand
     */
    public String getFooterHelpCommand() {
        return footerHelpCommand;
    }

    /**
     * @param footerHelpCommand The FooterHelpCommand
     */
    public void setFooterHelpCommand(String footerHelpCommand) {
        this.footerHelpCommand = footerHelpCommand;
    }

    /**
     * @return The contents
     */
    public ArrayList<Content> getContents() {
        return contents;
    }

    /**
     * @param contents The Contents
     */
    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }

    public class Content {

        @SerializedName("FileType")
        @Expose
        private Integer fileType;
        @SerializedName("FileUrl")
        @Expose
        private String fileUrl;
        @SerializedName("File_Height")
        @Expose
        private Integer fileHeight;
        @SerializedName("File_Width")
        @Expose
        private Integer fileWidth;
        @SerializedName("Thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("Thumbnail_Height")
        @Expose
        private Integer thumbnailHeight;
        @SerializedName("Thumbnail_Width")
        @Expose
        private Integer thumbnailWidth;

        /**
         * @return The fileType
         */
        public Integer getFileType() {
            return fileType;
        }

        /**
         * @param fileType The FileType
         */
        public void setFileType(Integer fileType) {
            this.fileType = fileType;
        }

        /**
         * @return The fileUrl
         */
        public String getFileUrl() {
            return fileUrl;
        }

        /**
         * @param fileUrl The FileUrl
         */
        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        /**
         * @return The fileHeight
         */
        public Integer getFileHeight() {
            return fileHeight;
        }

        /**
         * @param fileHeight The File_Height
         */
        public void setFileHeight(Integer fileHeight) {
            this.fileHeight = fileHeight;
        }

        /**
         * @return The fileWidth
         */
        public Integer getFileWidth() {
            return fileWidth;
        }

        /**
         * @param fileWidth The File_Width
         */
        public void setFileWidth(Integer fileWidth) {
            this.fileWidth = fileWidth;
        }

        /**
         * @return The thumbnail
         */
        public String getThumbnail() {
            return thumbnail;
        }

        /**
         * @param thumbnail The Thumbnail
         */
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        /**
         * @return The thumbnailHeight
         */
        public Integer getThumbnailHeight() {
            return thumbnailHeight;
        }

        /**
         * @param thumbnailHeight The Thumbnail_Height
         */
        public void setThumbnailHeight(Integer thumbnailHeight) {
            this.thumbnailHeight = thumbnailHeight;
        }

        /**
         * @return The thumbnailWidth
         */
        public Integer getThumbnailWidth() {
            return thumbnailWidth;
        }

        /**
         * @param thumbnailWidth The Thumbnail_Width
         */
        public void setThumbnailWidth(Integer thumbnailWidth) {
            this.thumbnailWidth = thumbnailWidth;
        }
    }
}
