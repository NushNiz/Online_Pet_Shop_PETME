package com.example.authenticationapp;

public class PostModel {

    String Description;
    String Category;
    String Telephonenumber;
    String Price;
    String Email;
    String image;


    public PostModel() {
    }

    public PostModel(String description, String category, String telephonenumber, String price,String email, String image) {
        Description = description;
        Category = category;
        Telephonenumber = telephonenumber;
        Price = price;
        Email=email;
        this.image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getTelephonenumber() {
        return Telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        Telephonenumber = telephonenumber;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }







}
