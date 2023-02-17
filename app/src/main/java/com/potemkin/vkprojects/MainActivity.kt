package com.potemkin.vkprojects

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

val url = "https://mobile-olympiad-trajectory.hb.bizmrg.com/semi-final-data.json"

class MainActivity : AppCompatActivity(), ProjectAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList: ArrayList<ProjectModelClass> = ArrayList()

        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonObj: JSONObject = JSONObject(data)
            val itemsArray: JSONArray = jsonObj.getJSONArray("items")

            for (i in 0 until itemsArray.length()) {
                val item = itemsArray.getJSONObject(i)

                val imURL = item.getString("icon_url")
                val name = item.getString("name")
                val desc = item.getString("description")
                val servURL = item.getString("service_url")

                val itemsDetails = ProjectModelClass(imURL, name, desc, servURL)

                itemList.add(itemsDetails)

            }
            rvList.layoutManager = LinearLayoutManager(this)

            val itemAdapter = ProjectAdapter(this, itemList, this)

            rvList.adapter = itemAdapter

        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)


    }

    override fun onItemClick(item: ArrayList<ProjectModelClass>, position: Int) {
        val i = Intent(this, ContentActivity::class.java).apply {
            putExtra("image", item[position].icon_url)
            putExtra("name", item[position].name)
            putExtra("desc", item[position].description)
            putExtra("url", item[position].service_url)
        }
        startActivity(i)

    }

}