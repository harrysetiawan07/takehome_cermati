package com.test.before.interview.Database.Manager;

import com.test.before.interview.Apps;
import com.test.before.interview.Database.Model.User;
import com.test.before.interview.Interactor.Response.DataUser;

import java.util.ArrayList;
import java.util.List;

public class DatabaseMapper {
    public boolean mapUser(List<DataUser> users){
        int record = Apps.getInstance().getDatabase().CountUser();
        List<User> dataUsers = new ArrayList<>();
        for (int n = 0; n < users.size() ; n += 1) {
            dataUsers.add(new User(users.get(n).getId(), users.get(n).getUsername(), users.get(n).getAvatarUrl(), users.get(n).getHTMLUrl(), record));
            record += 1;
        }
        Apps.getInstance().getDatabase().AddUser(dataUsers);
        return true;
    }

}
