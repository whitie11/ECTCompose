package auth.domain.use_case.get_user

import auth.data.dto.toUserItem
import auth.domain.models.TokenResponse
import auth.domain.models.UserItem
import auth.domain.repository.AuthRepository
import common.domain.repository.LocalSettingsRepository
import common.util.Resource
import common.util.onError
import common.util.onSuccess
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.whiteapps.ectmanager.data.dto.TokenRequestDTO
import net.whiteapps.ectmanager.data.dto.toTokenResponse

//import common.util.Resource
//import dependencies.AuthRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import net.whiteapps.ectmanager.common.util.Resource
//import auth.data.dto.toUserItem
//import auth.domain.models.UserItem
//import net.whiteapps.ectmanager.domain.repository.AuthRepository


//
//class GetUserUseCase(
//    private val repository: AuthRepository
//) {
//    operator fun invoke(userId: Int): Flow<Resource<UserItem>> = flow {
//        try {
////            emit(Resource.Loading())
//            val res = repository.getUser(userId).toUserItem()
//            emit(Resource.Success(res))
//        } catch(e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
//        } catch(e: IOException) {
//            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
//        }
//    }
//}

class GetUserUseCase(
//    client: AuthApi,
    private val authRepository: AuthRepository,
    private val localSettingsRepository: LocalSettingsRepository
) {
    operator fun invoke(userId: Int, token: String): Flow<Resource<UserItem>> = flow {
        try {
            emit(Resource.Loading())
            authRepository.getUser(userId, token)
                .onSuccess {
//                    localSettingsRepository.accessToken = it.toTokenResponse().access
                    emit(Resource.Success(it.toUserItem()))
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