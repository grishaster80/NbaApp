package com.example.nbaapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbaapp.R
import com.example.nbaapp.data.model.Player
import com.example.nbaapp.viewmodel.NbaViewModel
import com.example.nbaapp.viewmodel.NbaViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import javax.inject.Inject

class NbaActivity : AppCompatActivity() {
    @Inject
    lateinit var nbaViewModelFactory: NbaViewModelFactory
    var nbaAdapter = NbaAdapter(ArrayList())
    lateinit var nbaViewModel: NbaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        nbaViewModel = ViewModelProvider(this,nbaViewModelFactory)
            .get(NbaViewModel::class.java)

        progressBar.visibility = View.VISIBLE
        loadData()

        nbaViewModel.playersResult().observe(this,
            Observer {
                if(it!=null){
                    recycler.adapter = nbaAdapter
                    recycler.layoutManager = LinearLayoutManager(this)
                    nbaAdapter.addPlayers(it)
                }
            })

        nbaViewModel.playersError().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(this, resources.getString(R.string.error_message) + it,
                    Toast.LENGTH_SHORT).show()
            }
        })

        nbaViewModel.playersLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })


    }

    private fun initializeRecycler(){
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
        }
    }

    fun loadData() {
        nbaViewModel.loadPlayers()
    }

    override fun onDestroy() {
        nbaViewModel.disposeElements()
        super.onDestroy()
    }
}
