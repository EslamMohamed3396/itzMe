package com.itzme.utilits

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.itzme.R
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
            val bitMatrix = codeWriter.encode(linkeName, BarcodeFormat.QR_CODE, width, height)
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
//
//@BindingAdapter("app:isHiddenStrock")
//fun isHiddenStrock(cardView: CardView, hidden: Boolean?) {
//    if (hidden!!) {
//        cardView. = View.GONE
//    } else {
//        cardView.visibility = View.VISIBLE
//    }
//}

@BindingAdapter("isDirectOn")
fun isDirectOn(button: Button, isOn: Boolean) {
    if (isOn) {
        button.text = button.context.getString(R.string.direct_on)
        button.backgroundTintList = ColorStateList.valueOf(button.context.getColor(R.color.purple_200))
    } else {
        button.text = button.context.resources.getString(R.string.direct_off)
        button.backgroundTintList = ColorStateList.valueOf(button.context.getColor(R.color.dark_blue))
    }
}

@BindingAdapter("isProfilePrivate")
fun isProfilePrivate(button: SwitchMaterial, isPrivate: Boolean) {
    button.isChecked = isPrivate
}

@BindingAdapter("isActive")
fun isActive(button: SwitchMaterial, isActive: Boolean) {
    button.isChecked = !isActive
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

//
//@BindingAdapter("app:fromToHourText")
//fun workHourText(textView: TextView, fromTo: String) {
//    val text =
//        "${textView.context.resources.getString(R.string.choose_the_time_you_would_like_to_receive_the_contract_at_your_address_we_deliver_from)} $fromTo ."
//    textView.text = text
//}
//
//@BindingAdapter("app:convertGenderTextView")
//fun gender(textView: TextView, gender: String?) {
//    when (gender) {
//        Constant.MALE_GENDER -> {
//            textView.text = textView.context.resources.getString(R.string.male)
//        }
//        Constant.FEMALE_GENDER -> {
//            textView.text = textView.context.resources.getString(R.string.female)
//
//        }
//        else -> {
//            return
//        }
//    }
//
////    val text = "$gender ${textView.context.resources.getString(R.string.egp)}"
////    textView.text = "$text"
//}
//
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
//
//@BindingAdapter("app:isFavourite")
//fun isFavourite(imageView: ImageView, isFavourite: Boolean) {
//    if (isFavourite) {
//        imageView.setImageResource(R.drawable.selected_heart)
//    } else {
//        imageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
//    }
//}
//
//@BindingAdapter("app:dateTextView")
//fun changeDateFormat(textView: TextView, dateString: String?) {
//    if (dateString != null) {
//        var result = ""
//        val formatterOld = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
//        val formatterNew = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//        var date: Date? = null
//        try {
//            date = formatterOld.parse(dateString)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//        if (date != null) {
//            result = formatterNew.format(date)
//        }
//        textView.text = result
//    }
//
//    @BindingAdapter("app:dateStartDate")
//    fun startDateFormat(textView: TextView, dateString: String?) {
//        if (dateString != null) {
//            var result = ""
//            val formatterOld = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
//            val formatterNew = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            var date: Date? = null
//            try {
//                date = formatterOld.parse(dateString)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//            if (date != null) {
//                result = formatterNew.format(date)
//            }
//            textView.text =
//                DayrahApp.getContext().resources.getString(R.string.start_date) + " : " + result
//        }
//    }
//
//    @BindingAdapter("app:dateEndDate")
//    fun endDateFormat(textView: TextView, dateString: String?) {
//        if (dateString != null) {
//            var result = ""
//            val formatterOld = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
//            val formatterNew = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            var date: Date? = null
//            try {
//                date = formatterOld.parse(dateString)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//            if (date != null) {
//                result = formatterNew.format(date)
//            }
//            textView.text =
//                DayrahApp.getContext().resources.getString(R.string.end_date) + " : " + result
//        }
//    }
//
//    @BindingAdapter("app:isSelected")
//    fun isSelected(imageView: ImageView, isTaken: Boolean) {
//        if (isTaken) {
//            imageView.setImageResource(R.drawable.item_select_month_payment_method)
//        } else {
//            imageView.setImageResource(R.drawable.point_select)
//        }
//    }
//
//    @BindingAdapter("app:selectCircle")
//    fun isSelectCircle(imageView: ImageView, isTaken: Boolean?) {
//        if (isTaken == true) {
//            Glide.with(imageView.context)
////        .load(Constant.BASE_URL + url)
//                .load(R.drawable.item_select_month_payment_method)
//                .into(imageView)
////            imageView.setImageResource(R.drawable.item_select_month_payment_method)
//        } else {
//            Glide.with(imageView.context)
////        .load(Constant.BASE_URL + url)
//                .load(R.drawable.point_select)
//                .into(imageView)
////            imageView.setImageResource(R.drawable.point_select)
//        }
//    }
//
////    @BindingAdapter("app:selectedCircle")
////    fun circleSelect(imageView: ImageView, isTaken: Boolean) {
////        if (isTaken) {
////            imageView.setImageDrawable(
////                ResourcesCompat.getDrawable(
////                    imageView.context.resources, R.drawable.item_select_month_payment_method, null
////                )
////            )
////        } else {
////            imageView.setImageDrawable(
////                ResourcesCompat.getDrawable(
////                    imageView.context.resources, R.drawable.point_select, null
////                )
////            )
////        }
////    }
//
//}
