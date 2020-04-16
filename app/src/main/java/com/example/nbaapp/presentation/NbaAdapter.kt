package com.example.nbaapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaapp.R
import com.example.nbaapp.data.model.Player
import com.example.nbaapp.databinding.PlayerCardBinding
import com.example.nbaapp.presentation.NbaAdapter.NbaViewHolder
import kotlinx.android.synthetic.main.player_card.view.*
import java.util.ArrayList

class NbaAdapter(players: List<Player>?) : RecyclerView.Adapter<NbaViewHolder>()  {

    private var playersList = ArrayList<Player>()

    init {
        this.playersList = players as ArrayList<Player>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbaViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
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

    class NbaViewHolder constructor(val binding: PlayerCardBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(player:Player){
            binding.player = player
            binding.executePendingBindings()
        }






//        var playerFirstName = itemView.findViewById<TextView>(R.id.first_name)
//        var playerLastName = itemView.findViewById<TextView>(R.id.last_name)
//        var playerPosition = itemView.findViewById<TextView>(R.id.position)
//
//        fun playerListItem(playerItem:Player){
//            playerFirstName.text=playerItem.firstName.toString()
//            playerLastName.text=playerItem.lastName.toString()
//            playerPosition.text=playerItem.position.toString()
//        }
    }
}


