package auth.domain.repository

import auth.data.dto.UserItemDTO
import net.whiteapps.ectmanager.data.dto.TokenRequestDTO
import net.whiteapps.ectmanager.data.dto.TokenResponseDTO
import auth.data.remote.AuthApi
import auth.domain.models.UserItem
import common.util.NetworkError
import common.util.Result

interface AuthRepository {
    fun helloWorld(): String

    suspend fun getToken(tr: TokenRequestDTO): Result<TokenResponseDTO, NetworkError>

    suspend fun getUser(userId: Int, token: String): Result<UserItemDTO, NetworkError>

}

