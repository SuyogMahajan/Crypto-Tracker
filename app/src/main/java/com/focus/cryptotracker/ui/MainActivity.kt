
package com.focus.cryptotracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.focus.cryptotracker.R
import com.focus.cryptotracker.databinding.ActivityMainBinding
import com.focus.cryptotracker.ui.fragments.AddCoinsFragment
import com.focus.cryptotracker.ui.fragments.CoinsFragment
import meow.bottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nevBar.add(MeowBottomNavigation.Model(1,R.drawable.ic_search))
        binding.nevBar.add(MeowBottomNavigation.Model(2,R.drawable.ic_search))

        binding.nevBar.show(1,true)
        replaceFrag(CoinsFragment())

        binding.nevBar.setOnClickMenuListener {
            when(it.id) {
                1 -> {
                      replaceFrag(CoinsFragment())
                }

                2 ->{
                      replaceFrag(AddCoinsFragment())
                }
            }
        }
    }

    private fun replaceFrag(frag:Fragment) {

         val t = supportFragmentManager.beginTransaction()
         t.replace(binding.viewPager.id,frag)

    }
}