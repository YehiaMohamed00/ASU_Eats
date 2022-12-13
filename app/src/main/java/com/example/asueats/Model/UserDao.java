package com.example.asueats.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY email ASC")
    LiveData<List<User>> getAllUsersAlphabetized();

//    @Query("SELECT * FROM user_table WHERE email=:email AND password=:password")
//    LiveData<User> getUser(String email, String password);

    @Query("SELECT * FROM user_table WHERE email=:email AND password=:password")
    User getUser(String email, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

}
