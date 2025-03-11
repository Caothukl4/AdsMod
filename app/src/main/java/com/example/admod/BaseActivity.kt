package com.example.admod

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

open class BaseActivity : AppCompatActivity() {
    protected var database: FirebaseDatabase? = null // Sửa thành protected để MainActivity có thể truy cập

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance() // Đảm bảo Firebase được khởi tạo

        // Thiết lập full màn hình
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}