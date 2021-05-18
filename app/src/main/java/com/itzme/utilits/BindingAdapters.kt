package com.itzme.utilits

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputLayout
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.itzme.R
import com.itzme.data.models.baseResponse.BaseLink
import timber.log.Timber


@BindingAdapter("app:loadImageDrawable")
fun loadImageDrawable(imageView: ImageView, image: Int) {
    imageView.setImageResource(image)
}


@BindingAdapter("app:imageLoadUrl")
fun imageLoadUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView.context)
            .load(Constant.BASE_URL_IMAGE + url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
    }
}

@BindingAdapter("app:imageLoadQrCode")
fun imageLoadQrCode(imageView: ImageView, linkeName: String?) {
    if (linkeName != null) {
        val width = 500
        val height = 500
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix =
                codeWriter.encode("http://$linkeName", BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) {
            Timber.d("${e.message}")
        }

        Glide.with(imageView.context)
            .load(bitmap)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
    }
}


@BindingAdapter("app:isHiddenTrick")
fun isHiddenTrick(imageView: ImageView, hidden: Boolean?) {
    if (hidden!!) {
        imageView.visibility = View.GONE
    } else {
        imageView.visibility = View.VISIBLE
    }
}

@BindingAdapter("isDirectOn")
fun isDirectOn(button: Button, isOn: Boolean) {
    if (isOn) {
        button.text = button.context.getString(R.string.direct_on)
        button.backgroundTintList =
            ColorStateList.valueOf(button.context.getColor(R.color.purple_200))
    } else {
        button.text = button.context.resources.getString(R.string.direct_off)
        button.backgroundTintList =
            ColorStateList.valueOf(button.context.getColor(R.color.dark_blue))
    }
}

//
//@BindingAdapter("instructionHint")
//fun instructionHint(textView: TextView, link: BaseLink) {
//    when (link.linkType) {
//        0 -> {
//            textView.text =
//                    "Go to facebook.com and open your Facebook profile. Then copy and paste the url link here."
//        }
//        1 -> {
//            textView.text =
//                    "Go to facebook.com and open your Facebook page. Then copy and paste the url \n" +
//                            "link here"
//        }
//        2 -> {
//            textView.text =
//                    "Open the Instagram app and go to your profile. Your Instagram username will be \n" +
//                            "at the top of your screen."
//        }
//        3 -> {
//            textView.text =
//                    "Open Snapchat and tap your profile picture in the top left corner. Your username \n" +
//                            "is below your Snapchat name."
//        }
//        4 -> {
//            textView.text =
//                    "Open TikTok and go to the “me” tab. Your TikTok username is under your profile \n" +
//                            "picture."
//        }
//        5 -> {
//            textView.text =
//                    "Go to your Linkedin profile and scroll down to the “contact” section. Find your \n" +
//                            "LinkedIn profile link and copy/paste here!"
//        }
//        6 -> {
//            textView.text =
//                    "Open the Twitter app and tap your profile picture in the top left corner. Your \n" +
//                            "twitter username will be in grey with an @ sign"
//        }
//        7 -> {
//            textView.text =
//                    " Go to your channel or video. Tap the three dots in the top right corner and tap \n" +
//                            "share. Copy/paste the link here."
//        }
//        8 -> {
//            textView.text =
//                    "Open the Twitch app and go to your account. Copy and paste your username \n" +
//                            "here!"
//        }
//        9 -> {
//            textView.text =
//                    "Copy and Paste your Tumblr link."
//        }
//        10 -> {
//            textView.text =
//                    "Open the Vimeo app and tap the three lines in the top left corner. Your username \n" +
//                            "will be in grey with an @ sign."
//        }
//        11 -> {
//            textView.text =
//                    "Open Google Maps and find your business location. Tap the share button in the \n" +
//                            "top right and copy here!"
//        }
//        12 -> {
//            textView.text =
//                    "Input your personal email address."
//        }
//        13 -> {
//            textView.text =
//                    "Input your Business email address."
//        }
//        14 -> {
//            textView.text =
//                    "Add Your Line Username"
//        }
//        15 -> {
//            textView.text =
//                    "Enter your phone number. Make sure to include your country code!"
//        }
//        16 -> {
//            textView.text =
//                    "Input your phone number with your country code (Ex: +20 for Egypt)."
//        }
//    }
//}
//
@BindingAdapter("hintName")
fun hintName(textInputLayout: TextInputLayout, link: BaseLink) {
    when (link.linkType) {
        0, 1, 7, 9, 11, 12, 22, 23, 24, 25, 26, in 30..41 -> {
            textInputLayout.hint =
                "${link.linkTypeName} ${textInputLayout.context.resources.getString(R.string.link)}"

        }
        in 2..6, 8, 10, in 18..20, in 27..29 -> {
            textInputLayout.hint =
                "${link.linkTypeName} ${textInputLayout.context.resources.getString(R.string.user_name_hint)}"
        }
        in 14..17, 21 -> {
            textInputLayout.hint =
                "${link.linkTypeName} ${textInputLayout.context.resources.getString(R.string.hyper_link)}"
        }
    }
}


