package com.itzme.utilits

import android.R.id.message
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.itzme.data.models.baseResponse.BaseLink


class ClickOnLink {
    fun clickOnLink(view: View, baseLink: BaseLink) {
        if (!baseLink.link.isNullOrEmpty()) {
            when (baseLink.linkType) {
                0, 1 -> {
                    openFaceBook(view.context, baseLink.link!!)
                }
                2 -> {
                    openInstagramApp(view.context, baseLink.link!!)
                }
                3 -> {
                    openSnapChat(view.context, baseLink.link!!)
                }
                4 -> {
                    openTikTok(view.context, baseLink.link!!)
                }
                5 -> {
                    openLinkedIn(view.context, baseLink.link!!)
                }
                6 -> {
                    openTwitter(view.context, baseLink.link!!)
                }
                7 -> {
                    openYouTube(view.context, baseLink.link!!)
                }
                8 -> {
                    openTwitch(view.context, baseLink.link!!)
                }
                9 -> {
                    openTumbler(view.context, baseLink.link!!)
                }
                10 -> {
                    openVimeo(view.context, baseLink.link!!)
                }
                11, 12 -> {
                    composeEmail(view.context, baseLink.link!!)
                }
                13 -> {
                    openLine(view.context, baseLink.link!!)
                }
                14 -> {
                    openPhone(view.context, baseLink.link!!)
                }
                15 -> {
                    openSMS(view.context, baseLink.link!!)
                }
                16, 17 -> {
                    openWhatsApp(view.context, baseLink.link!!)
                }
                18 -> {
                    openTelegram(view.context, baseLink.link!!)
                }
                19 -> {
                    openWeChat(view.context, baseLink.link!!)
                }
                21 -> {
                    openMaps(view.context, baseLink.link!!)
                }
                29 -> {
                    openPinterest(view.context, baseLink.link!!)
                }
            }

        }
    }

    //region open another apps

    private fun openWhatsApp(context: Context, number: String) {
        val appName = "com.whatsapp"
        val isAppInstalled: Boolean = isAppAvailable(context.applicationContext, appName)

        if (isAppInstalled) {
            val url = "https://api.whatsapp.com/send?phone=$number"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            context.startActivity(i)
        } else {
            Toast.makeText(context, "The whatsApp doesn't install", Toast.LENGTH_SHORT).show()
        }

    }


    fun composeEmail(context: Context, email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        context.startActivity(Intent.createChooser(emailIntent, "Chooser Title"))
    }


