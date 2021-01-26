package com.github.avanlex.fundamentalsassignments

import android.app.Application
import com.github.avanlex.fundamentalsassignments.di.AppContainer

class MovieApplication : Application() {

    val appContainer: AppContainer by lazy { AppContainer() }

}
