package com.sevenpeakssoftware.zawlynn.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale


class CommonUtility {

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    @SuppressLint("SimpleDateFormat")
    fun getReleasedDate(str_date: String, context: Context): String {
        var _release_date = ""
        val _currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val release_fromat: SimpleDateFormat
        try {
            val input = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.ENGLISH)
            val date = input.parse(str_date)
            val calendar = GregorianCalendar()
            calendar.time = date
            val year = calendar.get(Calendar.YEAR)
            if (year == _currentYear) {
                if (!DateFormat.is24HourFormat(context)) {
                    release_fromat = SimpleDateFormat("dd MMMM hh:mm a", Locale.ENGLISH)
                } else {
                    release_fromat = SimpleDateFormat("dd MMMM kk:mm", Locale.ENGLISH)
                }
            } else {
                if (!DateFormat.is24HourFormat(context)) {
                    release_fromat = SimpleDateFormat("dd MMMM yyyy hh:mm a", Locale.ENGLISH)
                } else {
                    release_fromat = SimpleDateFormat("dd MMMM yyyy kk:mm", Locale.ENGLISH)
                }
            }
            _release_date = release_fromat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return _release_date
    }

    companion object {
        private var instance: CommonUtility? = null

        @Synchronized
        fun getInstance(): CommonUtility {
            if (instance == null) {
                instance = CommonUtility()
            }
            return instance as CommonUtility
        }
    }
}

