package com.app.amakenchatui.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.amakenchatui.datamodel.ACChatResponse;
import com.app.amakenchatui.datamodel.PromotionCard;
import com.app.amakenchatui.datamodel.UserCard;
import com.app.amakenchatui.datamodel.WelcomeCard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by jai on 20/02/16.
 */
public class ACSqliteHelper extends SQLiteOpenHelper {
    private static final String TAG = "ACSqliteHelper";
    private static final String DB_NAME = "AmakenChat.sqlite";
    private static SQLiteDatabase database = null;
    private final Context context;
    private final String DB_PATH;
    //Table names
    private final String TABLE_CONTENT = "Content";
    private final String TABLE_PROMOTIONS = "Promotions";
    private final String TABLE_USER_CARD = "UserCard";
    private final String TABLE_WELCOME_CARD = "WelcomeCard";
    private final String TABLE_CARD = "Card";

    //General column names
    private final String CARD_TYPE_ID = "CardTypeId";
    private final String ID = "Id";
    private final String SERVICE_TYPE_ID = "ServiceTypeId";
    private final String PROMOTION_SERVICE_TYPE_ID = "ServiceTypeID";

    //Content table column names
    private final String FILE_TYPE = "FileType";
    private final String FILE_URL = "FileUrl";
    private final String FILE_HEIGHT = "File_Height";
    private final String FILE_WIDTH = "File_Width";
    private final String THUMBNAIL = "Thumbnail";
    private final String THUMBNAIL_HEIGHT = "Thumbnail_Height";
    private final String THUMBNAIL_WIDTH = "Thumbnail_Width";

    //Promotion and welcome table column names
    private final String SERVICE_TYPE_CATEGORY_ID = "ServiceTypeCategoryID";
    private final String WELCOME_SERVICE_TYPE_CATEGORY_ID = "ServiceTypeCategoryId";

    private final String CARD_LOGO = "CardLogo";
    private final String CHANNEL_NAME = "ChannelName";
    private final String CHANNEL_NAME_COLOR = "ChannelNameColor";
    private final String CHANNEL_NAME_ALIGN = "ChannelNameAlign";
    private final String CHANNEL_NO = "ChannelNo";
    private final String CHANNEL_NO_COLOR = "ChannelNoColor";
    private final String CHANNEL_NO_ALIGN = "ChannelNoAlign";
    private final String ADDED_DATE = "AddedDate";
    private final String ADDED_DATE_COLOR = "AddedDateColor";
    private final String HEAD_TEXT = "HeadText";
    private final String HEAD_TEXT_COLOR = "HeadTextColor";
    private final String HEAD_TEXT_ALIGN = "HeadTextAlign";
    private final String HEAD_TEXT_BOLD = "HeadTextBold";
    private final String BODY_TEXT = "BodyText";
    private final String BODY_TEXT_COLOR = "BodyTextColor";
    private final String BODY_TEXT_ALIGN = "BodyTextAlign";
    private final String BODY_TEXT_BOLD = "BodyTextBold";
    private final String COMMENTS_COUNT = "CommentsCount";
    private final String LIKE_COUNT = "LikeCount";
    private final String VIEWERS_COUNT = "ViewersCount";
    private final String MORE_TEXT = "MoreText";
    private final String MORE_TEXT_COLOR = "MoreColor";
    private final String ACTION_TEXT = "ActionText";
    private final String ACTION_TEXT_COLOR = "ActionColor";

    //User table column names
    private final String MESSAGE_TEXT_BODY = "MessageTextBody";
    private final String MESSAGE_TEXT_COLOR = "MessageTextColor";
    private final String MESSAGE_TEXT_ALIGN = "MessageTextAlign";
    private final String MESSAGE_TEXT_BOLD = "MessageTextBold";

    /**
     * Default constructor
     *
     * @param context The context
     */
    public ACSqliteHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        String packageName = context.getPackageName();
        DB_PATH = String.format("//data//data//%s//databases//", packageName);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Method to open the database
     */
    public void openDataBase() {
        String path = DB_PATH + DB_NAME;
        try {
            if (database == null) {
                createDataBase();
                database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            }
        } catch (Exception exception) {
            Log.e(TAG, "Exception in openDataBase: " + exception.getLocalizedMessage(), exception);
        }
    }

