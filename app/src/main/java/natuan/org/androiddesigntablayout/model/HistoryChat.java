package natuan.org.androiddesigntablayout.model;

import java.util.List;

/**
 * Created by root1 on 11/27/15.
 */
public class HistoryChat {


    /**
     * id : 304
     * conversationId : 2
     * senderId : 1
     * message : Hjknbn
     * messageType : 0
     * data : {}
     * readCount : 0
     * ipAddress : null
     * createdAt : 2015-11-25T20:44:50.000Z
     * sender : {"senderId":1,"id":1,"username":"koredevman","name":"Worachet Saengprab","avatar":"themes/vdomax1.1/images/default-male-avatar","extension":"png"}
     */

    private List<ContentEntity> content;

    public void setContent(List<ContentEntity> content) {
        this.content = content;
    }

    public List<ContentEntity> getContent() {
        return content;
    }

    public static class ContentEntity {
        private int id;
        private int conversationId;
        private int senderId;
        private String message;
        private int messageType;
        private int readCount;
        private Object ipAddress;
        private String createdAt;
        /**
         * senderId : 1
         * id : 1
         * username : koredevman
         * name : Worachet Saengprab
         * avatar : themes/vdomax1.1/images/default-male-avatar
         * extension : png
         */

        private SenderEntity sender;

        public void setId(int id) {
            this.id = id;
        }

        public void setConversationId(int conversationId) {
            this.conversationId = conversationId;
        }

        public void setSenderId(int senderId) {
            this.senderId = senderId;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public void setIpAddress(Object ipAddress) {
            this.ipAddress = ipAddress;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setSender(SenderEntity sender) {
            this.sender = sender;
        }

        public int getId() {
            return id;
        }

        public int getConversationId() {
            return conversationId;
        }

        public int getSenderId() {
            return senderId;
        }

        public String getMessage() {
            return message;
        }

        public int getMessageType() {
            return messageType;
        }

        public int getReadCount() {
            return readCount;
        }

        public Object getIpAddress() {
            return ipAddress;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public SenderEntity getSender() {
            return sender;
        }

        public static class SenderEntity {
            private int senderId;
            private int id;
            private String username;
            private String name;
            private String avatar;
            private String extension;

            public void setSenderId(int senderId) {
                this.senderId = senderId;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public int getSenderId() {
                return senderId;
            }

            public int getId() {
                return id;
            }

            public String getUsername() {
                return username;
            }

            public String getName() {
                return name;
            }

            public String getAvatar() {
                return avatar;
            }

            public String getExtension() {
                return extension;
            }
        }
    }
}
