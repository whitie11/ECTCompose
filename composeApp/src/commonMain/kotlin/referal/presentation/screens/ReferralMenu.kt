package referal.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import referal.presentation.events.ReferralUIEvent

@Composable
fun ReferralMenu(
    onReferralEvent: (ReferralUIEvent) -> Unit,
    onNavigateHome: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {onReferralEvent(ReferralUIEvent.showNewReferral)}) {
            Text("New Referral")
        }
        Button(onClick = { onReferralEvent(ReferralUIEvent.showPending) }) {
            Text("List Pending Referrals")
        }
        Button(onClick = { onReferralEvent(ReferralUIEvent.showAll) }) {
            Text("List All Referrals")
        }
        Spacer(modifier = Modifier.Companion.size(16.dp))
        Button(onClick = { onNavigateHome() }) {
            Text("Return to Home Page")
        }
    }
}