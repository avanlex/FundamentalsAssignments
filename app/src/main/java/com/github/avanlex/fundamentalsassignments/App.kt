package com.github.avanlex.fundamentalsassignments

import android.app.Application

class App : Application() {

    companion object {
        private var application: Application? = null

        fun getApplication() : Application? = application;
    }

    @Override
    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
