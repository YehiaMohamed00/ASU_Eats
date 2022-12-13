package com.example.asueats.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="user_table")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="password")
    private String password;

    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return password;
    }

}

