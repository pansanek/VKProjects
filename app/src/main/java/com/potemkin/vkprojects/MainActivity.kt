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
        // Instance of users list using the data model class.
        val usersList: ArrayList<ProjectModelClass> = ArrayList()
        // As we have JSON object, so we are getting the object
        //Here we are calling a Method which is returning the JSON object
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonObj: JSONObject = JSONObject(data)
            val usersArray: JSONArray = jsonObj.getJSONArray("items")
            // fetch JSONArray named users by using getJSONArray
            // Get the users data using for loop i.e. id, name, email and so on

            for (i in 0 until usersArray.length()) {
                // Create a JSONObject for fetching single User's Data
                val user = usersArray.getJSONObject(i)
                // Fetch id store it in variable
                val imURL = user.getString("icon_url")
                val name = user.getString("name")
                val desc = user.getString("description")
                val servURL = user.getString("service_url")
                // Now add all the variables to the data model class and the data model class to the array list.
                val userDetails =
                    ProjectModelClass(imURL, name, desc, servURL)

                // add the details in the list
                usersList.add(userDetails)

            }
            rvList.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = ProjectAdapter(this, usersList,this)
            Log.d("myTag", usersList.toString());
            // adapter instance is set to the recyclerview to inflate the items.
            rvList.adapter = itemAdapter
        }, { Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)
        // Set the LayoutManager that this RecyclerView will use.


    }

    override fun onItemClick(item: ArrayList<ProjectModelClass>, position: Int) {
        val i = Intent(this,ContentActivity::class.java).apply{
            putExtra("image",item[position].icon_url)
            putExtra("name",item[position].name)
            putExtra("desc",item[position].description)
            putExtra("url",item[position].service_url)
        }
        startActivity(i)

    }

}