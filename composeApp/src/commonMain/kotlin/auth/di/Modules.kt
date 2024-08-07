package auth.di

import auth.data.remote.AuthApi
import auth.data.repository.AuthRepositoryImpl
import auth.domain.repository.AuthRepository
import auth.presentation.AuthViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authApi = AuthApi(HttpClient {
    install(ContentNegotiation) {
        json(
            json = Json { ignoreUnknownKeys = true },
            contentType = ContentType.Any
        )
    }
})

val AuthApiModule = module {
    singleOf(::authApi)
}

val AuthRepositoryModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}

val AuthViewModelModule = module {
    viewModelOf(::AuthViewModel)
}