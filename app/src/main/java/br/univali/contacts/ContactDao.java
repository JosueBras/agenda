package br.univali.contacts;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> findAll();

    @Insert
    void add(Contact contact);

    @Update(entity = Contact.class)
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
