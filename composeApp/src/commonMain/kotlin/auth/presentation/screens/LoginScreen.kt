package auth.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import auth.presentation.AuthViewModel
import auth.presentation.screens.components.LoginError
import auth.presentation.stateObjs.LogInState

@Composable
fun LoginScreen(
    vm: AuthViewModel,
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit
) {
    val tokenRequestUIState = vm.tokenRequestUIState.value
    val onLoginEvent = vm::onLoginEvent
    val tokenState = vm.tokenState.value
val loginState = vm.loginState.collectAsState()
    when (loginState.value) {
        is LogInState.SignedOut ->
            LoginUI(tokenState, tokenRequestUIState, onLoginEvent) // Display login UI components
        is LogInState.InProgress -> {
            // Display loading spinner
//            CustomCircularProgressBar()
        }
        is LogInState.Error -> {
//            val context = LocalContext.current
//            Toast.makeText(context, tokenState.error, Toast.LENGTH_SHORT).show()
            LoginError(onLoginEvent,tokenState.error )
        }    // Display error toast
        is LogInState.SignedIn -> {
            // Using the SignIn state as a trigger to navigate
             LaunchedEffect(Unit){
                onLogin()
            }
        }
    }
}