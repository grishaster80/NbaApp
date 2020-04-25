package com.example.nbaapp.presentation.nba

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaapp.data.model.Player
import com.example.nbaapp.databinding.PlayerCardBinding
import com.example.nbaapp.presentation.nba.NbaAdapter.NbaViewHolder
import java.util.ArrayList

class NbaAdapter : RecyclerView.Adapter<NbaViewHolder>()  {

    private var playersList = ArrayList<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val playerBinding = PlayerCardBinding.inflate(layoutInflater, parent, false)
        return NbaViewHolder(playerBinding)
    }

    override fun onBindViewHolder(holder: NbaViewHolder, position: Int) {
        val playerItem =playersList[position]
        holder.bind(playerItem)
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    fun addPlayers(players:List<Player>){
        val initPosition = playersList.size
        playersList.addAll(players)
        notifyItemRangeInserted(initPosition, playersList.size)
    }

    class NbaViewHolder constructor(private val binding: PlayerCardBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(player:Player){
            binding.player = player
            binding.executePendingBindings()
        }
    }
}


