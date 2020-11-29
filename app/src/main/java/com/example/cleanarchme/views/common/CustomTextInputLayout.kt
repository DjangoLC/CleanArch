package com.example.cleanarchme.views.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.example.cleanarchme.R
import com.google.android.material.textfield.TextInputLayout

@SuppressLint("ClickableViewAccessibility")
class CustomTextInputLayout(c: Context, attributes: AttributeSet) :
    TextInputLayout(c, attributes) {

    private val colorHintActive: Int
    private val colorHintInactive: Int

    val csl1: ColorStateList
    val csl2: ColorStateList

    var endIcon: Drawable? = null

    var showEndIcon: Boolean = false

     init {
        c.theme.obtainStyledAttributes(attributes,R.styleable.CustomTextInputLayout,0,0).apply {
            try{
                colorHintActive = getColor(R.styleable.CustomTextInputLayout_hintColorActive,0)
                colorHintInactive = getColor(R.styleable.CustomTextInputLayout_hintColorInactive,0)
                endIcon = getDrawable(R.styleable.CustomTextInputLayout_customEndDrawable)
                showEndIcon = getBoolean(R.styleable.CustomTextInputLayout_showCustomEndDrawable,false)

                csl1 = ColorStateList.valueOf(colorHintInactive)
                csl2 =ColorStateList.valueOf(colorHintActive)
            } finally {
                recycle()
            }
        }

        this.addOnEditTextAttachedListener {
            Log.e("tag,","asdasdasdasd")
            editText?.apply {
                this.setCompoundDrawablesWithIntrinsicBounds(null, null, if (showEndIcon) endIcon else null, null)
                this.setOnTouchListener(OnTouchListener { v, event ->
                    val DRAWABLE_LEFT = 0
                    val DRAWABLE_TOP = 1
                    val DRAWABLE_RIGHT = 2
                    val DRAWABLE_BOTTOM = 3
                    if (event.action == MotionEvent.ACTION_UP) {
                        if (event.rawX >= this.getRight() - this.getCompoundDrawables()
                                .get(DRAWABLE_RIGHT).getBounds().width()
                        ) {
                            // your action here
                            Log.e("TAGG","click click")
                            return@OnTouchListener true
                        }
                    }
                    false
                })
            }

        }

    }

    override fun onDraw(canvas: Canvas?) {
        val isEmpty = editText?.text?.isEmpty() ?: true
        hintTextColor = if (isEmpty || isFocused) csl1 else csl2
        invalidate()
        requestLayout()

        //endIconMode = END_ICON_CUSTOM
        setEndIconDrawable(android.R.drawable.screen_background_light_transparent)

        //setEndIconTintList(ColorStateList.valueOf(Color.parseColor("#00953a")))
        setEndIconOnClickListener {
            Log.e("TAG","was clkic")
        }
        super.onDraw(canvas)
    }

}