package com.example.funnymemesapp.modules.home.listeners

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.funnymemesapp.R
import com.example.funnymemesapp.modules.home.models.ui.MemeModels
import java.util.Date

class MemeEventListeners(
    private val favoriteMeme: (MemeModels) -> Unit,
    private val sendMeme: (Intent) -> Unit
) {

    fun onFavoriteClicked(view: ConstraintLayout?, memeModel: MemeModels?) {
        val ivFavorite = (view?.getViewById(R.id.iv_favorite) as ImageView)
        val ivLike = (view.getViewById(R.id.iv_like) as ImageView)
        onDoubleClicked(ivLike, ivFavorite, memeModel)
    }

    private fun onDoubleClicked(ivLike: ImageView, ivFavorite: ImageView, item: MemeModels?) {
        item?.let(favoriteMeme)
        val scaleUp: Animation = AnimationUtils.loadAnimation(ivLike.context, R.anim.scale_up)
        scaleUp.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                ivFavorite.setColorFilter(
                    ContextCompat.getColor(
                        ivFavorite.context,
                        R.color.like_color
                    )
                )
            }

            override fun onAnimationEnd(animation: Animation?) {
                ivLike.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
        ivLike.visibility = View.VISIBLE
        ivLike.startAnimation(scaleUp)
    }

    private var clickTime = 0L
    fun doubleClickListener(view: View?, memeModel: MemeModels?) {
        val ivFavorite = ((view as ConstraintLayout?)?.getViewById(R.id.iv_favorite) as ImageView)
        val ivLike = (view?.getViewById(R.id.iv_like) as ImageView)
        if ((Date().time - clickTime) < 300L) {
            onDoubleClicked(ivLike, ivFavorite, memeModel)
        }
        clickTime = Date().time
    }

    fun onShareClicked(view: View?, meme: MemeModels?) {
        val imgUri = Uri.parse(meme?.meme?.url)
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
            ex.printStackTrace()
        }
    }

}