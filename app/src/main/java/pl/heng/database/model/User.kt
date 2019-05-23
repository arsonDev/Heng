package pl.heng.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.heng.viewmodel.PersonalViewModel

@Entity
data class User(
    @PrimaryKey val uid : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name ="age") val age : Int,
    @ColumnInfo(name = "sex") val sex : PersonalViewModel.Sex
)