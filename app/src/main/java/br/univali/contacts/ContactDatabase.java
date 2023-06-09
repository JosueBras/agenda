package br.univali.contacts;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, PhoneNumber.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase instance;

    public static ContactDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ContactDatabase.class, "contact-database").build();
            return instance;
        } else {
            return instance;
        }
    }

    public abstract ContactDao contactDao();
}
