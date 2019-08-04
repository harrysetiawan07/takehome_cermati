package com.test.before.interview.Database.Manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.test.before.interview.BuildConfig;
import com.test.before.interview.Database.Model.DaoMaster;
import com.test.before.interview.Database.Model.DaoSession;
import com.test.before.interview.Database.Model.User;
import com.test.before.interview.Database.Model.UserDao;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class DatabaseManager implements InterfaceDatabaseManager, AsyncOperationListener {

    private String DATABASE_PATH = null;

    private static DatabaseManager instance;
    private Context context;

    //DB REALTIME
    private String DATABASE_NAME = BuildConfig.BASE_DB_NAME;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private AsyncSession asyncSession;
    private List<AsyncOperation> completedOperations;

    public DatabaseManager(final Context context) {
        this.context = context;
        DatabaseGenerator();
    }

    private void DatabaseGenerator() {
        DATABASE_PATH = "/data/data/" + context.getPackageName() + "/db/";

        mHelper = new DaoMaster.DevOpenHelper(this.context, DATABASE_PATH + DATABASE_NAME, null);
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();

        database = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }
    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);
    }

    @Override
    public void closeDbConnections() {
        if (instance != null) {
            instance = null;
        }
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
        if (instance != null) {
            instance = null;
        }
    }

    @Override
    public void AddUser(List<User> data) {
        try {
            if (data != null) {
                openWritableDb();
                UserDao dDao = daoSession.getUserDao();
                dDao.insertOrReplaceInTx(data);
                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> ListUserSingleCondition(WhereCondition condition1) {
        List<User> d = null;
        try {
            openReadableDb();
            UserDao dDao = daoSession.getUserDao();
            d = dDao.queryBuilder().where(condition1).orderAsc(UserDao.Properties.Id).list();
            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (d != null) {
            return new ArrayList<>(d);
        }
        return null;
    }

    @Override
    public List<User> ListAllUser(int start, int limit) {
        List<User> d = null;
        try {
            openReadableDb();
            UserDao dDao = daoSession.getUserDao();
            d = dDao.queryBuilder().orderAsc(UserDao.Properties.CreateAt).offset(start).limit(limit).list();
            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (d != null) {
            return new ArrayList<>(d);
        }
        return null;
    }

    @Override
    public int CountUser() {
        int d = 0;
        try {
            openReadableDb();
            UserDao dDao = daoSession.getUserDao();
            d = dDao.queryBuilder().list().size();
            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public void ClearUser() {
        try {
            openWritableDb();
            UserDao dDao = daoSession.getUserDao();
            dDao.deleteAll();
            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openReadableDb() throws SQLiteException {
        database = mHelper.getReadableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    @Override
    public void openWritableDb() throws SQLiteException {
        database    = mHelper.getWritableDatabase();
        daoMaster   = new DaoMaster(database);
        daoSession  = daoMaster.newSession();
        asyncSession= daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    @Override
    public void dropDatabase() {
        try {
            openWritableDb();
            daoMaster.dropAllTables(daoSession.getDatabase(), true);
            mHelper.onCreate(database);
            asyncSession = daoSession.startAsyncSession();

            DatabaseGenerator();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
