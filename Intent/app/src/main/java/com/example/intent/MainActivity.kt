package com.example.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val btn1 : Button = findViewById(R.id.btn1)
        val btn2 : Button = findViewById(R.id.btn2)
        val btn3 : Button = findViewById(R.id.btn3)



        val uri: Uri? = intent.data

        // Kiểm tra xem URI có tồn tại không và hiển thị nó trong TextView
        if (uri != null) {
            val textView: TextView = findViewById(R.id.text_view)
            textView.text = uri.toString()
        } else {
            // Nếu không có URI, hiển thị thông báo "No URL received"
            val textView: TextView = findViewById(R.id.text_view)
            textView.text = "No URL received"
        }


        btn1.setOnClickListener {
            openWeb()
        }


        btn2.setOnClickListener { openMap()  }


        btn3.setOnClickListener { shareText() }

    }
    private fun openWeb(){
        val url = "http://developer.android.com"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        if(intent.resolveActivity(packageManager)!=null){
            startActivity(intent)
        }
        else{
            Toast.makeText(this, " không có ứng dụng nào ",Toast.LENGTH_SHORT).show()
        }

    }


    private fun openMap(){
        val address ="Golden Gate Bridge, San Francisco, CA"
//        val uri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
//        val intent = Intent(Intent.ACTION_VIEW,uri)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:0,0?q=${Uri.encode(address)}")

        intent.setPackage("com.google.android.apps.maps")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this,"Không có ứng dụng nào", Toast.LENGTH_SHORT).show()
        }

    }




    private fun shareText(){
        val text ="My Story"
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"

        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, "Chia sẻ với"))
    }

}