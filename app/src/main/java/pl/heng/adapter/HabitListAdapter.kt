package pl.heng.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import pl.heng.BR
import pl.heng.database.model.Habit
import pl.heng.viewmodel.HabitsViewModel


class HabitListAdapter(layoutId : Int , viewModel : HabitsViewModel) : RecyclerView.Adapter<HabitViewHolder>() {

    private var _habits : List<Habit> = ArrayList()
    private val _layoutId = layoutId
    private val _viewModel = viewModel

    override fun getItemCount(): Int {
        return _habits.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(_viewModel,position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val layoutInflanter = LayoutInflater.from(parent.context)
        val viewDataBinding : ViewDataBinding = DataBindingUtil.inflate(layoutInflanter,viewType,parent,false)
        return HabitViewHolder(viewDataBinding)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    private fun getLayoutIdForPosition(position: Int) = _layoutId

    fun setHabits(habits : List<Habit>){
        this._habits = habits
        notifyDataSetChanged()
    }

    fun getHabit(position: Int) = _habits.get(position)
}

class HabitViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel : HabitsViewModel,position: Int){
        viewModel.getHabitAt(position)
        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.position, position)
        binding.executePendingBindings()
    }

}