package com.vinsol.loreal.cryptocurrency.presentation.coin_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinsol.loreal.cryptocurrency.R
import com.vinsol.loreal.cryptocurrency.domain.model.Coin

class CoinListAdapter(
    private var coinList: List<Coin>,
    private val coinClick: ((Coin) -> Unit)? = null
) :
    RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    fun setCoinList(list: List<Coin>) {
        coinList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)

        return CoinListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    inner class CoinListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal fun bind(coin: Coin) {
            itemView.findViewById<TextView>(R.id.tv_coin_name).text = "${coin.rank}. ${coin.name}"

            if (coin.is_active) {
                itemView.findViewById<TextView>(R.id.tv_active).visibility = View.VISIBLE
                itemView.findViewById<TextView>(R.id.tv_dead).visibility = View.GONE
            } else {
                itemView.findViewById<TextView>(R.id.tv_active).visibility = View.GONE
                itemView.findViewById<TextView>(R.id.tv_dead).visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
                coinClick?.invoke(coin)
            }
        }
    }
}


