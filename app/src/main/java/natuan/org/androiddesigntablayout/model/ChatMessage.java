package natuan.org.androiddesigntablayout.model;

import android.graphics.Bitmap;

/**
 * Created by madhur on 17/01/15.
 */
public class ChatMessage {
    public final static int MSG_TYPE_TEXT 	= 0;
    public final static int MSG_TYPE_CAMERA = 1;
    public final static int MSG_TYPE_PHOTO 	= 2;
    public final static int MSG_TYPE_VIDEO	= 3;

    private Bitmap mImage;
    private String mUrlImage;
    private String messageText;
    private UserType userType;
    private Status messageStatus;
    private String typeStyle;
    private String typeColor;

    private int mType;


    public  ChatMessage(){

    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    private long messageTime;

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }


    public String getTypeStyle() {

        return typeStyle;
    }

    public void setTypeStyle(String typeStyle) {
        this.typeStyle = typeStyle;
    }

    public String getTypeColor() {

        return typeColor;
    }

    public void setTypeColor(String typeColor) {
        this.typeColor = typeColor;
    }


    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setMessageStatus(Status messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageText() {

        return messageText;
    }


    public UserType getUserType() {
        return userType;
    }

    public Status getMessageStatus() {
        return messageStatus;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getmUrlImage() {
        return mUrlImage;
    }

    public void setmUrlImage(String mUrlImage) {
        this.mUrlImage = mUrlImage;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}
