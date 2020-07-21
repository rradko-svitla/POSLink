package com.ascend5050.paxposlink.services


sealed class CCStatus {
    class Progress(val detail: String) : CCStatus()
    class Completed(val result: CCResult) : CCStatus()
    class Error(val error: String) : CCStatus()
}