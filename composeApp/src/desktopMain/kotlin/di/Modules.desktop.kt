package di

//import dependencies.DbClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.engine.cio.CIO
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

//actual val platformModule = module {
//    singleOf(::DbClient)
//}

//actual fun myHttpClient(): HttpClient {
//    return HttpClient(CIO) {
////        install(JsonFeature) {
////            serializer = KotlinxSerializer()
////        }
////        install(Logging) {
////            level = LogLevel.INFO
////        }
//        install(ContentNegotiation) {
//                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
//            }
//        }
//    }
