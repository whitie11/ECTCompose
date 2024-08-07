package auth.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
actual fun keyboardVisibilityState(): State<Boolean> {
   return remember { mutableStateOf(false) }
}