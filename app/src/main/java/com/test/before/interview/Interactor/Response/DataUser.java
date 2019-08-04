package com.test.before.interview.Interactor.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harry_setiawan on 15/06/2018.
 */

public class DataUser {
    @SerializedName("id") private Long Id;
    @SerializedName("login") private String Username;
    @SerializedName("avatar_url") private String AvatarUrl;
    @SerializedName("html_url") private String HTMLUrl;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        AvatarUrl = avatarUrl;
    }

    public String getHTMLUrl() {
        return HTMLUrl;
    }

    public void setHTMLUrl(String HTMLUrl) {
        this.HTMLUrl = HTMLUrl;
    }
}
