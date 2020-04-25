package com.example.nbaapp.presentation.nba

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbaapp.R
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.nba_players.*
import javax.inject.Inject
import androidx.lifecycle.Observer

class NbaFragment : Fragment() {

    @Inject
    lateinit var nbaViewModelFactory: NbaViewModelFactory
    var nbaAdapter = NbaAdapter()
    lateinit var nbaViewModel: NbaViewModel


    override fun onAttach(context: Context) {

        AndroidSupportInjection.inject(this)

        nbaViewModel = ViewModelProvider(this,nbaViewModelFactory)
            .get(NbaViewModel::class.java)

        progressBar.visibility = View.VISIBLE
        loadData()

        nbaViewModel.playersResult().observe(this,
            Observer {
                if(it!=null){
                    recycler.adapter = nbaAdapter
                    recycler.layoutManager = LinearLayoutManager(this.context)
                    nbaAdapter.addPlayers(it)
                }
            })

        nbaViewModel.playersError().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(this.context, resources.getString(R.string.error_message) + it,
                    Toast.LENGTH_SHORT).show()
            }
        })

        nbaViewModel.playersLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })

        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nba_players,container,false)
    }



    private fun initializeRecycler(){
        val gridLayoutManager = GridLayoutManager(this.context, 1)
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