package com.test.before.interview.Database.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class User {
    @Id
    long Id;
    String Username;
    String AvatarURL;
    String HtmlURL;
    int CreateAt;
    @Generated(hash = 886766663)
    public User(long Id, String Username, String AvatarURL, String HtmlURL,
            int CreateAt) {
        this.Id = Id;
        this.Username = Username;
        this.AvatarURL = AvatarURL;
        this.HtmlURL = HtmlURL;
        this.CreateAt = CreateAt;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return this.Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }
    public String getUsername() {
        return this.Username;
    }
    public void setUsername(String Username) {
        this.Username = Username;
    }
    public String getAvatarURL() {
        return this.AvatarURL;
    }
    public void setAvatarURL(String AvatarURL) {
        this.AvatarURL = AvatarURL;
    }
    public String getHtmlURL() {
        return this.HtmlURL;
    }
    public void setHtmlURL(String HtmlURL) {
        this.HtmlURL = HtmlURL;
    }
    public int getCreateAt() {
        return this.CreateAt;
    }
    public void setCreateAt(int CreateAt) {
        this.CreateAt = CreateAt;
    }
}
