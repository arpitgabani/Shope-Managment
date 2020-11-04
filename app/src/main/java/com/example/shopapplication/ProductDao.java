package com.example.shopapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface ProductDao {

    @Query("SELECT * FROM product ORDER BY id desc")
    List<Product> getAllProducts();

    @Insert
    void insert(Product product);

    @Delete
    public void deleteProduct(Product product);


}
