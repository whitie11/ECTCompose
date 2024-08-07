package auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import auth.domain.repository.AuthRepository
import common.domain.repository.LocalSettingsRepository
import common.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.whiteapps.ectmanager.data.dto.TokenRequestDTO
import auth.domain.models.JwtPayload
import auth.domain.models.TokenResponse
import auth.domain.models.UserItem
import auth.domain.use_case.get_token.GetTokenUseCase
import auth.domain.use_case.get_user.GetUserUseCase
//import auth.domain.use_case.get_token.GetTokenUseCase
import auth.presentation.events.LoginUIEvent
import auth.presentation.stateObjs.LogInState
import auth.presentation.stateObjs.TokenRequestUIState
import auth.presentation.stateObjs.TokenState
import auth.utils.decodeJWT
import common.domain.models.IsLoggedIn

class AuthViewModel(
    private val repository: AuthRepository,
    private val localSettingsRepository: LocalSettingsRepository
): ViewModel() {

     val getTokenUseCase: GetTokenUseCase = GetTokenUseCase(
         authRepository = repository,
         localSettingsRepository = localSettingsRepository
     )

    val getUserUseCase: GetUserUseCase = GetUserUseCase(
        authRepository = repository,
        localSettingsRepository = localSettingsRepository
    )



    private val _loginState = MutableStateFlow<LogInState>(LogInState.SignedOut)
    val loginState = _loginState.asStateFlow()

    var tokenRequestUIState = mutableStateOf(TokenRequestUIState())

    private val _jwtPayload = mutableStateOf(JwtPayload())
    val jwtPayload: State<JwtPayload> = _jwtPayload

    private var _tokenState = MutableStateFlow(TokenState())
    val tokenState =_tokenState.asStateFlow()


    fun getHelloWorldString() : String{
        return repository.helloWorld()
    }

    fun onLoginEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.UsernameChanged -> {
                tokenRequestUIState.value = tokenRequestUIState.value.copy(
                    username = event.username
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                tokenRequestUIState.value = tokenRequestUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.DoLogin -> {
                val tr = TokenRequestDTO(
                    username = tokenRequestUIState.value.username,
                    password = tokenRequestUIState.value.password
                )
                   getToken(tr)
            }

            is LoginUIEvent.DoLogout -> {
              logout()
            }

        }
    }

     fun logout() {
         _tokenState.value = TokenState(
             refresh = "",
             access = "",
             isLoading = false,
             error = ""
         )
        _loginState.value = LogInState.SignedOut



//        Log.d("MyTag", "logout $loginState")
    }


    private fun getToken(tr: TokenRequestDTO)
    {
        getTokenUseCase(tr).onEach { result: Resource<TokenResponse> ->
            when (result) {
                is Resource.Success -> {
                    _tokenState.value = TokenState(
                        refresh = result.data?.refresh ?: "",
                        access = result.data?.access ?: ""
                    )
                    _jwtPayload.value = decodeJWT(_tokenState.value.access)
                    if (_jwtPayload.value.userID != null) {
                        val userId = _jwtPayload.value.userID
                        if (userId != null) {
                            getUser(userId, _tokenState.value.access )
                        }
                    } else {
                        // TODO logout if no userId & set error message
                    }

                }

                is Resource.Error -> {
                    _loginState.value = LogInState.Error
                    _tokenState.value = TokenState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _tokenState.value = TokenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    //TODO get user &  set isLoggedIn
    private fun getUser(userID: Int, token: String) {

        getUserUseCase(userID, token).onEach { result: Resource<UserItem> ->
            when (result) {
                is Resource.Success -> {
                    val isLoggedIn = IsLoggedIn(
                        userID = userID,
                        username = result.data?.username?: "",
                        role = result.data?.role?:  "",
                        state = true
                    )

                    localSettingsRepository.setIsLoggedInState(isLoggedIn)
                    _loginState.value = LogInState.SignedIn
                }

                is Resource.Error -> {
                    _loginState.value = LogInState.Error
                    _tokenState.value = TokenState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _tokenState.value = TokenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}