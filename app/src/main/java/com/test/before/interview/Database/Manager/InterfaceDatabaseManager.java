package com.test.before.interview.Database.Manager;


import com.test.before.interview.Database.Model.User;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public interface InterfaceDatabaseManager {

    void AddUser(List<User> data);
    List<User> ListUserSingleCondition(WhereCondition condition1);
    List<User> ListAllUser(int start, int limit);
    int CountUser();
    void ClearUser();

    void openReadableDb();
    void openWritableDb();
    void closeDbConnections();
    void dropDatabase();
}
