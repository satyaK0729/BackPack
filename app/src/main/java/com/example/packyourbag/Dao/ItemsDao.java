package com.example.packyourbag.Dao;

import static androidx.room.OnConflictStrategy.REPLACE;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.packyourbag.Models.Items;

import java.util.List;

@Dao
public interface ItemsDao {
    @Insert (onConflict = REPLACE) //onConflict: if we found a duplicate data, then we will replace it
    void saveItem(Items items); //used to save items in the data base

    //we need multiple queries inside this one, we will create now.

    @Query("select * from items where category =:category order by id asc")
    List<Items> getAll(String category);    //This is used to show all the items

    @Delete
    void delete(Items items);   //Used to delete the items

    @Query("update items set checked=:checked where ID=:id")
    void checkUncheck(int id, boolean checked); //used to check or uncheck a task

    @Query("select count(*) from items")
    Integer getItemsCount(); //used to count the data

    @Query("delete from items where addedby=:addedBy")
    Integer deleteAllSystemsItems(String addedBy);   //used to delete the default system tasks

    @Query("delete from items where category=:category")
    Integer deleteAllByCategory(String category);   //used to delete tasks based on the category

    @Query("delete from items where category=:category and addedby=:addedBy")
    Integer deleteAllByCategoryAndAddedBy(String category, String addedBy); //used to delete all the default system given tasks based on category

    @Query("select * from items where checked=:checked order by id asc")
    List<Items> getAllSelected(Boolean checked);    //get all the selected items in the ascending order from all categories
}
