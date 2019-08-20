package pl.heng.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.zagum.switchicon.SwitchIconView
import kotlinx.android.synthetic.main.habit_item.view.*
import pl.heng.R
import pl.heng.database.model.Habit

class HabitListAdapter(val context: Context) : RecyclerView.Adapter<HabitViewHolder>() {
    private var habits : List<Habit> = ArrayList()

    override fun getItemCount(): Int {
        return habits.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.habitName.text = habits[position].name
        for (i in 0 until habits[position].countOfWeek step 1){
            holder.doneView.addView(createDoneTick())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val layoutInflanter = LayoutInflater.from(context)
        return HabitViewHolder(layoutInflanter.inflate(R.layout.habit_item,parent,false))
    }

    fun setHabits(habits : List<Habit>){
        this.habits = habits
        notifyDataSetChanged()
    }

    private fun createDoneTick() : SwitchIconView {
        val icon = SwitchIconView(context)
        icon.background = this.context.getDrawable(R.drawable.ok)
        return icon
    }
}

class HabitViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val habitName = view.habitName
    val doneView = view.doneView
}