import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import auth.presentation.AuthViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import auth.presentation.screens.ForgotPasswordScreen
import auth.presentation.screens.LoginScreen
import auth.presentation.screens.RegisterScreen
import homeFeature.presentation.HomeViewModel
import homeFeature.presentation.screens.HomeScreen
import homeFeature.presentation.screens.Screen1
import homeFeature.presentation.screens.Screen2
import referal.presentation.ReferralViewModel
import referal.presentation.screens.ReferralsHomeScreen
import treatment.presentation.TreatmentViewModel
import treatment.presentation.screens.TreatmentHomeScreen

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            val authViewModel = koinViewModel<AuthViewModel>()
            val homeViewModel = koinViewModel<HomeViewModel>()
            val referralViewModel = koinViewModel<ReferralViewModel>()
            NavHost(
                navController = navController, startDestination = "auth"
            ) {

                navigation(
                    startDestination = "login",
                    route = "auth"
                ) {
                    composable("login") {
                        LoginScreen(
                            vm = authViewModel,
                            onLogin = {
                                navController.navigate("home") {
                                    popUpTo("auth") {
                                        inclusive = true
                                    }
                                }

                            },
                            onSignUp = {
                                navController.navigate("signUp")
                            },
                            onForgotPassword = {
                                navController.navigate("forgotPassword")
                            }
                        )
                    }
                    composable("signUp") {
                        RegisterScreen(
                            onNavigate = {
                                navController.navigateUp()
                            }
                        )
                    }
                    composable("forgotPassword") {
                        ForgotPasswordScreen(
                            onNavigate = {
                                navController.navigateUp()
                            }
                        )
                    }
                }

                navigation(
                    startDestination = "home1",
                    route = "home"
                ) {
                    composable("home1") {
                        HomeScreen(
                            vm = homeViewModel,
                            onLogout = {
                                authViewModel.logout()
                                navController.navigate("auth") {
                                    popUpTo("home") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateReferral = {
                                navController.navigate("referral") {
                                    popUpTo("home") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateToScreen1 = {
                                navController.navigate("screen1")
                            },
                            onNavigateToScreen2 = {
                                navController.navigate("screen2")
                            },
                            onNavigateTreatment = {
                                navController.navigate("treatment")
                            }
                        )
                    }
                    composable("screen1") {
                        Text(text = "Heading")
                        Screen1(
                            onNavigate = {
                                navController.navigateUp()
                            }
                        )
                    }
                    composable("screen2") {
                        Screen2(
                            onNavigate = {
                                navController.navigateUp()
                            }
                        )
                    }
                }

                navigation(
                    startDestination = "referralHome",
                    route = "referral"
                ) {
                    composable("referralHome")
                    {
                        ReferralsHomeScreen(
                            vm = referralViewModel,
                            onLogout = {
                                authViewModel.logout()
                                navController.navigate("auth") {
                                    popUpTo("referral") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateHome = {
                                navController.navigate("home") {
                                    popUpTo("referral") {
                                        inclusive = true
                                    }
                                }


                            }
                        )
                    }
                }

                navigation(
                    startDestination = "treatmentHome",
                    route = "treatment"
                ) {
                    composable("treatmentHome") {
                        TreatmentHomeScreen(
                            vm = koinViewModel<TreatmentViewModel>(),
                            onLogout = {
                                authViewModel.logout()
                                navController.navigate("auth") {
                                    popUpTo("treatment") {
                                        inclusive = true
                                    }
                                }
                            },
                            onNavigateHome = {
                                navController.navigate("home") {
                                    popUpTo("treatment") {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }


            }
        }
    }
}


//@OptIn(KoinExperimentalAPI::class)
//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
//    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//    return viewModel(parentEntry)
//}