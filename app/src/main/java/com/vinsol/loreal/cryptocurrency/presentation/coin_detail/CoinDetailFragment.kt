package com.vinsol.loreal.cryptocurrency.presentation.coin_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.vinsol.loreal.cryptocurrency.R
import com.vinsol.loreal.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.vinsol.loreal.cryptocurrency.domain.model.CoinDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {
    private val coinId: String by lazy { arguments?.getString(PARAM_COIN_ID, "") ?: "" }
    private val viewModel: CoinDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_coin_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCoin(coinId)
        viewModel.coinDetailStateLiveData.observe(viewLifecycleOwner, Observer {
            it?.coinDetail?.let{ coinDetail ->
                setupUI(coinDetail)
            }
        })
    }

    private fun setupUI(coinDetail: CoinDetail){
        view?.findViewById<TextView>(R.id.tv_coin_name)?.text = coinDetail.name
        view?.findViewById<TextView>(R.id.tv_coin_desc)?.text = coinDetail.description
    }
}