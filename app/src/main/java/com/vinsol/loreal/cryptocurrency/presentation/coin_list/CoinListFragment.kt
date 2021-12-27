package com.vinsol.loreal.cryptocurrency.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.vinsol.loreal.cryptocurrency.R
import com.vinsol.loreal.cryptocurrency.common.Constants.PARAM_COIN_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {
    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var coinListAdapter: CoinListAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_coin_list, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupUI()
        viewModel.coinStateLiveData.observe(viewLifecycleOwner, Observer { coinListState ->
            coinListState?.let{
                coinListAdapter.setCoinList(it.coins)
            }
        })
    }

    private fun setupUI(){
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_coin_list)
        coinListAdapter = CoinListAdapter(coinList = emptyList(), coinClick = { coin ->
            val args = bundleOf(PARAM_COIN_ID to coin.id)
            navController.navigate(
                R.id.action_coinListFragment_to_coinDetailFragment,
                args
            )
        })
        recyclerView?.adapter = coinListAdapter
    }
}