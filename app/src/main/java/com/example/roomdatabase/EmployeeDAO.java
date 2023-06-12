package com.example.roomdatabase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmploy(EmployeeEntity employee);
    @Query("SELECT * FROM EmployeeEntity")
    public List<EmployeeEntity> findAllEmploySync();
}
