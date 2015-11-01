package natuan.org.androiddesigntablayout.model;

import java.util.List;

/**
 * Created by root1 on 10/31/15.
 */
public class postss {

    /**
     * status : 1
     * posts : [{"id":"1356","name":"Joe Hart","image":"https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg","url":"She\u2019s So Hungry","description":""}]
     */

    private int status;
    /**
     * id : 1356
     * name : Joe Hart
     * image : https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg
     * url : She’s So Hungry
     * description :
     */

    private List<PostsEntity> posts;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
    }

    public int getStatus() {
        return status;
    }

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public static class PostsEntity {
        private String id;
        private String name;
        private String image;
        private String url;
        private String description;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public String getUrl() {
            return url;
        }

        public String getDescription() {
            return description;
        }
    }
}
