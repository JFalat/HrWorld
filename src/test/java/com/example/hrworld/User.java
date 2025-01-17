package com.example.hrworld;

public class User {
    private String passwordValue;
    private String repeatedPasswordValue;
    private String name;
    private String lastname;
    private String mail;
    private String telephone;
    private String address1;
    private String address2;
    private String town;
    private String state;
    private String cityCode;
    private String country;
    private String favouriteCategory;
    private String languageSelector;
    private boolean enable_MyList;
    private boolean enable_MyBanner;

    // Konstruktor
    public User(String passwordValue, String repeatedPasswordValue, String name, String lastname,
                String mail, String telephone, String address1, String address2,
                String town, String state, String cityCode, String country, String favouriteCategory,
                String languageSelector, boolean enable_MyList, boolean enable_MyBanner) {
        this.passwordValue = passwordValue;
        this.repeatedPasswordValue = repeatedPasswordValue;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.telephone = telephone;
        this.address1 = address1;
        this.address2 = address2;
        this.town = town;
        this.state = state;
        this.cityCode = cityCode;
        this.country = country;
        this.favouriteCategory = favouriteCategory;
        this.languageSelector = languageSelector;
        this.enable_MyList = enable_MyList;
        this.enable_MyBanner = enable_MyBanner;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public String getRepeatedPasswordValue() {
        return repeatedPasswordValue;
    }

    public void setRepeatedPasswordValue(String repeatedPasswordValue) {
        this.repeatedPasswordValue = repeatedPasswordValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFavouriteCategory() {
        return favouriteCategory;
    }

    public void setFavouriteCategory(String favouriteCategory) {
        this.favouriteCategory = favouriteCategory;
    }

    public String getLanguageSelector() {
        return languageSelector;
    }

    public void setLanguageSelector(String languageSelector) {
        this.languageSelector = languageSelector;
    }

    public boolean isEnable_MyList() {
        return enable_MyList;
    }

    public void setEnable_MyList(boolean enable_MyList) {
        this.enable_MyList = enable_MyList;
    }

    public boolean isEnable_MyBanner(){
        return enable_MyList;
    }

    public void setEnable_MyBanner(boolean enable_MyBanner) {
        this.enable_MyList = enable_MyList;
    }
}
