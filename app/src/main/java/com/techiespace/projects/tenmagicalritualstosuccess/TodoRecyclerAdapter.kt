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

class TodoRecyclerAdapter(context: Context, val onClick: (Habit, Boolean) -> Unit) :
    RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var todos = emptyList<Habit>()
    val calendar = Calendar.getInstance()
    val date = calendar.get(Calendar.DATE)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    val year = calendar.get(Calendar.YEAR)
    val complete_date = "$date $month $year"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = inflater.inflate(R.layout.tasklist_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindTodo(todos[position])
        holder.itemView.cbDone.setOnClickListener {
            onClick(todos[position], (it as CompoundButton).isChecked)
        }
    }

    fun setTodos(todos: List<Habit>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    fun updateRituals() {
        notifyDataSetChanged()
    }


    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTodo(todo: Habit) {
            with(todo) {
                itemView.tvTodo.text = title
                itemView.cbDone.isChecked = complete_date in timestamps
//                itemView.cbDone.isChecked = done
            }
        }

    }
}