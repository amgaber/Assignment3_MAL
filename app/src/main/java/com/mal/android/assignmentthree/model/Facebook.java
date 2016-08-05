package com.mal.android.assignmentthree.model;

/**
 * Created by toshiba1 on 8/4/2016.
 */
public class Facebook {

    String comments;
    String likes;
    String postImage;
    String postText;
    String postTime;
    Integer postType;
    String shares;
    String userName;
    String userPic;

    public Facebook() {
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public Facebook(String comments, String postImage, String likes, Integer postType, String postTime, String postText, String userName, String shares, String userPic) {
        this.comments = comments;
        this.postImage = postImage;
        this.likes = likes;
        this.postType = postType;
        this.postTime = postTime;
        this.postText = postText;
        this.userName = userName;
        this.shares = shares;
        this.userPic = userPic;
    }



}
