package pl.heng.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name ="age") val age : Int,
    @ColumnInfo(name = "sex") val sex : Byte
){
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0
}