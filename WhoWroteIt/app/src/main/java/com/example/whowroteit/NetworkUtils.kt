package com.example.whowroteit


import android.net.Uri
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Utility class for using the Google Book Search API to download book information.
 */
object NetworkUtils {

    private const val BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?"
    private const val QUERY_PARAM = "q" // Parameter for the search string.
    private const val MAX_RESULTS = "maxResults" // Parameter that limits search results.
    private const val PRINT_TYPE = "printType" // Parameter to filter by print type.


    fun getBookInfo(queryString: String): String? {

        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        var bookJSONString: String? = null

        try {
            // Build up your query URI, limiting results to 10 items and printed books.
            val builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build()

            val requestURL = URL(builtURI.toString())

            // Open the network connection.
            urlConnection = requestURL.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            // Get the InputStream.
            val inputStream = urlConnection.inputStream

            // Read the response string into a StringBuilder.
            val builder = StringBuilder()

            reader = BufferedReader(InputStreamReader(inputStream))

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // but it does make debugging a *lot* easier if you print out the completed buffer for debugging.
                builder.append(line).append("\n")
            }

            if (builder.isEmpty()) {
                // Stream was empty. No point in parsing.
                return null
            }
            bookJSONString = builder.toString()

        } catch (e: IOException) {
            e.printStackTrace()

        } finally {
            urlConnection?.disconnect()
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        // Return the raw response.
        return bookJSONString
    }
}
