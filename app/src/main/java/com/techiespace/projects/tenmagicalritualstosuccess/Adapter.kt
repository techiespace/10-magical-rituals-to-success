package com.techiespace.projects.tenmagicalritualstosuccess

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter


class Adapter(private val models: List<Model>, private val context: Context) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun getCount(): Int = models.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context);
        val view: View = layoutInflater.inflate(R.layout.item, container, false)

        val imageView: ImageView
        val title: TextView
        val desc: TextView

        imageView = view.findViewById(R.id.image)
        title = view.findViewById(R.id.title)
        desc = view.findViewById(R.id.desc)

        imageView.setImageResource(models[position].image)
        title.text = models[position].title
        desc.text = models[position].desc

        view.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("param", models[position].title)
            context.startActivity(intent)
            // finish();
        }


        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(`object` as View)


}