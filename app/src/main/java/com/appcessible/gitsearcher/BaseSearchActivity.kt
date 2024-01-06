package com.appcessible.gitsearcher

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.appcessible.gitsearcher.databinding.ActivityMainBinding
import com.appcessible.gitsearcher.services.BusProvider
import com.appcessible.gitsearcher.services.GitHubService
import com.appcessible.gitsearcher.services.event.BusEvent
import javax.inject.Inject

abstract class BaseSearchActivity : AppCompatActivity() {

    protected lateinit var sharedPref: SharedPreferences
    protected lateinit var binding: ActivityMainBinding
    @Inject internal lateinit var busProvider: BusProvider
    @Inject internal lateinit var service: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        (application as MyApplication).inject(this)
        sharedPref = getPreferences(Context.MODE_PRIVATE)
    }

    override fun onStart() {
        super.onStart()
        try {
            busProvider.register(this)
        } catch (e: IllegalArgumentException) {
            Log.e(BaseSearchActivity::class.java.simpleName, "Unable to register busProvider", e)
        }
    }

    override fun onStop() {
        super.onStop()
        busProvider.unregister(this)
    }

    fun post(event: Any?) {
        event?.let { busProvider.post(it) }
    }

    fun getEvent(type: BusEvent.EventType) = busProvider.getEvent(type)
}