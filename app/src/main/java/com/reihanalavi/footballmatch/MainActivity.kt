package com.reihanalavi.footballmatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.reihanalavi.footballmatch.R.id.next_match
import com.reihanalavi.footballmatch.R.id.past_match
import com.reihanalavi.footballmatch.fragments.NextMatch
import com.reihanalavi.footballmatch.fragments.PastMatch
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener ({ item ->
            when(item.itemId) {
                past_match -> {
                    loadPastMatch(savedInstanceState)
                }
                next_match -> {
                    loadNextMatch(savedInstanceState)
                }
            }
            true
        })
        bottom_navigation.selectedItemId = past_match
    }

    private fun loadPastMatch(savedInstanceState: Bundle?) {
        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, PastMatch(), PastMatch::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextMatch(savedInstanceState: Bundle?) {
        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatch(), NextMatch::class.simpleName)
                    .commit()
        }
    }
}
