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

    @ColumnInfo(name="first name")
    private String firstName;

    @ColumnInfo(name="last name")
    private String lastName;

    @ColumnInfo(name="address")
    private String address;

    String defaultValue = "Not Set";

    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
        this.firstName = defaultValue;
        this.lastName = defaultValue;
        this.address = defaultValue;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }
}

