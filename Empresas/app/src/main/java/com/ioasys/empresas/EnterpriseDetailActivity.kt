package com.ioasys.empresas

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso

class EnterpriseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enterprise_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbarDetails)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { view ->
            finish()
        }

        val description = intent.getStringExtra("description")
        val photo = intent.getStringExtra("photo")

        val imageView = findViewById<ImageView>(R.id.enterprise_image_details)

        Picasso.get()
            .load(photo)
            .error(R.drawable.img_e_1_lista)
            .into(imageView)

        findViewById<TextView>(R.id.tv_enterprise_description).apply {
            if (!description.isNullOrBlank())
                text = description
        }
    }
}
