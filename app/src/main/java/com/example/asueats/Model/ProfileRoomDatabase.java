package com.example.asueats.Model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class ProfileRoomDatabase extends RoomDatabase {

    static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public abstract UserDao userDao();

    private static volatile ProfileRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ProfileRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProfileRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ProfileRoomDatabase.class, "user_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    initializeInFirebase();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more users, just add them.
                Log.d("yehiaDebug","reached the database");
                UserDao dao = INSTANCE.userDao();
                dao.deleteAll();
                Log.d("yehiaDebug","reached insert");
                dao.insertUser(new User("admin@admin.com", "admin_admin"));
                Log.d("yehiaDebug","reached inserted");
            });
        }
    };

    public static void initializeInFirebase(){
                // baseUser is made for testing purposes
                mAuth.createUserWithEmailAndPassword("admin@admin.com", "admin_admin").addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Log.d("yehiaDebug", "registered successfully");
                    }else{

                        Log.d("yehiaDebug", "registration error");
//                            Log.d("yehiaDebug", "registration error" + task.getException().getMessage());
                    }
                });
    }

}