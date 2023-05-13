package com.example.kitchenrecipe

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Latest"
                1 -> tab.text = "Category"
                else -> tab.text = "Favourite"
            }
        }.attach()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val dialog = Dialog(this@MainActivity2)
        dialog.setContentView(R.layout.custom_exit)

//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogYes = dialog.findViewById<ImageView>(R.id.buttonYes)
        val dialogNo = dialog.findViewById<ImageView>(R.id.buttonNo)

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    //for remove black background of dialogbox
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        dialogNo.setOnClickListener { // dismiss the dialog
            dialog.dismiss()
        }
        dialogYes.setOnClickListener { // dismiss the dialog and exit the exit
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }

}