    private fun openLinkedIn(context: Context, name: String) {
        val profile_url = "https://www.linkedin.com/in/$name"
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(profile_url))
            intent.setPackage("com.linkedin.android")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(profile_url)))
        }
    }

    private fun openPinterest(context: Context, name: String) {
        val urlPt = "https://www.pinterest.com/$name"
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(urlPt)
            intent.setPackage("com.pinterest")
            context.startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(urlPt)))
        }
    }

    private fun openSnapChat(context: Context, name: String) {
        val urlSn = "https://snapchat.com/add/$name"
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "*/*"
            intent.setPackage("com.snapchat.android")
            context.startActivity(Intent.createChooser(intent, "Open Snapchat"))
        } catch (anfe: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlSn)))
        }
    }

    private fun openTikTok(context: Context, name: String) {
        val urlTick = "https://www.tiktok.com/@${name}"
        try {

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(urlTick)
            intent.setPackage("com.zhiliaoapp.musically")
            context.startActivity(intent)

        } catch (anfe: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(urlTick)))
        }


    }

    private fun openYouTube(context: Context, name: String) {
        val urlYt = "https://www.youtube.com/channel/$name"

        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.google.android.youtube")
            intent.data = Uri.parse(urlYt)
            context.startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlYt)))
        }
    }

    private fun openTwitch(context: Context, name: String) {
        val uri = Uri.parse("https://www.twitch.com/_u/$name")

        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("tv.twitch.android.viewer")

        try {
            context.startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.twitch.com/$name")))
        }
    }

    private fun openTumbler(context: Context, name: String) {
        val uri = Uri.parse("https://www.tumblr.com/blog/view/$name")

        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("com.tumblr")
        try {
            context.startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.tumblr.com/blog/view/$name")))
        }
    }

    private fun openVimeo(context: Context, name: String) {
        val uri = Uri.parse("https://vimeo.com/$name")

        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("com.vimeo.android.videoapp")
        try {
            context.startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://vimeo.com/$name")))
        }
    }

    private fun openLine(context: Context, name: String) {

        val appName = "jp.naver.line.android"
        val isAppInstalled: Boolean = isAppAvailable(context.applicationContext, appName)

        if (isAppInstalled) {
            val uri = Uri.parse("https://line.me/R/oaMessage/@$name/?Hi")

            val likeIng = Intent(Intent.ACTION_VIEW, uri)

            likeIng.setPackage("jp.naver.line.android")
            context.startActivity(likeIng)

        } else {
            Toast.makeText(context, "The Line App doesn't install", Toast.LENGTH_SHORT).show()
        }

    }

    private fun openTwitter(context: Context, name: String) {
        val urlTw = "https://twitter.com/$name"
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(urlTw)
            intent.setPackage("com.twitter.android")
            context.startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(urlTw)))
        }
    }

    fun openFaceBook(context: Context, url: String) {
        val appName = "com.facebook.katana"
        val isAppInstalled: Boolean = isAppAvailable(context.applicationContext, appName)
        val uri: Uri
        val intent: Intent

        if (isAppInstalled) {
            uri = Uri.parse("fb://facewebmodal/f?href=$url")
            intent = Intent(Intent.ACTION_VIEW, uri)
        } else {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        }
        context.startActivity(intent)
    }

    private fun openInstagramApp(context: Context, name: String) {
        val uri = Uri.parse("http://instagram.com/_u/$name")
        val likeIng = Intent(Intent.ACTION_VIEW, uri)
        likeIng.setPackage("com.instagram.android")
        try {
            context.startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/$name")))
        }
    }

    private fun openPhone(context: Context, number: String) {
        val u = Uri.parse("tel:" + number)

        val i = Intent(Intent.ACTION_DIAL, u)
        try {

            context.startActivity(i)
        } catch (s: SecurityException) {
        }
    }

    private fun openSMS(context: Context, number: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("smsto:" + number)

        intent.putExtra("sms_body", message)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    private fun openTelegram(context: Context, name: String?) {
        val appName = "org.telegram.messenger"
        val isAppInstalled: Boolean = isAppAvailable(context.applicationContext, appName)
        if (isAppInstalled) {
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/$name"))
            telegram.setPackage("org.telegram.messenger")
            context.startActivity(telegram)
        } else {
            Toast.makeText(context, "Telegram not Installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openWeChat(context: Context, name: String?) {
        val appName = "com.tencent.mm"
        val isAppInstalled: Boolean = isAppAvailable(context.applicationContext, appName)
        if (isAppInstalled) {
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("weixin://dl/chat?$name"))
            telegram.setPackage("com.tencent.mm")
            context.startActivity(telegram)
        } else {
            Toast.makeText(context, "WeChat not Installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openMaps(context: Context, urlMap: String?) {
        val strUri = "$urlMap"
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.google.android.apps.maps")
            intent.data = Uri.parse(strUri)
            context.startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(strUri)))
        }
    }

    private fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url) // only used based on your example.


        val title = "Select a browser"

        val chooser = Intent.createChooser(intent, title)

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(chooser)
        }
    }


    fun isAppAvailable(context: Context, appName: String?): Boolean {
        val pm: PackageManager = context.packageManager
        return try {
            pm.getPackageInfo(appName!!, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }


    private fun openWebPage(context: Context, url: String?) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }


//    private fun openLine(context: Context, url: String?) {
//        val intent = Intent()
//        intent.action = Intent.ACTION_VIEW
//        intent.data = Uri.parse("https://line.me/R/oaMessage/$url/?Hi")
//        context.startActivity(intent)
//    }

//endregion

}