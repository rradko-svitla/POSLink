package com.ascend5050.paxposlink.services

import android.util.Patterns

class CCDevice(ip: String = "") {
    val ipAddress: String = ip

    var isValid: Boolean = false
        get() = Patterns.IP_ADDRESS.matcher(ipAddress).matches()
}