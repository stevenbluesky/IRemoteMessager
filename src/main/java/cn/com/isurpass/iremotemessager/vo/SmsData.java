package cn.com.isurpass.iremotemessager.vo;

import cn.com.isurpass.iremotemessager.domain.User;

import java.util.List;

public class SmsData {
    private String language;
    private List<User> phoneusers;
    private String[][] phonenumbers;
    private List<List<String>> phonenumersList;
    private String message;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<User> getPhoneusers() {
        return phoneusers;
    }

    public void setPhoneusers(List<User> phoneusers) {
        this.phoneusers = phoneusers;
    }

    public String[][] getPhonenumbers() {
        if (phonenumbers == null && phonenumersList != null) {
            String[][] strings = new String[phonenumersList.size()][];
            for (int i = 0; i < phonenumersList.size(); i++) {
                List<String> strings1 = phonenumersList.get(i);
                String[] strings3 = strings1.toArray(new String[0]);
                strings[i] = strings3;
            }
            return strings;
        }
        return phonenumbers;
    }

    public void setPhonenumbers(String[][] phonenumbers) {
        this.phonenumbers = phonenumbers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<String>> getPhonenumersList() {
        return phonenumersList;
    }

    public void setPhonenumersList(List<List<String>> phonenumersList) {
        this.phonenumersList = phonenumersList;
    }
}
