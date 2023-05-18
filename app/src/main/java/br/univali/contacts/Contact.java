package br.univali.contacts;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Contact implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo
    private String name;

    public Contact(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Comparator implements java.util.Comparator<ContactWithPhoneNumber> {
        @Override
        public int compare(ContactWithPhoneNumber a, ContactWithPhoneNumber b) {
            return a.getContact().name.compareTo(b.getContact().name);
        }
    }
}

