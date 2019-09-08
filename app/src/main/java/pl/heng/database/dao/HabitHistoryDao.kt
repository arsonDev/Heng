package pl.heng.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.heng.database.model.HabitDoneHistory

@Dao
interface HabitHistoryDao {
    @Query("SELECT * FROM habitdonehistory ORDER BY creationDate DESC")
    suspend fun getHabitHistoryList() : List<HabitDoneHistory>

    @Query("SELECT COUNT(*) FROM habitdonehistory WHERE habitId = :HabitId")
    suspend fun getHabitDoneCount(HabitId : Int) : Int

    @Query("SELECT * FROM habitdonehistory WHERE id = :habitDoneId")
    suspend fun getHabitDoneHistory(habitDoneId: Int)  : HabitDoneHistory

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM habitdonehistory WHERE habitId = :habitId AND creationDate = :date")
    suspend fun getTodayDone(habitId : Int, date : String) : Int

    @Insert
    suspend fun insertHabitDone(habitDone : HabitDoneHistory)

    @Query("DELETE FROM habitdonehistory WHERE id = :id")
    suspend fun deleteHabitDone(id : Int)
}