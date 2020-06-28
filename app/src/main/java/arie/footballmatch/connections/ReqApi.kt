package arie.footballmatch.connections

import java.net.URL

class ReqApi {
    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}