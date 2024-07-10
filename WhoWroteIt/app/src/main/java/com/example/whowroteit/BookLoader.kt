package com.example.whowroteit

import android.content.Context
import androidx.loader.content.AsyncTaskLoader


class BookLoader(context: Context, private val queryString: String) : AsyncTaskLoader<String>(context) {


    override fun onStartLoading() {
        forceLoad() // Starts the loadInBackground method
    }


    override fun loadInBackground(): String? {
        return NetworkUtils.getBookInfo(queryString)
    }
}
