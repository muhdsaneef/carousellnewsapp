package com.example.carousellnewsapp.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class VerticalSpaceItemDecoration @Inject constructor() : RecyclerView.ItemDecoration() {
    var verticalSpaceHeight = 10

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = verticalSpaceHeight
    }
}