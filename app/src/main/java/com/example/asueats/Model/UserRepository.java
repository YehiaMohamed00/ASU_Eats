package com.example.asueats.Model;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public UserRepository(Application application) {
        ProfileRoomDatabase db = ProfileRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllUsersAlphabetized();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

//    public LiveData<User> getUser(String email) {
//        return mUserDao.getUser(email);
//    }

    public User getUser(String email, String password) {
        return mUserDao.getUser(email, password);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(User user) {
        ProfileRoomDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insertUser(user);
        });
    }

    public void deleteAll(){
        ProfileRoomDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.deleteAll();
        });
    }
}