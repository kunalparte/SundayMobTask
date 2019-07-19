package com.example.kunalparte.sundaymobtask.utils

import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.*
import android.widget.ImageView

open class ImageScaleListener(imageView: ImageView) : SimpleOnScaleGestureListener() {

    var imageView : ImageView = imageView

    override fun  onScale( scaleGestureDetector: ScaleGestureDetector):Boolean{

        var mScaleFactor:Float = scaleGestureDetector.scaleFactor
        mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
        imageView.setScaleX(mScaleFactor)
        imageView.setScaleY(mScaleFactor)
        return true
    }
}
