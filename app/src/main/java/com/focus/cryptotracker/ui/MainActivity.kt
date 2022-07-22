
package com.focus.cryptotracker.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.focus.cryptotracker.R
import com.focus.cryptotracker.databinding.ActivityMainBinding
import com.focus.cryptotracker.ui.fragments.AddCoinsFragment
import com.focus.cryptotracker.ui.fragments.CoinsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val coinFrag = CoinsFragment()
        val addCoinFrag = AddCoinsFragment()

        replaceFrag(coinFrag)
        binding.navBar.setOnItemSelectedListener { menuItem ->

            Log.d("ITEM_ID",menuItem.itemId.toString())
            Log.d("ITEM_ID",R.id.listButton.toString())


            when(menuItem.itemId) {
                R.id.listButton -> {
                    replaceFrag(coinFrag)
                }

                R.id.addCoin -> {
                    replaceFrag(addCoinFrag)
                }
            }
            true
        }

    }

    private fun replaceFrag(frag:Fragment) {

        val t = supportFragmentManager.beginTransaction()
        t.replace(binding.fragView.id,frag)
        t.commit()

    }

}