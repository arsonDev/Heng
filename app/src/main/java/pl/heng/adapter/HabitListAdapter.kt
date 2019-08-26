package pl.heng.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.habit_item.view.*
import pl.heng.R.*
import pl.heng.database.model.Habit
import kotlin.random.Random


class HabitListAdapter(val context: Context) : RecyclerView.Adapter<HabitViewHolder>() {
    private var habits : List<Habit> = ArrayList()

    override fun getItemCount(): Int {
        return habits.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.habitName.text = habits[position].name
        holder.stepProgress.max = habits[position].countOfWeek
        holder.stepProgress.progress  = Random.nextInt(1,7)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val layoutInflanter = LayoutInflater.from(context)
        return HabitViewHolder(layoutInflanter.inflate(layout.habit_item,parent,false))
    }

    fun setHabits(habits : List<Habit>){
        this.habits = habits
        notifyDataSetChanged()
    }
}

class HabitViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val habitName = view.habitName
    val stepProgress = view.pb
}