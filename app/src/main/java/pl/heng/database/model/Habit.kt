package pl.heng.database.model

import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "category") val category : String,
    @ColumnInfo(name = "finalDate") val finalDate : String,
    @ColumnInfo(name = "remindHour") val remindHour : String,
    @ColumnInfo(name = "createDate") val createDate : String
 ) : BaseObservable(){
    @PrimaryKey(autoGenerate = true) var uid : Int = 0

}