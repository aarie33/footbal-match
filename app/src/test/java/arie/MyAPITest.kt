package arie

import arie.footballmatch.connections.ReqApi
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class MyAPITest {
    @Test
    fun testDetailTeam(){
        val api = mock(ReqApi::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604"
        api.doRequest(url)
        verify(api).doRequest(url)
    }
}
