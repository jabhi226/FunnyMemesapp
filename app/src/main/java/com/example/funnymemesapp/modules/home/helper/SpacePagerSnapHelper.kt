package com.example.funnymemesapp.modules.home.helper

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class SpacePagerSnapHelper(
    private val snapHelper: SnapHelper
) : RecyclerView.OnScrollListener() {

    private enum class ScrollDirection {
        UNKNOWN, UP, DOWN
    }

    private var scrollDirection: ScrollDirection = ScrollDirection.UNKNOWN

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        scrollDirection = if (dy > 0) ScrollDirection.UP else ScrollDirection.DOWN
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> onPageChanged(recyclerView)
        }
    }

    private fun onPageChanged(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        val viewToSnap = snapHelper.findSnapView(layoutManager)
        viewToSnap?.let {
            val position = layoutManager?.getPosition(it) ?: 0
            when (scrollDirection) {
                ScrollDirection.DOWN -> {
                    recyclerView.smoothScrollToPosition(position)
                }
                ScrollDirection.UP -> {
                    recyclerView.smoothScrollToPosition(position)
                }
                ScrollDirection.UNKNOWN -> {
                    println("Unknown scrollDirection... Don't know where to go")
                }
            }
        }
    }
}