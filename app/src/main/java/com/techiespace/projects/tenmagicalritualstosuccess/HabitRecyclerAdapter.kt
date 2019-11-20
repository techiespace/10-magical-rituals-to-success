package com.techiespace.projects.tenmagicalritualstosuccess

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.techiespace.projects.tenmagicalritualstosuccess.db.Habit
import kotlinx.android.synthetic.main.tasklist_item.view.*
import java.util.*

class HabitRecyclerAdapter(context: Context, val onClick: (Habit, Boolean) -> Unit) :
    RecyclerView.Adapter<HabitRecyclerAdapter.TodoViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var habits = emptyList<Habit>()
    private val calendar = Calendar.getInstance()
    private val date = calendar.get(Calendar.DATE)
    private val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    private val year = calendar.get(Calendar.YEAR)
    val completeDate = "$date $month $year"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = inflater.inflate(R.layout.tasklist_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun getItemCount() = habits.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindTodo(habits[position])
        holder.itemView.cbDone.setOnClickListener {
            onClick(habits[position], (it as CompoundButton).isChecked)
        }
    }

    fun setTodos(todos: List<Habit>) {
        this.habits = todos
        notifyDataSetChanged()
    }

    fun updateRituals() {
        notifyDataSetChanged()
    }


    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTodo(todo: Habit) {
            with(todo) {
                itemView.textViewHabit.text = title
                itemView.cbDone.isChecked =
                    completeDate in timestamps.split("|")   //this might break if complete date is empty string due to the extra empty string generated because of trailing |
            }
        }

    }
}