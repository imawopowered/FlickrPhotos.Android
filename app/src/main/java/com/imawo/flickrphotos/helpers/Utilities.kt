package com.imawo.flickrphotos.helpers

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur

object Utilities {
    fun setupBlurView(
        context: Context?,
        rootView: ViewGroup?,
        windowBackground: Drawable?,
        blurView: BlurView,
        radius: Float
    ) {
        rootView?.let {
            blurView.setupWith(it)
                .windowBackground(windowBackground)
                .blurAlgorithm(RenderScriptBlur(context))
                .blurRadius(radius)
                .setHasFixedTransformationMatrix(true)
        }
    }
}