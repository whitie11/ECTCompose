package auth.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import platform.Foundation.NSNotification
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.UIKit.UIKeyboardDidHideNotification
import platform.UIKit.UIKeyboardDidShowNotification

@Composable
actual fun keyboardVisibilityState(): State<Boolean> {
    var isKeyboardVisible by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        val keyboardDidShowObserver = NSNotificationCenter.defaultCenter.addObserverForName(
            name = UIKeyboardDidShowNotification,
            `object` = null,
            queue = NSOperationQueue.mainQueue
        ) { _: NSNotification? ->
            isKeyboardVisible = true
        }

        val keyboardDidHideObserver = NSNotificationCenter.defaultCenter.addObserverForName(
            name = UIKeyboardDidHideNotification,
            `object` = null,
            queue = NSOperationQueue.mainQueue
        ) { _: NSNotification? ->
            isKeyboardVisible = false
        }

        onDispose {
            NSNotificationCenter.defaultCenter.removeObserver(keyboardDidShowObserver)
            NSNotificationCenter.defaultCenter.removeObserver(keyboardDidHideObserver)
        }
    }

    return remember { mutableStateOf(isKeyboardVisible) }
}