package homeFeature.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import common.presentation.uiComponents.Blue
import common.presentation.uiComponents.GradientBox
import common.presentation.uiComponents.MainMenu
import common.presentation.uiComponents.Tail600
import homeFeature.presentation.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: HomeViewModel,
    onLogout: () -> Unit,
    onNavigateToScreen1: () -> Unit,
    onNavigateToScreen2: () -> Unit,
    onNavigateReferral: () -> Unit,
    onNavigateTreatment: () -> Unit,
) {
    val isLoggedIn = vm.isLoggedInState.value

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                MainMenu(onNavigateReferral)
            }
        },
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Blue,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text(vm.screenTitle)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                            colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description",
                            )
                        }
                    },
                    actions = {
                        Button(onClick = onLogout) {
                            Text("Logout")
                        }
                    },
                )
            },

            bottomBar = {
                BottomAppBar(
                    containerColor = Tail600,
                    contentColor = Color.White,
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar",
                    )
                }
            },
//            floatingActionButton = {
//                FloatingActionButton(onClick = { presses++ }) {
//                    Icon(Icons.Default.Add, contentDescription = "Add")
//                }
//            }
        ) {
            GradientBox(modifier = Modifier.fillMaxSize()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Home Screen")
                    Text("userID = " + isLoggedIn.userID.toString())
                    Text("username = " + isLoggedIn.username)
                    Text("user Role = " + isLoggedIn.role)

//                    Button(onClick = onNavigateToScreen1) {
//                        Text("Navigate Screen One")
//                    }
//                    Spacer(modifier = Modifier.Companion.size(4.dp))
//                    Button(onClick = onNavigateToScreen2) {
//                        Text("Navigate Screen Two")
//                    }
                    Spacer(modifier = Modifier.Companion.size(4.dp))
                    Button(onClick = onNavigateReferral) {
                        Text("Referrals")
                    }
                    Spacer(modifier = Modifier.Companion.size(4.dp))
                    Button(onClick = {}) {
                        Text("Clinic List")
                    }
//                    Spacer(modifier = Modifier.Companion.size(4.dp))
//                    Button(onClick = {}) {
//                        Text("Assessments")
//                    }
                    Spacer(modifier = Modifier.Companion.size(4.dp))
                    Button(onClick = onNavigateTreatment ) {
                        Text("Treatment")
                    }
                    Spacer(modifier = Modifier.Companion.size(10.dp))
                    Button(onClick = onLogout) {
                        Text("Logout")
                    }
                }
            }
        }
    }
}