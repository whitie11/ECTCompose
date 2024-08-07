package auth.data.repository

import auth.data.dto.UserItemDTO
import auth.data.remote.AuthApi
import auth.domain.repository.AuthRepository
import common.util.NetworkError
import common.util.Result
import net.whiteapps.ectmanager.data.dto.TokenRequestDTO
import net.whiteapps.ectmanager.data.dto.TokenResponseDTO

class AuthRepositoryImpl(
    private val api: AuthApi,
): AuthRepository {
    override fun helloWorld(): String { return "Hello World" }

    override suspend fun getToken(tr: TokenRequestDTO): Result<TokenResponseDTO, NetworkError> {
        return  api.getTokens(tr)
    }

    override suspend fun getUser(userId: Int, token: String): Result<UserItemDTO, NetworkError> {
        return api.getUser(userId,token)
    }


}