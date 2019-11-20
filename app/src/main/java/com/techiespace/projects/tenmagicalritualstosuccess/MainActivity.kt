package com.techiespace.projects.tenmagicalritualstosuccess

import android.animation.ArgbEvaluator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.techiespace.projects.tenmagicalritualstosuccess.viewmodels.HabitsViewModel
import com.techiespace.projects.tenmagicalritualstosuccess.viewmodels.RitualsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var mainCardAdapter: MainCardAdapter
    var argbEvaluator = ArgbEvaluator()
    private lateinit var ritualsViewModel: RitualsViewModel
    private var activeRitualIds: List<Int> = mutableListOf(-1)
    private var habitCounts: MutableList<Int> = mutableListOf(-1)
    var totalActiveHabits = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val job = Job()
        val scopeMainThread = CoroutineScope(job + Dispatchers.Main)

        val tasklistButton = findViewById<Button>(R.id.btnTasklist)
        tasklistButton.setOnClickListener {
            this.startActivity(
                Intent(
                    this,
                    TasklistActivity::class.java
                )
            )
        }

        var mainCards = mutableListOf(
            MainCard(R.drawable.ritual1, "", ""),
            MainCard(R.drawable.lock, "", ""),
            MainCard(R.drawable.lock, "", ""),
            MainCard(R.drawable.lock, "", ""),
            MainCard(R.drawable.lock, "", ""),
            MainCard(R.drawable.lock, "", ""),
            MainCard(R.drawable.lock, "", ""),
            MainCard(R.drawable.lock, "", ""),
            MainCard(R.drawable.lock, "", ""),
            MainCard(R.drawable.lock, "", "")
        )

        ritualsViewModel = ViewModelProvider(this).get(RitualsViewModel::class.java)
        val todoViewModel = ViewModelProviders.of(this).get(HabitsViewModel::class.java)

        ritualsViewModel.activeRitualIds.observe(
            this,
            androidx.lifecycle.Observer { activeRitualIdsLocal ->
                activeRitualIds = activeRitualIdsLocal
                todoViewModel.dummyHabitToggle()    //hack to force run todoViewModel observer TODO: Find a better approach
            })
        scopeMainThread.launch {
            //this slows down the scrolling a lot
            for (i in 1..9) {
                habitCounts.add(i, todoViewModel.getActiveHabitsCount(i + 1))
            }
            todoViewModel.dummyHabitToggle()
        }
        todoViewModel.todos.observe(this, androidx.lifecycle.Observer { allHabits ->
            totalActiveHabits = 0
            for (ritual_id in activeRitualIds) {
                for (habit in allHabits) {
                    if (ritual_id == habit.ritual_id) {
                        totalActiveHabits++
                    }
                }
            }
            displayCalendar(todoViewModel, totalActiveHabits)
            //TODO: Delete timestamps older than 20 days/ one month
            val cal = Calendar.getInstance()
            var consistentDates = ""

            cal.add(Calendar.DATE, -7)
            for (i in 1..7) {   //consecutive dates for the last 7 days
                consistentDates += "${cal.get(Calendar.DATE)} ${cal.getDisplayName(
                    Calendar.MONTH,
                    Calendar.LONG,
                    Locale.getDefault()
                )} ${cal.get(Calendar.YEAR)}|"
                cal.add(Calendar.DATE, 1)
            }
            var consistencyCount1 = 0
            var consistencyCount2 = 0
            var consistencyCount3 = 0
            var consistencyCount4 = 0
            var consistencyCount5 = 0
            var consistencyCount6 = 0
            var consistencyCount7 = 0
            var consistencyCount8 = 0
            var consistencyCount9 = 0
            for (habit in allHabits) {
                if (habit.ritual_id == 1) {//if all habits with ritual_id 1 are followed for past 7 days, unlock next ritual
                    if (consistentDates in habit.timestamps) {
                        consistencyCount1++
                    }
                }
                if (habit.ritual_id == 2) {
                    if (consistentDates in habit.timestamps) {
                        consistencyCount2++
                    }
                }
                if (habit.ritual_id == 3) {
                    if (consistentDates in habit.timestamps) {
                        consistencyCount3++
                    }
                }
                if (habit.ritual_id == 4) {
                    if (consistentDates in habit.timestamps) {
                        consistencyCount4++
                    }
                }
                if (habit.ritual_id == 5) {
                    if (consistentDates in habit.timestamps) {
                        consistencyCount5++
                    }
                }
                if (habit.ritual_id == 6) {
                    if (consistentDates in habit.timestamps) {
                        consistencyCount6++
                    }
                }
                if (habit.ritual_id == 7) {
                    if (consistentDates in habit.timestamps) {
                        consistencyCount7++
                    }
                }
                if (habit.ritual_id == 8) {
                    if (consistentDates in habit.timestamps) {
                        consistencyCount8++
                    }
                }
                if (habit.ritual_id == 9) {
                    if (consistentDates in habit.timestamps) {
                        consistencyCount9++
                    }
                }
                if (habitCounts.size == 10) {
                    if (consistencyCount1 == habitCounts[1] && habit.ritual_id == 1)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    if (consistencyCount2 == habitCounts[2] && habit.ritual_id == 2)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    if (consistencyCount3 == habitCounts[3] && habit.ritual_id == 3)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    if (consistencyCount4 == habitCounts[4] && habit.ritual_id == 4)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    if (consistencyCount5 == habitCounts[5] && habit.ritual_id == 5)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    if (consistencyCount6 == habitCounts[6] && habit.ritual_id == 6)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    if (consistencyCount7 == habitCounts[7] && habit.ritual_id == 7)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    if (consistencyCount8 == habitCounts[8] && habit.ritual_id == 8)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    if (consistencyCount9 == habitCounts[9] && habit.ritual_id == 9)
                        ritualsViewModel.unlock(habit.ritual_id + 1)
                    Log.e(
                        "Consistency counts: ",
                        consistentDates + " " + consistencyCount1 + " " + habit.timestamps + " " + habitCounts.toString()
                    )
                }
            }
        })

        ritualsViewModel.allRituals.observe(this, androidx.lifecycle.Observer { allWords ->
            for (i in allWords.indices) { //size should be always 10
                if (allWords[i].locked)
                    mainCards[i] = MainCard(
                        R.drawable.lock,
                        "Follow the previous ritual for a week to unlock this secret",
                        ""
                    )
            }
            if (allWords.size == 10) {
                if (!allWords[0].locked)
                    mainCards[0] = MainCard(R.drawable.ritual1, allWords[0].title, allWords[0].desc)
                if (!allWords[1].locked)
                    mainCards[1] = MainCard(R.drawable.ritual2, allWords[1].title, allWords[1].desc)
                if (!allWords[2].locked)
                    mainCards[2] = MainCard(R.drawable.ritual3, allWords[2].title, allWords[2].desc)
                if (!allWords[3].locked)
                    mainCards[3] = MainCard(R.drawable.ritual4, allWords[3].title, allWords[3].desc)
                if (!allWords[4].locked)
                    mainCards[4] = MainCard(R.drawable.ritual5, allWords[4].title, allWords[4].desc)
                if (!allWords[5].locked)
                    mainCards[5] = MainCard(R.drawable.ritual6, allWords[5].title, allWords[5].desc)
                if (!allWords[6].locked)
                    mainCards[6] = MainCard(R.drawable.ritual7, allWords[6].title, allWords[6].desc)
                if (!allWords[7].locked)
                    mainCards[7] = MainCard(R.drawable.ritual8, allWords[7].title, allWords[7].desc)
                if (!allWords[8].locked)
                    mainCards[8] = MainCard(R.drawable.ritual9, allWords[8].title, allWords[8].desc)
                if (!allWords[9].locked)
                    mainCards[9] =
                        MainCard(R.drawable.ritual10, allWords[9].title, allWords[9].desc)
            }
//            mainCardAdapter = MainCardAdapter(mainCards, this)
//            viewPager = findViewById(R.id.viewPager)
//            viewPager.adapter = mainCardAdapter
//            viewPager.setPadding(130, 0, 130, 0)
//            viewPager.setPageTransformer(false, fragmentCardShadowTransformer)
//            viewPager.offscreenPageLimit = 3
        })

        mainCardAdapter = MainCardAdapter(mainCards, this)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = mainCardAdapter
        viewPager.setPadding(130, 0, 130, 0)

        val colors = listOf(
            Integer.valueOf(resources.getColor(R.color.color1)),
            Integer.valueOf(resources.getColor(R.color.color2)),
            Integer.valueOf(resources.getColor(R.color.color3)),
            Integer.valueOf(resources.getColor(R.color.color4))
        )

        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            /*fun enableScaling(enable: Boolean) {
                if (scalingEnabled && !enable) {
                    // shrink main card
                    val currentCard:CardView = mainCardAdapter.getCardViewAt(viewPager.currentItem)
                    if (currentCard != null) {
                        currentCard!!.animate().scaleY(1f)
                        currentCard!!.animate().scaleX(1f)
                    }
                } else if (!scalingEnabled && enable) {
                    // grow main card
                    val currentCard = mainCardAdapter.getCardViewAt(viewPager.currentItem)
                    if (currentCard != null) {
                        //enlarge the current item
                        currentCard!!.animate().scaleY(1.1f)
                        currentCard!!.animate().scaleX(1.1f)
                    }
                }
                scalingEnabled = enable
            }*/

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < (mainCardAdapter.count - 1) && position < colors.size - 1) {
                    viewPager.setBackgroundColor(

                        argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1]
                        ) as Int
                    )
                } else {
                    viewPager.setBackgroundColor(colors[colors.size - 1])
                }
            }

            override fun onPageSelected(position: Int) {
            }
        })

        displayCalendar(todoViewModel, totalActiveHabits)
    }

    private fun displayCalendar(todoViewModel: HabitsViewModel, totalActiveHabits: Int) {
        val calendar = Calendar.getInstance()
        today.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub1.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub2.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub3.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub4.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub5.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub6.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub7.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub8.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub9.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub10.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub11.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub12.text = calendar.get(Calendar.DATE).toString()
        calendar.add(Calendar.DATE, -1)
        today_sub13.text = calendar.get(Calendar.DATE).toString()

        val cal = Calendar.getInstance()
        var date = cal.get(Calendar.DATE)
        var month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        var year = cal.get(Calendar.YEAR)
        var complete_date = "$date $month $year"
        todoViewModel.todos.observe(this, androidx.lifecycle.Observer { todos ->
            todos?.let {
                val totalCount: Int = if (totalActiveHabits == 0) todos.size else totalActiveHabits
                var todayCount = 0
                var todayCountMin1 = 0
                var todayCountMin2 = 0
                var todayCountMin3 = 0
                var todayCountMin4 = 0
                var todayCountMin5 = 0
                var todayCountMin6 = 0
                var todayCountMin7 = 0
                var todayCountMin8 = 0
                var todayCountMin9 = 0
                var todayCountMin10 = 0
                var todayCountMin11 = 0
                var todayCountMin12 = 0
                var todayCountMin13 = 0

                for (todo in todos) {
                    //val cal= Calendar.getInstance()
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCount++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin1++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin2++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin3++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin4++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin5++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin6++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin7++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin8++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin9++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin10++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin11++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin12++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps.split("|")) {
                        todayCountMin13++
                    }
                    cal.add(Calendar.DATE, 13)
                }
                if (totalCount != 0) {
                    today.setBackgroundColor(Color.GREEN)
                    today.background.alpha = (todayCount * 100 / totalCount)
                    today_sub1.setBackgroundColor(Color.GREEN)
                    today_sub1.background.alpha = (todayCountMin1 * 100 / totalCount)
                    today_sub2.setBackgroundColor(Color.GREEN)
                    today_sub2.background.alpha = (todayCountMin2 * 100 / totalCount)
                    today_sub3.setBackgroundColor(Color.GREEN)
                    today_sub3.background.alpha = (todayCountMin3 * 100 / totalCount)
                    today_sub4.setBackgroundColor(Color.GREEN)
                    today_sub4.background.alpha = (todayCountMin4 * 100 / totalCount)
                    today_sub5.setBackgroundColor(Color.GREEN)
                    today_sub5.background.alpha = (todayCountMin5 * 100 / totalCount)
                    today_sub6.setBackgroundColor(Color.GREEN)
                    today_sub6.background.alpha = (todayCountMin6 * 100 / totalCount)
                    today_sub7.setBackgroundColor(Color.GREEN)
                    today_sub7.background.alpha = (todayCountMin7 * 100 / totalCount)
                    today_sub8.setBackgroundColor(Color.GREEN)
                    today_sub8.background.alpha = (todayCountMin8 * 100 / totalCount)
                    today_sub9.setBackgroundColor(Color.GREEN)
                    today_sub9.background.alpha = (todayCountMin9 * 100 / totalCount)
                    today_sub10.setBackgroundColor(Color.GREEN)
                    today_sub10.background.alpha = (todayCountMin10 * 100 / totalCount)
                    today_sub11.setBackgroundColor(Color.GREEN)
                    today_sub11.background.alpha = (todayCountMin11 * 100 / totalCount)
                    today_sub12.setBackgroundColor(Color.GREEN)
                    today_sub12.background.alpha = (todayCountMin12 * 100 / totalCount)
                    today_sub13.setBackgroundColor(Color.GREEN)
                    today_sub13.background.alpha = (todayCountMin13 * 100 / totalCount)
                }

            }
        })
    }
}
