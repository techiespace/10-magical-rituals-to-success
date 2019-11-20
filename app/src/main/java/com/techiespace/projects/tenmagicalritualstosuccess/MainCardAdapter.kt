package com.techiespace.projects.tenmagicalritualstosuccess

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter


class MainCardAdapter(private val mainCards: List<MainCard>, private val context: Context) :
    PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun getCount(): Int = mainCards.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.item, container, false)

        val imageView: ImageView
        val title: TextView
        val desc: TextView
        val cardView: CardView

        imageView = view.findViewById(R.id.image)
        title = view.findViewById(R.id.title)
        desc = view.findViewById(R.id.desc)
        cardView = view.findViewById(R.id.cardViewMain)

        imageView.setImageResource(mainCards[position].image)
        title.text = mainCards[position].title
        desc.text = mainCards[position].desc

        cardView.setOnClickListener {
            if (desc.text == "")
                Toast.makeText(
                    context,
                    "Follow the previous ritual for a week to unlock this ritual",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("param", mainCards[position].title)
                context.startActivity(intent)
                // finish();
            }
        }

        container.addView(view)
        return view
    }

    /*fun getCardViewAt(position: Int): ImageView {
//        return mainCards[position].image
    }*/

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(`object` as View)
}