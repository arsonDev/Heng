package pl.heng.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.habit_item.view.*
import pl.heng.R
import pl.heng.database.model.Habit

class HabitListAdapter(val context: Context,val list : List<Habit>) : RecyclerView.Adapter<HabitViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.habitName.text = list[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val layoutInflanter = LayoutInflater.from(context)
        return HabitViewHolder(layoutInflanter.inflate(R.layout.habit_item,parent,false))
    }

}

class HabitViewHolder(view : View) : RecyclerView.ViewHolder(view){
    val habitName = view.habitName
}