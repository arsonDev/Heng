package pl.heng.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity
data class Habit(
    @PrimaryKey val uid : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "description") val desc : String,
    @ColumnInfo(name = "countOfWeek") val countOfWeek : Byte,
    @ColumnInfo(name = "finalDate") val finalDate : Date,
    @ColumnInfo(name = "remindCountOfDay") val remindCountOfDay : Int,
    @ColumnInfo(name = "remindHour") val remindHour : Time
 )