package com.example.funnymemesapp.modules.home.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.funnymemesapp.R
import com.example.funnymemesapp.databinding.ItemMemeBinding
import com.example.funnymemesapp.modules.home.listeners.MemeEventListeners
import com.example.funnymemesapp.modules.home.models.ui.MemeModels


class MemesAdapter(
    private val sendMeme: (Intent) -> Unit, private val favoriteMeme: (MemeModels) -> Unit
) : PagingDataAdapter<MemeModels, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<MemeModels>() {
    override fun areItemsTheSame(oldItem: MemeModels, newItem: MemeModels): Boolean {
        return oldItem.meme.postLink == newItem.meme.postLink
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MemeModels, newItem: MemeModels): Boolean {
        return oldItem == newItem
    }

}) {

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun ImageView.setImageUrl(url: String?) {
            if (url != null) {
                val circularProgressDrawable = CircularProgressDrawable(this.context)
                circularProgressDrawable.strokeWidth = 16f
                circularProgressDrawable.centerRadius = 64f
                circularProgressDrawable.setColorSchemeColors(
                    ContextCompat.getColor(
                        this.context, R.color.icon_color
                    )
                )
                circularProgressDrawable.start()
                Glide.with(this.context).load(url).placeholder(circularProgressDrawable).into(this)
            }
        }

        @JvmStatic
        @BindingAdapter("app:imageTint")
        fun ImageView.setImageTint(isFavorite: Boolean) {
            if (isFavorite) {
                this.setColorFilter(
                    ContextCompat.getColor(
                        this.context, R.color.like_color
                    )
                )
            } else {
                this.setColorFilter(
                    ContextCompat.getColor(
                        this.context, R.color.icon_color
                    )
                )
            }
        }
    }

    inner class ViewHolder(val c: ItemMemeBinding) : RecyclerView.ViewHolder(c.root) {
        fun bindData(item: MemeModels?, eventListeners: MemeEventListeners) {
            c.memeModel = item
            c.events = eventListeners
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_meme, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(
            getItem(position),
            MemeEventListeners({ favoriteMeme(it) }, { sendMeme(it) })
        )
    }
}