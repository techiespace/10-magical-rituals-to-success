package com.techiespace.projects.tenmagicalritualstosuccess

import android.animation.ArgbEvaluator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager

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

    }
}
