package com.techiespace.projects.tenmagicalritualstosuccess

import android.animation.ArgbEvaluator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: Adapter
    var argbEvaluator = ArgbEvaluator()
    private lateinit var wordViewModel: RitualsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tasklistButton = findViewById<Button>(R.id.btnTasklist)
        tasklistButton.setOnClickListener {
            this.startActivity(
                Intent(
                    this,
                    TasklistActivity::class.java
                )
            )
        }

        val mainCards = listOf(
            MainCard(
                R.drawable.brochure,
                "Believe",
                "Dream big and give give it everything you got."
            ),
            MainCard(
                R.drawable.sticker,
                "Faith",
                "Faith is the fire that fuels the dream, that powers the engine that turns the world."
            ),
            MainCard(R.drawable.poster, "Plan", "Make a damn schedule"),
            MainCard(R.drawable.namecard, "Action", "What gets you out of bed everyday"),
            MainCard(R.drawable.namecard, "Reflect", "How was it? What could you have done better?")
        )

        wordViewModel = ViewModelProvider(this).get(RitualsViewModel::class.java)
        val todoViewModel = ViewModelProviders.of(this).get(HabitsViewModel::class.java)

        adapter = Adapter(mainCards, this)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        viewPager.setPadding(130, 0, 130, 0)

        val colors = listOf(
            Integer.valueOf(resources.getColor(R.color.color1)),
            Integer.valueOf(resources.getColor(R.color.color2)),
            Integer.valueOf(resources.getColor(R.color.color3)),
            Integer.valueOf(resources.getColor(R.color.color4))
        )

        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < (adapter.count - 1) && position < colors.size - 1) {
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

        displayCalendar(todoViewModel)
    }

    private fun displayCalendar(todoViewModel: HabitsViewModel) {
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
                val totalCount: Int = todos.size
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
                    if (complete_date in todo.timestamps) {
                        todayCount++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin1++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin2++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin3++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin4++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin5++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin6++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin7++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin8++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin9++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin10++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin11++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
                        todayCountMin12++
                    }
                    cal.add(Calendar.DATE, -1)
                    date = cal.get(Calendar.DATE)
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    year = cal.get(Calendar.YEAR)
                    complete_date = "$date $month $year"
                    if (complete_date in todo.timestamps) {
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
