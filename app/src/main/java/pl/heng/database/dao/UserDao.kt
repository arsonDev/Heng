package pl.heng.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.heng.database.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getUser() : User

    @Insert
    suspend fun insertUser(user : User)

    @Delete
    suspend fun deleteUser(user: User)
}