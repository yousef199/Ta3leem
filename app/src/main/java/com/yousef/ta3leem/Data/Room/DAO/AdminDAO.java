package com.yousef.ta3leem.Data.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yousef.ta3leem.Data.Room.Enitities.Admin;

import java.util.List;

@Dao
public interface AdminDAO {

    @Insert
    void Insert(Admin admin);

    @Update
    void Update(Admin admin);

    @Delete
    void Delete(Admin admin);

    @Query("SELECT * FROM Admin")
    LiveData<List<Admin>> getAdmins();

}
