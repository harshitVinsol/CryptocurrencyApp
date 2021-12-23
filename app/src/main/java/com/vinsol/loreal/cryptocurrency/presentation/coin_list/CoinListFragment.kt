package com.vinsol.loreal.cryptocurrency.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.vinsol.loreal.cryptocurrency.R

class CoinListFragment : Fragment() {
    private lateinit var viewModel: CoinListViewModel
    private lateinit var coinListAdapter: CoinListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_coin_list, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        viewModel.coinStateLiveData.observe(viewLifecycleOwner, Observer { coinListState ->
            coinListState?.let{
                coinListAdapter.setCoinList(it.coins)
            }
        })
    }

    private fun setupUI(){
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_coin_list)
        coinListAdapter = CoinListAdapter(coinList = emptyList(), coinClick = {

        })
        recyclerView?.adapter = coinListAdapter
    }

    companion object {
        fun getInstance() = CoinListFragment()
    }
}