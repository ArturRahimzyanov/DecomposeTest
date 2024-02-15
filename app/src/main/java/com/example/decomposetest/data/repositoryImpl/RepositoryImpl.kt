package com.example.decomposetest.data.repositoryImpl

import com.example.decomposetest.R
import com.example.decomposetest.data.Api
import com.example.decomposetest.data.RepositoryResponse
import com.example.decomposetest.di.ResourcesProvider
import com.example.decomposetest.domain.model.Data
import com.example.decomposetest.domain.model.GifsArray
import com.example.decomposetest.domain.repository.Repository
import org.koin.java.KoinJavaComponent
import retrofit2.HttpException
import java.net.ConnectException

class RepositoryImpl(private val api: Api) : Repository {

    override var data: Data? = null
    private val resourcesProvider: ResourcesProvider by KoinJavaComponent.inject(
        ResourcesProvider::class.java
    )

    override suspend fun getGifs(
        limit: Int,
        offset: Int,
        q: String
    ): RepositoryResponse<GifsArray> = safeCall {
        api.searchGifs(API_KEY, q, limit, offset).body()
    }


    private suspend fun <T> safeCall(call: suspend () -> T?): RepositoryResponse<T> {
        try {

            val callResponse = call()

            return RepositoryResponse(
                success = RepositoryResponse.Success(
                    status = SUCCESS,
                    data = callResponse,
                )
            )
        } catch (exception: HttpException) {
            exception.printStackTrace()

            val errorMsg = when {
                exception.code() in 400..404 -> resourcesProvider.string(R.string.request_error)
                else -> resourcesProvider.string(R.string.undefined)
            }

            return RepositoryResponse(
                error = RepositoryResponse.Error(
                    status = ERROR,
                    code = exception.code(),
                    msg = errorMsg
                )
            )
        } catch (exception: ConnectException) {

            exception.printStackTrace()
            return RepositoryResponse(
                error = RepositoryResponse.Error(
                    status = ERROR,
                    msg = resourcesProvider.string(R.string.connection_error)
                )
            )

        } catch (exception: Exception) {

            exception.printStackTrace()
            return RepositoryResponse(
                error = RepositoryResponse.Error(
                    status = ERROR,
                    msg = resourcesProvider.string(R.string.undefined)
                )
            )
        }
    }

    companion object {
        const val API_KEY = "WEWvCoe7W4MyEkPxJLmRxPdRNbJte63R"
        const val SUCCESS = "success"
        const val ERROR = "error"
    }
}