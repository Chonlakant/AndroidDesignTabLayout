package natuan.org.androiddesigntablayout.model;

/**
 * Created by root1 on 6/10/15.
 */
public class Conversation {

    public int cid;
    public int partnerId;
    public String msg;
    public String userName;
    public String avatraUrl;
    public String time;

    public Conversation(int cid, int partnerId, String msg, String userName, String avatraUrl, String time) {
        this.cid = cid;
        this.partnerId = partnerId;
        this.msg = msg;
        this.userName = userName;
        this.avatraUrl = avatraUrl;
        this.time = time;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAvatraUrl(String avatraUrl) {
        this.avatraUrl = avatraUrl;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCid() {
        return cid;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public String getMsg() {
        return msg;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatraUrl() {
        return avatraUrl;
    }

    public String getTime() {
        return time;
    }
}
