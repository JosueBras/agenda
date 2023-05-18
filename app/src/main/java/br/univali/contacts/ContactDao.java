package br.univali.contacts;

import androidx.room.*;

import java.util.List;

@Dao
public interface ContactDao {
    @Transaction
    @Query("SELECT * FROM Contact")
    List<ContactWithPhoneNumber> findAll();

    @Transaction
    @Query("SELECT * FROM Contact WHERE id = :id")
    ContactWithPhoneNumber findById(String id);

    @Transaction
    @Query("SELECT * from PhoneNumber WHERE id = :id")
    PhoneNumber findPhoneNumberById(int id);

    @Insert
    void add(Contact contact);

    @Insert
    void addPhoneNumber(PhoneNumber number);

    @Update(entity = Contact.class)
    void update(Contact contact);

    @Update(entity = PhoneNumber.class)
    void updatePhoneNumber(PhoneNumber number);

    @Delete
    void delete(Contact contact);

    @Delete
    void deletePhoneNumber(PhoneNumber number);
}
