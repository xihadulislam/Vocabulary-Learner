    package com.project.wordlearner.common

    import android.content.ActivityNotFoundException
    import android.content.Context
    import android.content.Intent
    import android.net.Uri
    import com.project.wordlearner.R


    fun Context.openPrivacyActivity() {
        val uri = Uri.parse(AppConstants.privacy_policy_link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    fun Context.openPlayStoreActivity() {
        val uri = Uri.parse(getString(R.string.moreapps_link))
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    fun Context.openRateAppActivity() {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.rateapp_link) + packageName)
                )
            )
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(String.format(getString(R.string.rateapp_link), packageName))
                )
            )
        }
    }


    fun Context.openFeedbackActivity() {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:" + getString(R.string.feedback_email_link))
        startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)))
    }
