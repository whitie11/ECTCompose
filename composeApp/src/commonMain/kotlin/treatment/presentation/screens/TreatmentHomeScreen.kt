package treatment.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import common.presentation.uiComponents.Blue
import common.presentation.uiComponents.GradientBox
import common.presentation.uiComponents.Tail600
import referal.presentation.ReferralViewModel
import referal.presentation.events.ReferralUIEvent
import referal.presentation.screens.AllReferrals
import referal.presentation.screens.NewReferralScreen
import referal.presentation.screens.PendingReferrals
import referal.presentation.screens.ReferralMenu
import referal.presentation.stateObjs.ReferralUIState
import treatment.presentation.TreatmentViewModel
import treatment.presentation.stateObjs.TreatmentUIState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreatmentHomeScreen(
    onNavigateHome: () -> Unit,
    onLogout: () -> Unit,
    vm: TreatmentViewModel
) {
    val scope = rememberCoroutineScope()
//    val onTreatmentEvent = vm::onTreatmentEvent
//    val onCancel = {
//        onReferralEvent(ReferralUIEvent.showMenu)
//    }
    val onCancel = {}
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
                val treatmentUIState = vm.treatmentUIState.collectAsState()
                when (treatmentUIState.value) {
                    TreatmentUIState.PreChecks -> {
                        PreTreatmentChecks(onCancel)
                    }

                    TreatmentUIState.TreatmentHome -> {
                        TreatmentHomeUI(onCancel)
                    }
                }

            }
        }
    }
}
