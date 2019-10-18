package com.techiespace.projects.tenmagicalritualstosuccess

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: Adapter
    var argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val models = listOf<Model>(
            Model(R.drawable.brochure, "Believe", "Dream big and give give it everything you got."),
            Model(
                R.drawable.sticker,
                "Faith",
                "Faith is the fire that fuels the dream, that powers the engine that turns the world."
            ),
            Model(R.drawable.poster, "Plan", "Make a damn schedule"),
            Model(R.drawable.namecard, "Action", "What gets you out of bed everyday")
        )

        adapter = Adapter(models, this)
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
