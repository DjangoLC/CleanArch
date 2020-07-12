package com.example.cleanarchme.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.animation.addListener
import com.example.cleanarchme.R
import kotlinx.android.synthetic.main.switch_layout.view.*

typealias onClickSwitch = (isEnabled: Boolean) -> Unit

class CustomSwitch(val c: Context, val attr: AttributeSet) : LinearLayout(c, attr),
    View.OnClickListener {

    private var backColorGroundShip: Int = Color.parseColor("#000000")
    private var backColorGroundSwitch: Int = Color.parseColor("#000000")
    private var dimen = 0

    private var textOn: Int
    private var textOff: Int
    private var isChecked = false

    private var onClickSwitch: onClickSwitch? = null

    private var tvOn: TextView
    private var tvOff: TextView
    private var container: FrameLayout
    private var shape: AppCompatButton

    private var oa: ObjectAnimator? = null

    init {

        inflate(c, R.layout.switch_layout, this)

        //get a pixel size for a particular dimension - will differ by device according to screen density
        dimen = resources.getDimensionPixelSize(R.dimen.settings_toggle_width)

        context.theme.obtainStyledAttributes(
            attr,
            R.styleable.CustomSwitch,
            0, 0
        ).apply {

            tvOn = findViewById(R.id.tvOn)
            tvOff = findViewById(R.id.tvOff)
            container = findViewById(R.id.custom_switch_container)
            container.setOnClickListener(this@CustomSwitch)
            shape = findViewById(R.id.imgShape)

            try {
                backColorGroundShip = getInt(R.styleable.CustomSwitch_backColorGroundShip, 0)
                backColorGroundSwitch = getInt(R.styleable.CustomSwitch_backColorGroundSwitch, 0)

                textOn = getResourceId(R.styleable.CustomSwitch_textOn, R.string.default_switch_on)
                textOff =
                    getResourceId(R.styleable.CustomSwitch_textOff, R.string.default_switch_off)


                val parent = container.layoutParams
                shape.layoutParams = FrameLayout.LayoutParams(
                    parent.width / 2,
                    parent.height - 10, Gravity.CENTER_VERTICAL
                )

                Log.e("tag,", "h:${shape.layoutParams.height} w: ${shape.layoutParams.width}")
            } finally {
                recycle()
            }
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        changeTextColor(isChecked)
    }

    fun seClickTextOnListener(clickListener: onClickSwitch) {
        this.onClickSwitch = clickListener
    }

    override fun onClick(v: View?) {

        if (oa?.isRunning == true || oa?.isStarted == true) return

        if (isChecked) {
            oa = ObjectAnimator.ofFloat(imgShape, "x", 8f)
            oa?.duration = 250
            oa?.startDelay = 250
        } else {
            val s = container.width - (imgShape.width + 8)
            oa = ObjectAnimator.ofFloat(imgShape, "x", s.toFloat())
            oa?.duration = 250
            oa?.startDelay = 250
        }

        oa?.addListener(onEnd = {
            isChecked = !isChecked
            changeTextColor(isChecked)
        })

        oa?.start()
        onClickSwitch?.invoke(!isChecked)

    }

    private fun changeTextColor(boolean: Boolean) {
        tvOn.setTextColor(if (boolean) Color.WHITE else Color.parseColor("#80000000"))
        tvOff.setTextColor(if (!boolean) Color.WHITE else Color.parseColor("#80000000"))

    }

}