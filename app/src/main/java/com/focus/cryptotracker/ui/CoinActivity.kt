package com.focus.cryptotracker.ui

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.focus.cryptotracker.R
import com.focus.cryptotracker.data.model.Coin
import com.focus.cryptotracker.data.source.remote.CryptoApiDataSource
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coin = intent.getSerializableExtra(CLICKED_COIN) as Coin

        binding.nameTv.text = coin.name
        binding.prizeTv.text = "$ " + coin.currentPrice.toString()
        binding.rankTv.text = coin.marketCapRank.toString()
        binding.codeTv.text = coin.symbol
        binding.totalSupplyTv.text ="Total Supply : " + "$ " + coin.totalSupply.toLong().toString()
        binding.circulatingSupplyTv.text = "Circulating Supply : " +"$ " + coin.circulatingSupply.toLong().toString()
        Glide.with(this).load(coin.image).into(binding.iconIv)


        setChart(coin)


//       val l = LineDataSet(listOf(Entry(0f,0f),
//            Entry(1f,5f),Entry
//                (3f,2f)),"trial")
//
//        l.color = R.color.black
//        l.lineWidth = 2f
//
//        binding.chatViewCandle.data = LineData(l)


//        val dataSet = CandleDataSet(
//            listOf(
//                CandleEntry(0f, 255.0f, 211f, 221f, 215f),
//                CandleEntry(1f, 211.0f, 255f, 215f, 221f)
//            ), "trial"
//        )
//        dataSet.shadowColor = resources.getColor(R.color.black)
//        dataSet.shadowWidth = 1f
//
//        dataSet.decreasingColor = Color.parseColor("#FF0000")
//        dataSet.decreasingPaintStyle = Paint.Style.FILL
//
//        dataSet.increasingColor = Color.parseColor("#64DD17")
//        dataSet.increasingPaintStyle = Paint.Style.FILL
//        binding.chatViewCandle.data = CandleData(dataSet)
    }

    private fun setChart(coin: Coin) {

        binding.chatViewCandle.setHighlightPerDragEnabled(true)
        binding.chatViewCandle.setDrawBorders(true)
        binding.chatViewCandle.setBorderColor(resources.getColor(androidx.appcompat.R.color.material_blue_grey_800))
        binding.chatViewCandle.setBackgroundColor(getColor(R.color.black))
        binding.chatViewCandle.xAxis.textColor = getColor(R.color.white)
        binding.chatViewCandle.axisLeft.textColor = getColor(R.color.white)
        binding.chatViewCandle.axisRight.textColor = getColor(R.color.white)

        CoroutineScope(Dispatchers.Main).launch {

            val dataSource = CryptoApiDataSource(Dispatchers.IO)
            val chartData = dataSource.getCoinChart(coin.id)
            var i = 1f

            val candleEntryList = chartData.map {
                CandleEntry(
                    it[0].toFloat()/8640000,
                    it[2].toFloat(),
                    it[3].toFloat(),
                    it[1].toFloat(),
                    it[4].toFloat()
                )
            }

            Log.d("HELLO?",chartData.size.toString())
            val candleDataSet = CandleDataSet(candleEntryList,"Price")

            candleDataSet.shadowColor = resources.getColor(R.color.white)
            candleDataSet.shadowWidth = 1f;
            candleDataSet.setDrawValues(false)

            candleDataSet.decreasingColor = Color.RED
            candleDataSet.decreasingPaintStyle = Paint.Style.STROKE

            candleDataSet.increasingColor = Color.GREEN
            candleDataSet.increasingPaintStyle = Paint.Style.STROKE
            candleDataSet.color = getColor(R.color.black)

            val candleData = CandleData(candleDataSet)
            binding.chatViewCandle.data = candleData

//            val prices = chartData.prices!!.map {
//                Entry(it[0].toFloat(), it[1].toFloat())
//            }
//
//            val marketCap = chartData.marketCaps!!.map {
//                Entry(it[0].toFloat(), it[1].toFloat())
//            }
//
//            val priceLineData = LineDataSet(prices,"Price")
//            priceLineData.color = resources.getColor(R.color.green)
//            priceLineData.setCircleColor(resources.getColor(R.color.black))
//            priceLineData.lineWidth = 1.5f
//
//            val marketCapData = LineDataSet(marketCap,"Market Capital")
//            priceLineData.color = R.color.green
//            priceLineData.disableDashedLine()
//
//        val lineData = LineData(listOf(priceLineData))
//            binding.chatViewCandle.data = lineData

        }

    }
}