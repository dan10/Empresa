package com.ioasys.empresas

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ioasys.empresas.adapters.SearchAdapter
import com.ioasys.empresas.viewmodels.MainActivityViewModel
import com.ioasys.empresas.viewmodels.MainActivityViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var homeTextView: TextView
    private lateinit var model: MainActivityViewModel

    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeTextView = findViewById(R.id.tv_home)

        viewAdapter = SearchAdapter(null)
        val searchRecyclerView = findViewById<RecyclerView>(R.id.rv_search).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val accessToken = intent.getStringExtra("access-token")
        val client = intent.getStringExtra("client")
        val uid = intent.getStringExtra("uid")

        val headerMap = mapOf(
            "access-token" to accessToken,
            "client" to client,
            "uid" to uid
        )
        model = ViewModelProviders
            .of(this, MainActivityViewModelFactory(headerMap))
            .get(MainActivityViewModel::class.java)

        model.enterprises.observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                searchRecyclerView.swapAdapter(SearchAdapter(it), true)
                searchRecyclerView.visibility = View.VISIBLE
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the options menu from XML
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        (menu.findItem(R.id.action_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    model.name.value = query
                    model.getEnterprises()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })

            setOnSearchClickListener { homeTextView.visibility = View.GONE }
            setOnCloseListener {
                homeTextView.visibility = View.VISIBLE
                true
            }
        }
        return true
    }
}