@BindingAdapter("isProfilePrivate")
fun isProfilePrivate(button: SwitchMaterial, isPrivate: Boolean) {
    button.isChecked = isPrivate
}


@BindingAdapter("setPrefixText")
fun setPrefixText(textInputLayout: TextInputLayout, link: BaseLink) {
    when (link.linkType) {
        0, 1, 7, 9, 11, 12, 22, 23, 24, 25, 26, in 30..41 -> {
            if (link.link != null && !link?.link?.startsWith("http://")!! && !link.link?.startsWith(
                    "https://"
                )!!
            ) {
                textInputLayout.prefixText = "http://"
            }
        }

    }
}

@BindingAdapter("customLinkName")
fun customLinkName(textView: TextView, link: BaseLink) {
    when (link.linkType) {
        in 39..41 -> {
            if (!link.name.isNullOrEmpty()) {
                textView.text = link.name
            } else {
                textView.text = link.linkTypeName
            }
        }
        else -> {
            textView.text = link.linkTypeName
        }

    }
}

@BindingAdapter("isActive")
fun isActive(button: SwitchMaterial, isActive: Boolean) {
    button.isChecked = isActive
}

@BindingAdapter("isActiveForButton")
fun isActiveForButton(button: Button, isActive: Boolean) {
    if (isActive) {
        button.setTextColor(ColorStateList.valueOf(button.context.getColor(R.color.white)))
        button.text = button.context.getString(R.string.deactive)
        button.backgroundTintList =
            ColorStateList.valueOf(button.context.getColor(R.color.green))
    }
}

@BindingAdapter("app:langEn")
fun langEn(button: Button, language: String?) {
    when (language) {
        Constant.ENGLISH_LANGUAGE -> {
            button.text = button.context.getString(R.string.english)
            button.backgroundTintList =
                ColorStateList.valueOf(button.context.getColor(R.color.dark_blue))
        }
        Constant.ARABIC_LANGUAGE -> {
            button.text = button.context.getString(R.string.english)
            button.backgroundTintList =
                ColorStateList.valueOf(button.context.getColor(R.color.gray))
        }
        else -> {
            return
        }
    }
}

@BindingAdapter("app:langAr")
fun langAr(button: Button, language: String?) {
    when (language) {
        Constant.ENGLISH_LANGUAGE -> {
            button.text = button.context.getString(R.string.arabic)
            button.backgroundTintList =
                ColorStateList.valueOf(button.context.getColor(R.color.gray))
        }
        Constant.ARABIC_LANGUAGE -> {
            button.text = button.context.getString(R.string.arabic)
            button.backgroundTintList =
                ColorStateList.valueOf(button.context.getColor(R.color.dark_blue))
        }
        else -> {
            return
        }
    }
}



