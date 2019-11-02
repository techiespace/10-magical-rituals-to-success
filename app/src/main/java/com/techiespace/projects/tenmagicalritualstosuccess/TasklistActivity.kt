package com.techiespace.projects.tenmagicalritualstosuccess

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techiespace.projects.tenmagicalritualstosuccess.db.Habit
import kotlinx.android.synthetic.main.activity_tasklist.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TasklistActivity : AppCompatActivity() {
    private lateinit var todoViewModel: HabitsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasklist)
//        cv2.visibility = View.GONE //both work
//        cv2.isVisible = false //both work
        val textView: TextView = findViewById(R.id.textViewDate)
        val calendar = Calendar.getInstance()
        textView.text = SimpleDateFormat("MMM").format(calendar.time)
        val date = calendar.get(Calendar.DATE)
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        val year = calendar.get(Calendar.YEAR)
        val complete_date = "$date $month $year"
        textView.text = complete_date

        val adapter = todoRecyclerAdapter1()
        val adapter2 = todoRecyclerAdapter2()
        val adapter3 = todoRecyclerAdapter3()
        val adapter4 = todoRecyclerAdapter4()
        val adapter5 = todoRecyclerAdapter5()
        val adapter6 = todoRecyclerAdapter6()
        val adapter7 = todoRecyclerAdapter7()
        val adapter8 = todoRecyclerAdapter8()
        val adapter9 = todoRecyclerAdapter9()
        val adapter10 = todoRecyclerAdapter10()

        todoViewModel = ViewModelProviders.of(this).get(HabitsViewModel::class.java)

        todoViewModel.todos.observe(this, Observer { todos ->
            todos?.let {
                var ritualTodos: MutableList<Habit> = ArrayList()
                var ritualTodos2: MutableList<Habit> = ArrayList()
                var ritualTodos3: MutableList<Habit> = ArrayList()
                var ritualTodos4: MutableList<Habit> = ArrayList()
                var ritualTodos5: MutableList<Habit> = ArrayList()
                var ritualTodos6: MutableList<Habit> = ArrayList()
                var ritualTodos7: MutableList<Habit> = ArrayList()
                var ritualTodos8: MutableList<Habit> = ArrayList()
                var ritualTodos9: MutableList<Habit> = ArrayList()
                var ritualTodos10: MutableList<Habit> = ArrayList()

                for (todo in todos) {
                    if (todo.ritual_id == 1) {
                        ritualTodos.add(todo)
                    }
                    if (todo.ritual_id == 2) {
                        ritualTodos2.add(todo)
                    }
                    if (todo.ritual_id == 3) {
                        ritualTodos3.add(todo)
                    }
                    if (todo.ritual_id == 4) {
                        ritualTodos4.add(todo)
                    }
                    if (todo.ritual_id == 5) {
                        ritualTodos5.add(todo)
                    }
                    if (todo.ritual_id == 6) {
                        ritualTodos6.add(todo)
                    }
                    if (todo.ritual_id == 7) {
                        ritualTodos7.add(todo)
                    }
                    if (todo.ritual_id == 8) {
                        ritualTodos8.add(todo)
                    }
                    if (todo.ritual_id == 9) {
                        ritualTodos9.add(todo)
                    }
                    if (todo.ritual_id == 10) {
                        ritualTodos10.add(todo)
                    }
                }
//                ritualTodos.let { it1 -> adapter2.setTodos(it1) }
                adapter.setTodos(ritualTodos)
                adapter2.setTodos(ritualTodos2)
                adapter3.setTodos(ritualTodos3)
                adapter4.setTodos(ritualTodos4)
                adapter5.setTodos(ritualTodos5)
                adapter6.setTodos(ritualTodos6)
                adapter7.setTodos(ritualTodos7)
                adapter8.setTodos(ritualTodos8)
                adapter9.setTodos(ritualTodos9)
                adapter10.setTodos(ritualTodos10)
            }
        })

        todoViewModel.lockedRituals.observe(this, Observer { lockedRituals ->
            lockedRituals?.let {
                for (ritual in lockedRituals) {
                    when (ritual) {
                        2 -> cv2.isVisible = false
                        3 -> cv3.isVisible = false
                        4 -> cv4.isVisible = false
                        5 -> cv5.isVisible = false
                        6 -> cv6.isVisible = false
                        7 -> cv7.isVisible = false
                        8 -> cv8.isVisible = false
                        9 -> cv9.isVisible = false
                        10 -> cv10.isVisible = false
                        else -> true
                    }
                }
                adapter.updateRituals()
            }
        })
    }


    private fun todoRecyclerAdapter1(): TodoRecyclerAdapter {
        val recyclerView = rvTodos
        val adapter = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        return adapter
    }

    private fun todoRecyclerAdapter2(): TodoRecyclerAdapter {
        val recyclerView2 = rvTodos2
        val adapter2 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(this)
        return adapter2
    }

    private fun todoRecyclerAdapter3(): TodoRecyclerAdapter {
        val recyclerView3 = rvTodos3
        val adapter3 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView3.adapter = adapter3
        recyclerView3.layoutManager = LinearLayoutManager(this)
        return adapter3
    }

    private fun todoRecyclerAdapter4(): TodoRecyclerAdapter {
        val recyclerView4 = rvTodos4
        val adapter4 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView4.adapter = adapter4
        recyclerView4.layoutManager = LinearLayoutManager(this)
        return adapter4
    }

    private fun todoRecyclerAdapter5(): TodoRecyclerAdapter {
        val recyclerView5 = rvTodos5
        val adapter5 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView5.adapter = adapter5
        recyclerView5.layoutManager = LinearLayoutManager(this)
        return adapter5
    }

    private fun todoRecyclerAdapter6(): TodoRecyclerAdapter {
        val recyclerView6 = rvTodos6
        val adapter6 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView6.adapter = adapter6
        recyclerView6.layoutManager = LinearLayoutManager(this)
        return adapter6
    }

    private fun todoRecyclerAdapter7(): TodoRecyclerAdapter {
        val recyclerView7 = rvTodos7
        val adapter7 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView7.adapter = adapter7
        recyclerView7.layoutManager = LinearLayoutManager(this)
        return adapter7
    }

    private fun todoRecyclerAdapter8(): TodoRecyclerAdapter {
        val recyclerView8 = rvTodos8
        val adapter8 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView8.adapter = adapter8
        recyclerView8.layoutManager = LinearLayoutManager(this)
        return adapter8
    }

    private fun todoRecyclerAdapter9(): TodoRecyclerAdapter {
        val recyclerView9 = rvTodos9
        val adapter9 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView9.adapter = adapter9
        recyclerView9.layoutManager = LinearLayoutManager(this)
        return adapter9
    }

    private fun todoRecyclerAdapter10(): TodoRecyclerAdapter {
        val recyclerView10 = rvTodos10
        val adapter10 = TodoRecyclerAdapter(this) { todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView10.adapter = adapter10
        recyclerView10.layoutManager = LinearLayoutManager(this)
        return adapter10
    }
}
