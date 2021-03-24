package com.itzme.utilits

import android.content.res.ColorStateList
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.itzme.R


@BindingAdapter("app:loadImageDrawable")
fun loadImageDrawable(imageView: ImageView, image: Int) {
    imageView.setImageResource(image)
}

//
//@BindingAdapter("app:imageBase")
//fun imageBitmap(imageView: ImageView, base64: String?) {
//    if (base64 != null) {
//        val data = Base64.decode(base64, Base64.DEFAULT)
//        Glide.with(imageView.context)
//            .load(data)
//            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//            .into(imageView)
//    }
//}
//
//@BindingAdapter("pointTextView")
//fun point(textView: TextView, point: String) {
//    val text = "$point ${textView.context.resources.getString(R.string.egp)}"
//    textView.text = text
//}
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
            button.backgroundTintList = ColorStateList.valueOf(button.context.getColor(R.color.dark_blue))
        }
        Constant.ARABIC_LANGUAGE -> {
            button.text = button.context.getString(R.string.english)
            button.backgroundTintList = ColorStateList.valueOf(button.context.getColor(R.color.gray))
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
            button.backgroundTintList = ColorStateList.valueOf(button.context.getColor(R.color.gray))
        }
        Constant.ARABIC_LANGUAGE -> {
            button.text = button.context.getString(R.string.english)
            button.backgroundTintList = ColorStateList.valueOf(button.context.getColor(R.color.dark_blue))
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
