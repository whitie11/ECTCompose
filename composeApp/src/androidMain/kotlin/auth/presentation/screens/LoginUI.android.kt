package auth.presentation.screens

import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp


//@Composable
//actual fun keyboardVisibilityState(): State<Boolean> {
//    val context = LocalContext.current
//    val view = LocalView.current
//
//    val density = LocalDensity.current
//    val heightThresholdDp = 100.dp // Consider keyboard visible if height changes by more than this
//
//    val heightThresholdPx = with(density) { heightThresholdDp.toPx() }
//
//    var isKeyboardVisible by remember { mutableStateOf(false) }
//
//    DisposableEffect(view) {
//        val activity = context as Activity
//        val rootView = activity.window.decorView
//
//        val rect = Rect()
//        val listener = ViewTreeObserver.OnGlobalLayoutListener {
//            rootView.getWindowVisibleDisplayFrame(rect)
//            val screenHeight = rootView.height
//            val keypadHeight = screenHeight - rect.bottom
//            Log.d("Ian", "keypadHeight = " + keypadHeight + "screenHeight = " + screenHeight )
//            isKeyboardVisible = keypadHeight > 500 //heightThresholdPx
//
//        }
//
//        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
//
//        onDispose {
//            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
//        }
//    }
//    Log.d("Ian", "visible = " + isKeyboardVisible  )
//    return remember { mutableStateOf(isKeyboardVisible) }
//}