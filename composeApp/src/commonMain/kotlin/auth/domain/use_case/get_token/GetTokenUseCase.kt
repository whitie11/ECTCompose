package auth.domain.use_case.get_token

import common.domain.repository.LocalSettingsRepository
import common.util.Resource
import auth.domain.repository.AuthRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.whiteapps.ectmanager.data.dto.TokenRequestDTO
import net.whiteapps.ectmanager.data.dto.toTokenResponse
import auth.domain.models.TokenResponse
import common.util.onError
import common.util.onSuccess


class GetTokenUseCase(
//    client: AuthApi,
    private val authRepository: AuthRepository,
    private val localSettingsRepository: LocalSettingsRepository
) {
    operator fun invoke(tr: TokenRequestDTO): Flow<Resource<TokenResponse>> = flow {
        try {
            emit(Resource.Loading())
          authRepository.getToken(tr)
                .onSuccess {
                    localSettingsRepository.accessToken = it.toTokenResponse().access
                    emit(Resource.Success(it.toTokenResponse()))
                }
                .onError {
                    emit(Resource.Error(it.name))
                }
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch(e: Throwable ) {
            emit(Resource.Error("Something went badly wrong"))
        }

    }
}