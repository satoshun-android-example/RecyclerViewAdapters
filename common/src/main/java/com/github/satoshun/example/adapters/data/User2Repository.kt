package com.github.satoshun.example.adapters.data

import androidx.paging.DataSource
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "users2")
data class User2(
  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid") var id: Long? = null,
  val name: String
)

@Database(
    entities = [
      User2::class
    ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
  abstract fun author(): User2Dao
}

@Dao
interface User2Dao {
  @Insert
  fun insert(author: User2): Long

  @Query("select * FROM users2")
  fun getAuthors(): DataSource.Factory<Int, User2>
}
