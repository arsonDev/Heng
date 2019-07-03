package pl.heng.database.repository

import pl.heng.database.dao.UserDao
import pl.heng.database.model.User


class UserRepository(private val userDao: UserDao) {

    suspend fun getUser() : User{
        return userDao.getUser();
    }


    suspend fun insert(user : User){
        userDao.insertUser(user)
    }
}