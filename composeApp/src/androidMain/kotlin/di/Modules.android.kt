package di

//import dependencies.DbClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

//actual val platformModule = module {
////    singleOf(::DbClient)
//}

//actual fun myHttpClient(): HttpClient {
//    return HttpClient(OkHttp) {
////        install(JsonFeature) {
////            serializer = KotlinxSerializer()
////        }
////        install(Logging) {
////            level = LogLevel.INFO
////        }
//        install(ContentNegotiation) {
//            json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
//        }
//    }
//}
