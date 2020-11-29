package com.example.cleanarchme.views.login

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchme.R
import com.example.cleanarchme.views.common.inflate
import kotlinx.android.synthetic.main.row_snap.view.*
import kotlin.properties.Delegates

class Snap()

class SnapAdapter() : RecyclerView.Adapter<SnapAdapter.ViewHolder>() {

    var snaps: List<Snap> by Delegates.observable(emptyList(), { _, _, _ ->
        notifyDataSetChanged()
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.row_snap, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = snaps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val snap = snaps[position]
        holder.bind(snap, position)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Snap, position: Int) {
            if (position == 0) {
                val old = view.container.layoutParams
                val params =  ConstraintLayout.LayoutParams(old.width, old.height)
                params.setMargins(80,0,0,0)
                view.container.layoutParams = params
            } else {
                val old = view.container.layoutParams
                val params =  ConstraintLayout.LayoutParams(old.width, old.height)
                params.setMargins(0,0,0,0)
                view.container.layoutParams = params
            }
        }
    }
}