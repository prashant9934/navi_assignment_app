package com.example.naviassignmentapp.data.exceptions

import java.io.IOException

class NoConnectivityException : IOException() {

    override val message: String
        get() = "No network available, please check your WiFi or Data connection"

    override fun getLocalizedMessage(): String? {
        return "No network available, please check your WiFi or Data connection"
    }
}