package pl.heng.database.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import pl.heng.database.dao.UserDao
import pl.heng.database.model.User

class UserRepository(private val userDao: UserDao) {
    val user : User = userDao.getUser()

    @WorkerThread
    suspend fun insert(user : User){
        userDao.insertUser(user)
    }
}