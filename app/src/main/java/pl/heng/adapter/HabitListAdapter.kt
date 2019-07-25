package pl.heng.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.heng.R

class HabitListAdapter(val context: Context) : RecyclerView.Adapter<HabitViewHolder>() {
    override fun getItemCount(): Int {
        return 10;
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val layoutInflanter = LayoutInflater.from(context)
        return HabitViewHolder(layoutInflanter.inflate(R.layout.habit_item,parent,false))
    }

}

class HabitViewHolder(view : View) : RecyclerView.ViewHolder(view){

}