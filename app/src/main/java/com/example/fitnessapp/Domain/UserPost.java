package com.example.fitnessapp.Domain;

public class UserPost {

    String userName;



    String profilePic;
    String postUrl;
    String userId;
    String desc;



    public UserPost(String userName, String profilePic, String postUrl, String userId, String desc) {
        this.userName = userName;
        this.profilePic = profilePic;
        this.postUrl = postUrl;
        this.userId = userId;
        this.desc = desc;
    }

    public UserPost(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
