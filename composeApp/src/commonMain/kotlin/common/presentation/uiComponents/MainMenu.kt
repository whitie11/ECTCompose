package common.presentation.uiComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainMenu(
    onNavigateReferral: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.fillMaxSize()
    ) {
        Text("Main Menu")

        Button(
            onClick = onNavigateReferral) {
            Text("Referrals")
        }
    }
}