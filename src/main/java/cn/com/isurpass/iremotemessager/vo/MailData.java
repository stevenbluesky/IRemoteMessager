package cn.com.isurpass.iremotemessager.vo;

import cn.com.isurpass.iremotemessager.domain.User;

import java.util.ArrayList;
import java.util.List;

public class MailData {
    private String language;
    private List<User> phoneusers;
    private String[] mails;
    private List<String> mailsList;
    private String mailtitle;
    private String mailcontent;

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

    public String[] getMails() {
        if ( mails == null && mailsList != null )
            return mailsList.toArray(new String[0]);
        return mails;
    }

    public void setMails(String[] mails) {
        this.mails = mails;
    }

    public String getMailtitle() {
        return mailtitle;
    }

    public void setMailtitle(String mailtitle) {
        this.mailtitle = mailtitle;
    }

    public String getMailcontent() {
        return mailcontent;
    }

    public void setMailcontent(String mailcontent) {
        this.mailcontent = mailcontent;
    }

    public List<String> getMailsList() {
        return mailsList;
    }

    public void setMailsList(List<String> mailsList) {
        this.mailsList = mailsList;
    }
}
