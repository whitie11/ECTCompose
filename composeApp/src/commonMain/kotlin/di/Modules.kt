package di

import common.data.repository.LocalSettingsRepositoryImpl
import common.domain.repository.LocalSettingsRepository
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val provideLocalSettingsRepository = module {
    singleOf(::LocalSettingsRepositoryImpl).bind<LocalSettingsRepository>()
}


//val provideAuthHttpClient = module {
//    single {
//        HttpClient {
//            install(ContentNegotiation) {
//                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
//            }
//        }
//    }
//}

