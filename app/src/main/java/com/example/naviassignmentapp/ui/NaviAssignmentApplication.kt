package com.example.naviassignmentapp.ui

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NaviAssignmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    companion object {
        private lateinit var _instance: Application
        fun getContext(): Context {
           return _instance
        }
    }
}