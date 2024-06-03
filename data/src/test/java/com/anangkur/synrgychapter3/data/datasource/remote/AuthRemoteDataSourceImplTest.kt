package com.anangkur.synrgychapter3.data.datasource.remote

import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.ReqresService
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.request.LoginBody
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.response.LoginResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class AuthRemoteDataSourceImplTest {

    // Given
    private val reqresService = mock<ReqresService>()

    private val dataSource = AuthRemoteDataSourceImpl(
        reqresService = reqresService,
    )

    @Test
    fun loginHardCodeTest() = runTest {
        // given
        val username = "anangkur"
        val password = "anangkur123"

        // when
        val expected = "abcdefghijklmnopqrstuvwxyz0987654321"
        val actual = dataSource.login(username, password)

        // then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun loginReqresTest() = runTest {
        // given
        val username: String = "username"
        val password: String = "password"
        val loginBody = LoginBody(username, password, username)
        val loginResponse = LoginResponse("id", "token")

        // when
        whenever(reqresService.login(loginBody)).thenReturn(loginResponse)
        val expected = "token"
        val actual = dataSource.login(username, password)

        // then
        Assert.assertEquals(expected, actual)
    }
}