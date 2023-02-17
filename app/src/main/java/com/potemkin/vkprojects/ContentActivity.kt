package com.potemkin.vkprojects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        tvTitul.text = intent.getStringExtra("name")
        tvDesc.text = intent.getStringExtra("desc")
        tvURL.text = intent.getStringExtra("url")
        var icon = intent.getStringExtra("image")
        Picasso.get().load(icon).into(iV);
        Linkify.addLinks(tvURL,Linkify.WEB_URLS)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}