package com.example.task1.customView

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.example.task1.COMMENT_NUMBER
import com.example.task1.LIKE_DIFFERENCE
import com.example.task1.R

class CustomLikeBar : LinearLayout {

    private var likeDifferenceText: Int? = null
    private var commentNumberText: Int? = null
    private var likeDifferenceView: AppCompatTextView? = null
    private var commentNumberView: AppCompatTextView? = null


    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        val view = inflate(context, R.layout.custom_likebar, this)

        likeDifferenceView = view.findViewById(R.id.like_difference)
        commentNumberView = view.findViewById(R.id.comment_number)

        attrs?.let {
            val typedArray =
                context.theme.obtainStyledAttributes(attrs, R.styleable.CustomLikeBar, 0, 0)
            try {
                likeDifferenceText =
                    typedArray.getResourceId(R.styleable.CustomLikeBar_customLikeDifference, -1)
                likeDifferenceText?.let { likeDifferenceView?.text = LIKE_DIFFERENCE }
                commentNumberText =
                    typedArray.getResourceId(R.styleable.CustomLikeBar_customCommentNumber, -1)
                commentNumberText?.let { commentNumberView?.text = COMMENT_NUMBER }
            } catch (e: Exception) {

            } finally {
                typedArray.recycle()
            }

        }
    }


}