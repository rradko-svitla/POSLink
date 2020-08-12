package com.ascend5050.paxposlink

import com.ascend5050.paxposlink.services.CCDevice
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object Configuration {
    /// The common IP settings
    val ipAddress = BehaviorSubject.create<String>()
    /// Create device from ip address
    var device: Observable<CCDevice> = ipAddress
            .map { CCDevice(it) }

}