package com.brainx.ticket_tribe

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brainx.utils_extensions.setActivityProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeEvent()
        setContent {
            SetDeviceEdgeToEdge()
            App()
        }
    }

    override fun onDestroy() {
        setActivityProvider { null }
        super.onDestroy()
    }

    private fun init(){
        setActivityProvider { this }
    }

    @Composable
    private fun SetDeviceEdgeToEdge(){
        val darkColor = Color.TRANSPARENT
        val lightColor = Color.TRANSPARENT

        val isDarkTheme = isSystemInDarkTheme()
        enableEdgeToEdge(
            statusBarStyle = if(!isDarkTheme){
                SystemBarStyle.dark(darkColor.hashCode())
            }
            else
                SystemBarStyle.light(lightColor.hashCode(), lightColor.hashCode()),

            navigationBarStyle = if(!isDarkTheme){
                SystemBarStyle.dark(darkColor.hashCode())
            }
            else
            {
                SystemBarStyle.light(lightColor.hashCode(), lightColor.hashCode())
            }
        )
    }

    private fun observeEvent(){
//        lifecycleScope.launch(Dispatchers.Main) {
//            Events.baseEventFlow.collectLatest {
//                when(it){
//                    is BaseUiEvents.CloseApp->{
//                        this@MainActivity.finish()
//                    }
//                    is BaseUiEvents.UpdateSystemColors->{
//                        enableEdgeToEdge(
//                            statusBarStyle = SystemBarStyle.light(it.statusBarColor.toArgb(), it.statusBarColor.toArgb()),
//                            navigationBarStyle = SystemBarStyle.light(it.navBarColor.toArgb(),it.navBarColor.toArgb())
//                        )
//                    }
//                    else->Unit
//                }
//            }
//        }
    }

}


@Preview
@Composable
fun AppAndroidPreview() {
    App()
}