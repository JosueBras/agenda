package br.univali.contacts;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class PhoneNumber implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String contactId;

    @ColumnInfo
    private String number;

    public PhoneNumber() {
    }

    public PhoneNumber(Contact contact, String number) {
        this.contactId = contact.getId();
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
