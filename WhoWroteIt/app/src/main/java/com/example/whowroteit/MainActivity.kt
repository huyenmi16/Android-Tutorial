package com.example.whowroteit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    // Variables for the search input field, and results TextViews
    private lateinit var mBookInput: EditText
    private lateinit var mTitleText: TextView
    private lateinit var mAuthorText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize all the view variables
        mBookInput = findViewById(R.id.bookInput)
        mTitleText = findViewById(R.id.titleText)
        mAuthorText = findViewById(R.id.authorText)

        // Check if a Loader is running, if it is, reconnect to it
        if (supportLoaderManager.getLoader<String>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }
    }


    fun searchBooks(view: View) {
        // Get the search string from the input field.
        val queryString = mBookInput.text.toString()

        // Hide the keyboard when the button is pushed.
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

        // Check the status of the network connection.
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo

        // If the network is active and the search field is not empty,
        // add the search term to the arguments Bundle and start the loader.
        if (networkInfo != null && networkInfo.isConnected && queryString.isNotEmpty()) {
            mAuthorText.text = ""
            mTitleText.setText(R.string.loading)
            val queryBundle = Bundle().apply {
                putString("queryString", queryString)
            }
            supportLoaderManager.restartLoader(0, queryBundle, this)
        } else {
            if (queryString.isEmpty()) {
                mAuthorText.text = ""
                mTitleText.setText(R.string.no_search_term)
            } else {
                mAuthorText.text = ""
                mTitleText.setText(R.string.no_network)
            }
        }
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        val queryString = args?.getString("queryString")
        return BookLoader(this, queryString ?: "")
    }


    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        try {
            // Convert the response into a JSON object.
            val jsonObject = JSONObject(data)
            // Get the JSONArray of book items.
            val itemsArray: JSONArray = jsonObject.getJSONArray("items")

            // Initialize iterator and results fields.
            var i = 0
            var title: String? = null
            var authors: String? = null

            // Look for results in the items array, exiting when both the title and author
            // are found or when all items have been checked.
            while (i < itemsArray.length() && (authors == null && title == null)) {
                // Get the current item information.
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject("volumeInfo")

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title")
                    authors = volumeInfo.getString("authors")
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                // Move to the next item.
                i++
            }

            // If both are found, display the result.
            if (title != null && authors != null) {
                mTitleText.text = title
                mAuthorText.text = authors
                mBookInput.setText("")
            } else {
                // If none are found, update the UI to show failed results.
                mTitleText.setText(R.string.no_results)
                mAuthorText.text = ""
            }

        } catch (e: Exception) {
            // If onPostExecute does not receive a proper JSON string, update the UI to show failed results.
            mTitleText.setText(R.string.no_results)
            mAuthorText.text = ""
            e.printStackTrace()
        }
    }


    override fun onLoaderReset(loader: Loader<String>) {}
}