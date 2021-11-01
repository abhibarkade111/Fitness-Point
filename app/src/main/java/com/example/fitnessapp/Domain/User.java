package com.example.fitnessapp.Domain;

public class User {
    String profilepic;
    String userName;
    String mail;
    String password;
    String userId;
    String fname;
    String lname;
    String phone;
    String gender;
    float height, weight,age;

    public User( String userName, String mail, String password,String profilepic, String fname, String lname, String phone, float height, float weight, float age, String gender) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;

        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender=gender;
    }


    public  User(){}

    //Sign up constructor
    public User(String userName, String mail, String password) {

        this.userName = userName;
        this.mail = mail;
        this.password = password;

    }


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String laname) {
        this.lname = laname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }



}
