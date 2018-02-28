package com.example.christian.barangaybalibagostudentinformationsystem;

/**
 * Created by Christian on 26/02/2018.
 */

public class Student {
    private int id;


    private String username;
    private String password;
    private String fullname;
    private String dateOfBirth;
    private String placeOfBirth;
    private String citizenship;
    private String comelecNo;
    private String dateIssued;
    private byte[] image;

    public Student(int id, String fullname, String dateOfBirth, String placeOfBirth, String citizenship, String comelecNo, String dateIssued, byte[] image, String username, String password) {
        this.id = id;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.citizenship = citizenship;
        this.comelecNo = comelecNo;
        this.dateIssued = dateIssued;
        this.image = image;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getComelecNo() {
        return comelecNo;
    }

    public void setComelecNo(String comelecNo) {
        this.comelecNo = comelecNo;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