    /**
     * Method to create database
     */
    private void createDataBase() {
        try {
            boolean dbExist = checkDataBase();
            if (!dbExist) {
                this.getReadableDatabase();
                try {
                    copyDataBase();
                } catch (Exception e) {
                    Log.e(this.getClass().toString(), "Copying error", e);
                    throw new Error("Error copying database!");
                }
            } else {
                Log.d("class is", this.getClass().toString());
            }
        } catch (Exception exception) {
            Log.e(TAG, "Exception in createDataBase: " + exception.getLocalizedMessage(), exception);
        }
    }

    /**
     * Method to copy database
     */
    private void copyDataBase() {
        try {
            InputStream externalDbStream = context.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream localDbStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = externalDbStream.read(buffer)) > 0) {
                localDbStream.write(buffer, 0, bytesRead);
            }
            localDbStream.close();
            externalDbStream.close();
        } catch (IOException exception) {
            Log.e(TAG, "I/O Exception in copyDataBase: " + exception.getLocalizedMessage(), exception);
        }
    }

    /**
     * Method to check the database
     *
     * @return Return the boolean value
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDb = null;
        try {
            String path = DB_PATH + DB_NAME;
            checkDb = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {
            Log.v(this.getClass().toString(), "Error while checking db");
        }
        if (checkDb != null) {
            checkDb.close();
        }
        return checkDb != null;
    }

    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }

    public void insertChat(ACChatResponse chatResponse) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();

            //Inserting values to the Cards table
            ContentValues cardContent = new ContentValues();
            cardContent.put(CARD_TYPE_ID, chatResponse.getCardTypeId());
            cardContent.put(ID, chatResponse.getId());
            if (checkCardExist(chatResponse.getCardTypeId(), chatResponse.getId()).getCount() > 0) {
                database.update(TABLE_CARD, cardContent, CARD_TYPE_ID + "=" + chatResponse.getCardTypeId() + " AND " + ID + " = " + chatResponse.getId(), null);
            } else {
                database.insert(TABLE_CARD, null, cardContent);
            }

            ContentValues values = new ContentValues();
            //Inserting values to the welcome card table
            WelcomeCard welcomeCard = chatResponse.getWelcomeCard();
            if (welcomeCard != null) {
                values.put(CARD_TYPE_ID, chatResponse.getCardTypeId());
                values.put(ID, chatResponse.getId());
                Integer serviceTypeId = welcomeCard.getServiceTypeId();
                values.put(SERVICE_TYPE_ID, serviceTypeId);
                values.put(SERVICE_TYPE_CATEGORY_ID, welcomeCard.getServiceTypeCategoryId());
                values.put(CARD_LOGO, welcomeCard.getCardLogo());
                values.put(CHANNEL_NAME, welcomeCard.getChannelName());
                values.put(CHANNEL_NAME_COLOR, welcomeCard.getChannelNameColor());
                values.put(CHANNEL_NAME_ALIGN, welcomeCard.getChannelNameAlign());
                values.put(CHANNEL_NO, welcomeCard.getChannelNo());
                values.put(CHANNEL_NO_COLOR, welcomeCard.getChannelNoColor());
                values.put(CHANNEL_NO_ALIGN, welcomeCard.getChannelNoAlign());
                values.put(ADDED_DATE, welcomeCard.getAddedDate());
                values.put(ADDED_DATE_COLOR, welcomeCard.getAddedDateColor());
                values.put(HEAD_TEXT, welcomeCard.getHeadText());
                values.put(HEAD_TEXT_COLOR, welcomeCard.getHeadTextColor());
                values.put(HEAD_TEXT_ALIGN, welcomeCard.getHeadTextAlign());
                values.put(HEAD_TEXT_BOLD, welcomeCard.getHeadTextBold());
                values.put(BODY_TEXT, welcomeCard.getBodyText());
                values.put(BODY_TEXT_COLOR, welcomeCard.getBodyTextColor());
                values.put(BODY_TEXT_ALIGN, welcomeCard.getBodyTextAlign());
                values.put(BODY_TEXT_BOLD, welcomeCard.getHeadTextBold());
                if (checkWelcomeCardExist(serviceTypeId).getCount() > 0) {
                    database.update(TABLE_WELCOME_CARD, values, SERVICE_TYPE_ID + "=" + serviceTypeId, null);
                } else {
                    database.insert(TABLE_WELCOME_CARD, null, values);
                }
                //Inserting values to the content table with welcome card content values
                ArrayList<WelcomeCard.Content> contents = welcomeCard.getContents();
                if (contents != null) {
                    for (WelcomeCard.Content content : contents) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(CARD_TYPE_ID, chatResponse.getCardTypeId());
                        contentValues.put(ID, chatResponse.getId());
                        contentValues.put(SERVICE_TYPE_ID, welcomeCard.getServiceTypeId());
                        contentValues.put(FILE_TYPE, content.getFileType());
                        contentValues.put(FILE_URL, content.getFileUrl());
                        contentValues.put(FILE_HEIGHT, content.getFileHeight());
                        contentValues.put(FILE_WIDTH, content.getFileWidth());
                        contentValues.put(THUMBNAIL, content.getThumbnail());
                        contentValues.put(THUMBNAIL_HEIGHT, content.getThumbnailHeight());
                        contentValues.put(THUMBNAIL_WIDTH, content.getThumbnailWidth());
                        if (checkContentExist(chatResponse.getId(), chatResponse.getCardTypeId(), welcomeCard.getServiceTypeId(), content.getFileType()).getCount() > 0) {
                            database.update(TABLE_CONTENT, contentValues, ID + "= ? " + " AND " + CARD_TYPE_ID + " = ? "
                                            + " AND " + SERVICE_TYPE_ID + " = ? "
                                            + " AND " + FILE_TYPE + " = ? ",
                                    new String[]{String.valueOf(chatResponse.getId()),
                                            String.valueOf(chatResponse.getCardTypeId()),
                                            String.valueOf(welcomeCard.getServiceTypeId()),
                                            String.valueOf(content.getFileType())});

                        } else {
                            database.insert(TABLE_CONTENT, null, contentValues);
                        }
                    }
                }
            }
            //Inserting values to the promotion card table
            PromotionCard promotionCard = chatResponse.getPromotionCard();
            if (promotionCard != null) {
                values.put(CARD_TYPE_ID, chatResponse.getCardTypeId());
                values.put(ID, chatResponse.getId());
                values.put(SERVICE_TYPE_ID, promotionCard.getServiceTypeID());
                values.put(SERVICE_TYPE_CATEGORY_ID, promotionCard.getServiceTypeCategoryID());
                values.put(CARD_LOGO, promotionCard.getCardLogo());
                values.put(CHANNEL_NAME, promotionCard.getChannelName());
                values.put(CHANNEL_NAME_COLOR, promotionCard.getChannelNameColor());
                values.put(CHANNEL_NAME_ALIGN, promotionCard.getChannelNameAlign());
                values.put(CHANNEL_NO, promotionCard.getChannelNo());
                values.put(CHANNEL_NO_COLOR, promotionCard.getChannelNoColor());
                values.put(CHANNEL_NO_ALIGN, promotionCard.getChannelNoAlign());
                values.put(ADDED_DATE, promotionCard.getAddedDate());
                values.put(ADDED_DATE_COLOR, promotionCard.getAddedDateColor());
                values.put(HEAD_TEXT, promotionCard.getHeadText());
                values.put(HEAD_TEXT_COLOR, promotionCard.getHeadTextColor());
                values.put(HEAD_TEXT_ALIGN, promotionCard.getHeadTextAlign());
                values.put(HEAD_TEXT_BOLD, promotionCard.getHeadTextBold());
                values.put(BODY_TEXT, promotionCard.getBodyText());
                values.put(BODY_TEXT_COLOR, promotionCard.getBodyTextColor());
                values.put(BODY_TEXT_ALIGN, promotionCard.getBodyTextAlign());
                values.put(BODY_TEXT_BOLD, promotionCard.getHeadTextBold());
                values.put(COMMENTS_COUNT, promotionCard.getCommentsCount());
                values.put(LIKE_COUNT, promotionCard.getLikeCount());
                values.put(VIEWERS_COUNT, promotionCard.getViewersCount());
                values.put(COMMENTS_COUNT, promotionCard.getCommentsCount());
                values.put(MORE_TEXT, promotionCard.getMoreText());
                values.put(MORE_TEXT_COLOR, promotionCard.getMoreColor());
                values.put(ACTION_TEXT, promotionCard.getActionText());
                values.put(ACTION_TEXT_COLOR, promotionCard.getActionColor());
                if (checkPromotionCardExist(promotionCard.getServiceTypeID()).getCount() > 0) {
                    database.update(TABLE_PROMOTIONS, values, SERVICE_TYPE_ID + "=" + promotionCard.getServiceTypeID(), null);
                } else {
                    database.insert(TABLE_PROMOTIONS, null, values);
                }
                //Inserting values to the content table with promotion card content values
                ArrayList<PromotionCard.Content> contents = promotionCard.getContents();
                if (contents != null) {
                    for (PromotionCard.Content content : contents) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(CARD_TYPE_ID, chatResponse.getCardTypeId());
                        contentValues.put(ID, chatResponse.getId());
                        contentValues.put(SERVICE_TYPE_ID, promotionCard.getServiceTypeID());
                        contentValues.put(FILE_TYPE, content.getFileType());
                        contentValues.put(FILE_URL, content.getFileUrl());
                        contentValues.put(FILE_HEIGHT, content.getFileHeight());
                        contentValues.put(FILE_WIDTH, content.getFileWidth());
                        contentValues.put(THUMBNAIL, content.getThumbnail());
                        contentValues.put(THUMBNAIL_HEIGHT, content.getThumbnailHeight());
                        contentValues.put(THUMBNAIL_WIDTH, content.getThumbnailWidth());
                        if (checkContentExist(chatResponse.getId(), chatResponse.getCardTypeId(), promotionCard.getServiceTypeID(), content.getFileType()).getCount() > 0) {
                            database.update(TABLE_CONTENT, contentValues, ID + "= ? " + " AND " + CARD_TYPE_ID + " = ? "
                                            + " AND " + SERVICE_TYPE_ID + " = ? "
                                            + " AND " + FILE_TYPE + " = ? ",
                                    new String[]{String.valueOf(chatResponse.getId()),
                                            String.valueOf(chatResponse.getCardTypeId()),
                                            String.valueOf(promotionCard.getServiceTypeID()),
                                            String.valueOf(content.getFileType())});
                        } else {
                            database.insert(TABLE_CONTENT, null, contentValues);
                        }

                    }
                }
            }
            //Inserting values to the promotion card table
            UserCard userCard = chatResponse.getUserCard();
            if (userCard != null) {
                values.put(CARD_TYPE_ID, chatResponse.getCardTypeId());
                values.put(ID, chatResponse.getId());
                values.put(MESSAGE_TEXT_BODY, userCard.getMessageTextBody());
                values.put(MESSAGE_TEXT_COLOR, userCard.getMessageTextColor());
                values.put(MESSAGE_TEXT_BOLD, userCard.getMessageTextBold());
                values.put(MESSAGE_TEXT_ALIGN, userCard.getMessageTextAlign());
                values.put(ADDED_DATE, userCard.getAddedDate());
                values.put(ADDED_DATE_COLOR, userCard.getAddedDateColor());
                if (checkUserCardExist(chatResponse.getCardTypeId()).getCount() > 0) {
                    database.update(TABLE_USER_CARD, values, CARD_TYPE_ID + "=" + chatResponse.getCardTypeId(), null);
                } else {
                    database.insert(TABLE_USER_CARD, null, values);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "insertChat: caught sql exception: " + e.getMessage(), e);
        } catch (Exception e) {
            Log.e(TAG, "insertChat: caught exception: " + e.getMessage(), e);
        }
    }

    private Cursor checkUserCardExist(Integer cardTypeId) {
        SQLiteDatabase database = this.getWritableDatabase();
        String linesQuery = "SELECT * " + " FROM " + TABLE_USER_CARD + " WHERE " + CARD_TYPE_ID + "=" + cardTypeId;
        Cursor linesCursor = database.rawQuery(linesQuery, null);
        return linesCursor;
    }

    private Cursor checkCardExist(Integer cardTypeId, Double id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String linesQuery = "SELECT * " + " FROM " + TABLE_CARD + " WHERE " + CARD_TYPE_ID + "=" + cardTypeId + " AND " + ID + " = " + id;
        Cursor linesCursor = database.rawQuery(linesQuery, null);
        return linesCursor;
    }

    private Cursor checkWelcomeCardExist(Integer serviceTypeId) {
        SQLiteDatabase database = this.getWritableDatabase();
        String linesQuery = "SELECT * " + " FROM " + TABLE_WELCOME_CARD + " WHERE " + SERVICE_TYPE_ID + "=" + serviceTypeId;
        Cursor linesCursor = database.rawQuery(linesQuery, null);
        return linesCursor;
    }

    private Cursor checkPromotionCardExist(Integer serviceTypeId) {
        SQLiteDatabase database = this.getWritableDatabase();
        String linesQuery = "SELECT * " + " FROM " + TABLE_PROMOTIONS + " WHERE " + SERVICE_TYPE_ID + "=" + serviceTypeId;
        Cursor linesCursor = database.rawQuery(linesQuery, null);
        return linesCursor;
    }

    private Cursor checkContentExist(double id, int cardTypeId, int serviceTypeId, int fileType) {
        SQLiteDatabase database = this.getWritableDatabase();
        String linesQuery = "SELECT * " + " FROM " + TABLE_CONTENT + " WHERE " + ID + "=" + id + " AND " + CARD_TYPE_ID + "=" + cardTypeId
                + " AND " + SERVICE_TYPE_ID + "=" + serviceTypeId
                + " AND " + FILE_TYPE + "=" + fileType;
        Cursor linesCursor = database.rawQuery(linesQuery, null);
        return linesCursor;
    }

    public ArrayList<Integer> getCardIds() {
        ArrayList<Integer> cardIds = new ArrayList<>();
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String cardIDQuery = "SELECT " + CARD_TYPE_ID + " FROM " + TABLE_CARD + " ORDER BY " + CARD_TYPE_ID;
            Cursor cardIdCursor = database.rawQuery(cardIDQuery, null);
            if (cardIdCursor.moveToFirst()) {
                do {
                    cardIds.add(cardIdCursor.getInt(0));
                } while (cardIdCursor.moveToNext());
            }
            cardIdCursor.close();
        } catch (SQLException e) {
            Log.e(TAG, "getCardIds: Caught sql exception: " + e.getMessage(), e);
        } catch (Exception e) {
            Log.e(TAG, "getCardIds: Caught exception: " + e.getMessage(), e);
        }
        return cardIds;
    }

    public ACChatResponse getWelcomeCard(Integer cardId) {
        ACChatResponse chatResponse = new ACChatResponse();
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String welComeCardQuery = "SELECT * FROM " + TABLE_WELCOME_CARD + " WHERE " + CARD_TYPE_ID + " = " + cardId;
            Cursor cursor = database.rawQuery(welComeCardQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    chatResponse.setId(Double.valueOf(cursor.getInt(cursor.getColumnIndex(ID))));
                    chatResponse.setCardTypeId(cursor.getInt(cursor.getColumnIndex(CARD_TYPE_ID)));
                    WelcomeCard welcomeCard = new WelcomeCard();
                    welcomeCard.setServiceTypeId(cursor.getInt(cursor.getColumnIndex(SERVICE_TYPE_ID)));
                    welcomeCard.setServiceTypeCategoryId(cursor.getInt(cursor.getColumnIndex(WELCOME_SERVICE_TYPE_CATEGORY_ID)));
                    welcomeCard.setCardLogo(cursor.getString(cursor.getColumnIndex(CARD_LOGO)));
                    welcomeCard.setChannelName(cursor.getString(cursor.getColumnIndex(CHANNEL_NAME)));
                    welcomeCard.setChannelNameColor(cursor.getString(cursor.getColumnIndex(CHANNEL_NAME_COLOR)));
                    welcomeCard.setChannelNameAlign(cursor.getString(cursor.getColumnIndex(CHANNEL_NAME_ALIGN)));
                    welcomeCard.setChannelNo(cursor.getString(cursor.getColumnIndex(CHANNEL_NO)));
                    welcomeCard.setChannelNoColor(cursor.getString(cursor.getColumnIndex(CHANNEL_NO_COLOR)));
                    welcomeCard.setChannelNoAlign(cursor.getString(cursor.getColumnIndex(CHANNEL_NO_ALIGN)));
                    welcomeCard.setAddedDate(cursor.getString(cursor.getColumnIndex(ADDED_DATE)));
                    welcomeCard.setAddedDateColor(cursor.getString(cursor.getColumnIndex(ADDED_DATE_COLOR)));
                    welcomeCard.setHeadText(cursor.getString(cursor.getColumnIndex(HEAD_TEXT)));
                    welcomeCard.setHeadTextColor(cursor.getString(cursor.getColumnIndex(HEAD_TEXT_COLOR)));
                    welcomeCard.setHeadTextAlign(cursor.getString(cursor.getColumnIndex(HEAD_TEXT_ALIGN)));
                    welcomeCard.setHeadTextBold(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(HEAD_TEXT_BOLD))));
                    welcomeCard.setBodyText(cursor.getString(cursor.getColumnIndex(BODY_TEXT)));
                    welcomeCard.setBodyTextColor(cursor.getString(cursor.getColumnIndex(BODY_TEXT_COLOR)));
                    welcomeCard.setBodyTextAlign(cursor.getString(cursor.getColumnIndex(BODY_TEXT_ALIGN)));
                    welcomeCard.setBodyTextBold(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(BODY_TEXT_BOLD))));
                    ArrayList<WelcomeCard.Content> contents = getContents(cardId, cursor.getInt(cursor.getColumnIndex(ID)), cursor.getInt(cursor.getColumnIndex(SERVICE_TYPE_ID)));
                    welcomeCard.setContents(contents);
                    chatResponse.setWelcomeCard(welcomeCard);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "getWelcomeCard: Caught exception: " + e.getMessage(), e);
        }
        return chatResponse;
    }

    private ArrayList<WelcomeCard.Content> getContents(Integer cardId, int id, int serviceTypeId) {
        ArrayList<WelcomeCard.Content> welcomContentList = new ArrayList<>();
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String cardIDQuery = "SELECT * FROM " + TABLE_CONTENT + " WHERE " + CARD_TYPE_ID + " = " + cardId
                    + " AND " + ID + " = " + id
                    + " AND " + SERVICE_TYPE_ID + " = " + serviceTypeId
                    + " ORDER BY " + SERVICE_TYPE_ID;
            Cursor contentCursor = database.rawQuery(cardIDQuery, null);
            if (contentCursor.moveToFirst()) {
                do {
                    WelcomeCard welcomeCard = new WelcomeCard();
                    WelcomeCard.Content contentVal = welcomeCard.new Content();
                    contentVal.setFileUrl(contentCursor.getString(contentCursor.getColumnIndex(FILE_URL)));
                    contentVal.setFileType(contentCursor.getInt(contentCursor.getColumnIndex(FILE_TYPE)));
                    contentVal.setFileHeight(contentCursor.getInt(contentCursor.getColumnIndex(FILE_HEIGHT)));
                    contentVal.setFileWidth(contentCursor.getInt(contentCursor.getColumnIndex(FILE_WIDTH)));
                    contentVal.setThumbnail(contentCursor.getString(contentCursor.getColumnIndex(THUMBNAIL)));
                    contentVal.setThumbnailHeight(contentCursor.getInt(contentCursor.getColumnIndex(THUMBNAIL_HEIGHT)));
                    contentVal.setThumbnailWidth(contentCursor.getInt(contentCursor.getColumnIndex(THUMBNAIL_WIDTH)));
                    welcomContentList.add(contentVal);
                } while (contentCursor.moveToNext());
            }
            contentCursor.close();
        } catch (Exception e) {
            Log.e(TAG, "getContents: Caught exception: " + e.getMessage(), e);
        }
        return welcomContentList;
    }

    public ACChatResponse getPromotionCard(Integer cardId) {
        ACChatResponse chatResponse = new ACChatResponse();
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String promotionCardQuery = "SELECT * FROM " + TABLE_PROMOTIONS + " WHERE " + CARD_TYPE_ID + " = " + cardId;
            Cursor cursor = database.rawQuery(promotionCardQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    PromotionCard promotionCard = new PromotionCard();
                    promotionCard.setServiceTypeID(cursor.getInt(cursor.getColumnIndex(PROMOTION_SERVICE_TYPE_ID)));
                    promotionCard.setServiceTypeCategoryID(cursor.getInt(cursor.getColumnIndex(SERVICE_TYPE_CATEGORY_ID)));
                    promotionCard.setCardLogo(cursor.getString(cursor.getColumnIndex(CARD_LOGO)));
                    promotionCard.setChannelName(cursor.getString(cursor.getColumnIndex(CHANNEL_NAME)));
                    promotionCard.setChannelNameColor(cursor.getString(cursor.getColumnIndex(CHANNEL_NAME_COLOR)));
                    promotionCard.setChannelNameAlign(cursor.getString(cursor.getColumnIndex(CHANNEL_NAME_ALIGN)));
                    promotionCard.setChannelNo(Integer.valueOf(cursor.getString(cursor.getColumnIndex(CHANNEL_NO))));
                    promotionCard.setChannelNoColor(cursor.getString(cursor.getColumnIndex(CHANNEL_NO_COLOR)));
                    promotionCard.setChannelNoAlign(cursor.getString(cursor.getColumnIndex(CHANNEL_NO_ALIGN)));
                    promotionCard.setAddedDate(cursor.getString(cursor.getColumnIndex(ADDED_DATE)));
                    promotionCard.setAddedDateColor(cursor.getString(cursor.getColumnIndex(ADDED_DATE_COLOR)));
                    promotionCard.setHeadText(cursor.getString(cursor.getColumnIndex(HEAD_TEXT)));
                    promotionCard.setHeadTextColor(cursor.getString(cursor.getColumnIndex(HEAD_TEXT_COLOR)));
                    promotionCard.setHeadTextAlign(cursor.getString(cursor.getColumnIndex(HEAD_TEXT_ALIGN)));
                    promotionCard.setHeadTextBold(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(HEAD_TEXT_BOLD))));
                    promotionCard.setBodyText(cursor.getString(cursor.getColumnIndex(BODY_TEXT)));
                    promotionCard.setBodyTextColor(cursor.getString(cursor.getColumnIndex(BODY_TEXT_COLOR)));
                    promotionCard.setBodyTextAlign(cursor.getString(cursor.getColumnIndex(BODY_TEXT_ALIGN)));
                    promotionCard.setBodyTextBold(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(BODY_TEXT_BOLD))));
                    promotionCard.setCommentsCount(String.valueOf(cursor.getInt(cursor.getColumnIndex(COMMENTS_COUNT))));
                    promotionCard.setLikeCount(String.valueOf(cursor.getInt(cursor.getColumnIndex(LIKE_COUNT))));
                    promotionCard.setViewersCount(String.valueOf(cursor.getInt(cursor.getColumnIndex(VIEWERS_COUNT))));
                    promotionCard.setMoreText(cursor.getString(cursor.getColumnIndex(MORE_TEXT)));
                    promotionCard.setMoreColor(cursor.getString(cursor.getColumnIndex(MORE_TEXT_COLOR)));
                    promotionCard.setActionText(cursor.getString(cursor.getColumnIndex(ACTION_TEXT)));
                    promotionCard.setActionColor(cursor.getString(cursor.getColumnIndex(ACTION_TEXT_COLOR)));
                    promotionCard.setMoreText(cursor.getString(cursor.getColumnIndex(MORE_TEXT)));
                    chatResponse.setId(Double.valueOf(cursor.getInt(cursor.getColumnIndex(ID))));
                    chatResponse.setCardTypeId(cursor.getInt(cursor.getColumnIndex(CARD_TYPE_ID)));
                    ArrayList<PromotionCard.Content> contents = getPromotionContents(cardId, cursor.getInt(cursor.getColumnIndex(ID)), cursor.getInt(cursor.getColumnIndex(PROMOTION_SERVICE_TYPE_ID)));
                    promotionCard.setContents(contents);
                    chatResponse.setPromotionCard(promotionCard);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (NumberFormatException e) {
            Log.e(TAG, "getPromotionCard: Caught number format exception: " + e.getMessage(), e);
        } catch (Exception e) {
            Log.e(TAG, "getPromotionCard: Caught exception: " + e.getMessage(), e);
        }
        return chatResponse;
    }

    private ArrayList<PromotionCard.Content> getPromotionContents(Integer cardId, int id, int serviceTypeId) {
        ArrayList<PromotionCard.Content> promotionContentList = new ArrayList<>();
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String cardIDQuery = "SELECT * FROM " + TABLE_CONTENT + " WHERE " + CARD_TYPE_ID + " = " + cardId
                    + " AND " + ID + " = " + id
                    + " AND " + SERVICE_TYPE_ID + " = " + serviceTypeId
                    + " ORDER BY " + SERVICE_TYPE_ID;
            Cursor contentCursor = database.rawQuery(cardIDQuery, null);
            if (contentCursor.moveToFirst()) {
                do {
                    PromotionCard promotionCard = new PromotionCard();
                    PromotionCard.Content contentVal = promotionCard.new Content();
                    contentVal.setFileUrl(contentCursor.getString(contentCursor.getColumnIndex(FILE_URL)));
                    contentVal.setFileType(contentCursor.getInt(contentCursor.getColumnIndex(FILE_TYPE)));
                    contentVal.setFileHeight(contentCursor.getInt(contentCursor.getColumnIndex(FILE_HEIGHT)));
                    contentVal.setFileWidth(contentCursor.getInt(contentCursor.getColumnIndex(FILE_WIDTH)));
                    contentVal.setThumbnail(contentCursor.getString(contentCursor.getColumnIndex(THUMBNAIL)));
                    contentVal.setThumbnailHeight(contentCursor.getInt(contentCursor.getColumnIndex(THUMBNAIL_HEIGHT)));
                    contentVal.setThumbnailWidth(contentCursor.getInt(contentCursor.getColumnIndex(THUMBNAIL_WIDTH)));
                    promotionContentList.add(contentVal);
                } while (contentCursor.moveToNext());
            }
            contentCursor.close();
        } catch (Exception e) {
            Log.e(TAG, "getPromotionContents: Caught exception: " + e.getMessage(), e);
        }
        return promotionContentList;
    }

    public ACChatResponse getUserCard(Integer cardId) {
        ACChatResponse chatResponse = new ACChatResponse();
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String promotionCardQuery = "SELECT * FROM " + TABLE_USER_CARD + " WHERE " + CARD_TYPE_ID + " = " + cardId;
            Cursor cursor = database.rawQuery(promotionCardQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    chatResponse.setId(Double.valueOf(cursor.getInt(cursor.getColumnIndex(ID))));
                    chatResponse.setCardTypeId(cursor.getInt(cursor.getColumnIndex(CARD_TYPE_ID)));
                    UserCard userCard = new UserCard();
                    userCard.setAddedDate(cursor.getString(cursor.getColumnIndex(ADDED_DATE)));
                    userCard.setAddedDateColor(cursor.getString(cursor.getColumnIndex(ADDED_DATE_COLOR)));
                    userCard.setMessageTextBody(cursor.getString(cursor.getColumnIndex(MESSAGE_TEXT_BODY)));
                    userCard.setMessageTextColor(cursor.getString(cursor.getColumnIndex(MESSAGE_TEXT_COLOR)));
                    userCard.setMessageTextBold(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(MESSAGE_TEXT_BOLD))));
                    userCard.setMessageTextAlign(cursor.getString(cursor.getColumnIndex(MESSAGE_TEXT_ALIGN)));
                    chatResponse.setUserCard(userCard);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "getUserCard: Caught exception: " + e.getMessage(), e);
        }
        return chatResponse;
    }
}