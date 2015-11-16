package natuan.org.androiddesigntablayout.model;

import android.graphics.Bitmap;

/**
 * Created by madhur on 17/01/15.
 */
public class ChatMessage {
    private Bitmap mImage;

    private String messageText;
    private UserType userType;
    private Status messageStatus;
    private String typeStyle;
    private String typeColor;


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
}
