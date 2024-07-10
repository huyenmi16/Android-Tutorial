package com.example.dynamicfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl.Transaction
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentOne: Fragment
    private lateinit var fragmentTwo: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager
        fragmentOne = FragmentOne()
        fragmentTwo = FragmentTwo()

        // Hiển thị fragment mặc định (fragment one)
        showFragment(fragmentOne)

        // Xử lý sự kiện khi click vào nút "Thêm Fragment"
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            addFragment()
        }

        // Xử lý sự kiện khi click vào nút "Thay thế Fragment"
        findViewById<Button>(R.id.btnReplace).setOnClickListener {
            replaceFragment()
        }

        // Xử lý sự kiện khi click vào nút "Đóng Fragment"
        findViewById<Button>(R.id.btnRemove).setOnClickListener {
            removeFragment()
        }
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun addFragment() {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragmentTwo)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun replaceFragment() {
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        val targetFragment = if (currentFragment is FragmentOne) fragmentTwo else fragmentOne

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, targetFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun removeFragment() {
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment != null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(currentFragment)
            transaction.commit()
        }
    }
}
