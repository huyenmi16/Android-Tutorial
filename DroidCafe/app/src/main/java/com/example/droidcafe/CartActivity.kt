package com.example.droidcafe

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class CartActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(findViewById(R.id.toolbar))
        val buttonShowAlert: Button = findViewById(R.id.button_show_alert)
        buttonShowAlert.setOnClickListener {
            showAlertDialog()
        }
        val buttonShowDatePicker: Button = findViewById(R.id.button_show_date_picker)
        buttonShowDatePicker.setOnClickListener {
            showDatePicker()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

//    gán menu cho activity
//    override phương thức onCreateOptionMenu để inflate (nạp) menu vào Activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

//    Override phương thức onOptionsItemSelected để xử lý các sự kiện khi người dùng chọn một mục từ menu.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            R.id.food_burger -> {
                displayMessage("Burger selected")
                return true
            }
            R.id.food_pizza -> {
                displayMessage("Pizza selected")
                return true
            }
            R.id.food_salad -> {
                displayMessage("Salad selected")
                return true
            }
            R.id.food_pasta -> {
                displayMessage("Pasta selected")
                return true
            }
            R.id.drink_water -> {
                displayMessage("Water selected")
                return true
            }
            R.id.drink_coffee -> {
                displayMessage("Coffee selected")
                return true
            }
            R.id.drink_tea -> {
                displayMessage("Tea selected")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    private fun displayMessage(message: String) {

         Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert Dialog")
        builder.setMessage("This is a simple alert dialog.")
        builder.setPositiveButton("OK") { dialog, which ->
            // Xử lý khi người dùng nhấn nút OK
            Toast.makeText(this, "OK button clicked", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            // Xử lý khi người dùng nhấn nút Cancel
            Toast.makeText(this, "Cancel button clicked", Toast.LENGTH_SHORT).show()
        }
        val dialog = builder.create()
        dialog.show()
    }
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                Toast.makeText(this, "Selected date: $selectedDate", Toast.LENGTH_SHORT).show()
            }, year, month, dayOfMonth)

        datePickerDialog.show()
    }
}