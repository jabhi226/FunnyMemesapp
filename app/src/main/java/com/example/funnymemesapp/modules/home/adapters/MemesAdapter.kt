package com.example.funnymemesapp.modules.home.adapters

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.funnymemesapp.R
import com.example.funnymemesapp.databinding.ItemMemeBinding
import com.example.funnymemesapp.modules.home.models.network.Memes


class MemesAdapter(val sendMeme: (Intent) -> Unit, val favoriteMeme: (Memes) -> Unit) : PagingDataAdapter<Memes, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Memes>() {
    override fun areItemsTheSame(oldItem: Memes, newItem: Memes): Boolean {
        return oldItem.postLink == newItem.postLink
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Memes, newItem: Memes): Boolean {
        return oldItem == newItem
    }

}) {
    inner class ViewHolder(private val c: ItemMemeBinding) : RecyclerView.ViewHolder(c.root) {
        fun bindData(item: Memes?) {
            item?.let {
                if (it.preview.isNotEmpty()){
                    Glide.with(c.root).load(it.preview[it.preview.size-1]).into(c.ivMeme)
                }
                c.tvTitle.text = item.title
                c.tvSubreddit.text = item.subreddit + " | " + item.author
//                c.webView.webViewClient = WebViewClient()
//                c.webView.settings.javaScriptEnabled = true
//                val newUA =
//                    "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0"
//                c.webView.settings.userAgentString = newUA
//                c.webView.loadUrl(this)
                c.ivShare.setOnClickListener {
                    val imgUri = Uri.parse(item.preview[item.preview.size-1])
                    val whatsappIntent = Intent(Intent.ACTION_SEND)
                    whatsappIntent.type = "text/plain"
                    whatsappIntent.setPackage("com.whatsapp")
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT, "🤣😂\uD83E\uDD23\uD83D\uDE02")
                    whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri)
                    whatsappIntent.type = "image/jpeg"
                    whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    try {
                        sendMeme(whatsappIntent)
                    } catch (ex: ActivityNotFoundException) {

                    }
                }
                c.ivFavorite.setOnClickListener {
                    favoriteMeme(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_meme,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(getItem(position))
    }
}