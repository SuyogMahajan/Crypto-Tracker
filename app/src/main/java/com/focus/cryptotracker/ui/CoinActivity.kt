package com.focus.cryptotracker.ui

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.focus.cryptotracker.R
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.source.remote.CoinRemoteDataSource
import com.focus.cryptotracker.data.source.repository.CoinDataViewModel
import com.focus.cryptotracker.databinding.ActivityCoinBinding
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val CLICKED_COIN = "COIN"
class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding
    private lateinit var coin: Coin
    private lateinit var viewModel: CoinDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CoinDataViewModel::class.java)

        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coin = intent.getSerializableExtra(CLICKED_COIN) as Coin

        binding.nameTv.text = coin.name
        binding.prizeTv.text = "$ " + coin.current_price.toString()
        binding.rankTv.text = coin.market_cap_rank.toString()
        binding.codeTv.text = coin.symbol
        binding.totalSupplyTv.text ="Total Supply : " + "$ " + coin.total_supply.toLong().toString()
        binding.circulatingSupplyTv.text = "Circulating Supply : " +"$ " + coin.circulating_supply.toLong().toString()

        Glide.with(this).load(coin.image).into(binding.iconIv)
         setChart(coin)

        binding.refreshLayout.setOnRefreshListener {
            refreshChart()
        }

    }

    private fun setChart(coin: Coin) {

        binding.chatViewCandle.setHighlightPerDragEnabled(true)
        binding.chatViewCandle.setDrawBorders(true)
        binding.chatViewCandle.setBorderColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
        binding.chatViewCandle.setBackgroundColor(getColor(R.color.black))
        binding.chatViewCandle.xAxis.textColor = getColor(R.color.white)
        binding.chatViewCandle.axisLeft.textColor = getColor(R.color.white)
        binding.chatViewCandle.axisRight.textColor = getColor(R.color.white)

        refreshChart()

    }

    private fun refreshChart() {


        val chartData = viewModel.getCoinChart(coin.id)

        chartData.observe(this, object : Observer<List<List<Double>>> {
            override fun onChanged(t: List<List<Double>>?) {
                val candleEntryList = t!!.map {
                    CandleEntry(
                        it[0].toFloat() / 8640000,
                        it[2].toFloat(),
                        it[3].toFloat(),
                        it[1].toFloat(),
                        it[4].toFloat()
                    )
                }

                val candleDataSet = CandleDataSet(candleEntryList, "Price")

                candleDataSet.shadowColor = getColor(R.color.white)
                candleDataSet.shadowWidth = 1f;
                candleDataSet.setDrawValues(false)

                candleDataSet.decreasingColor = Color.RED
                candleDataSet.decreasingPaintStyle = Paint.Style.STROKE

                candleDataSet.increasingColor = Color.GREEN
                candleDataSet.increasingPaintStyle = Paint.Style.STROKE
                candleDataSet.color = getColor(R.color.black)


                val candleData = CandleData(candleDataSet)

                binding.chatViewCandle.animateXY(2000,1000)
                binding.chatViewCandle.startLayoutAnimation()
                binding.chatViewCandle.data = candleData
                binding.chatViewCandle.startLayoutAnimation()


                binding.refreshLayout.isRefreshing = false
            }

        })

    }
}
