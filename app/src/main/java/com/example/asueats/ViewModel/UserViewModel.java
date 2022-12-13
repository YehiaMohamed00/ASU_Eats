package com.example.asueats.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.asueats.Model.User;
import com.example.asueats.Model.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;
    private final LiveData<List<User>> mAllUsers;
    //private final LiveData<User> user;

    public UserViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
        //user = mRepository.getUser("yehia@test.com");
    }

    public LiveData<List<User>> getAllUsers() { return mAllUsers; }

//    public LiveData<User> getUser(String email, String password){
//        return mRepository.getUser(email, password);
//    }

    public User getUser(String email, String password){
        return mRepository.getUser(email, password);
    }

    public void updateInfo(String email, String firstName, String lastName, String address){
        mRepository.updateInfo(email, firstName, lastName, address);
    }

    public void updatePass(String email, String password){
        mRepository.updatePass(email, password);
    }

    public void insert(User user) { mRepository.insert(user); }

    public void deleteAll(){
        mRepository.deleteAll();
    }
}