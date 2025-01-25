package dev.mammet.jetpacknewsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.mammet.jetpacknewsapp.domain.usecases.AppEntryUseCases
import dev.mammet.jetpacknewsapp.presentation.navgraph.NavGraph
import dev.mammet.jetpacknewsapp.presentation.onboarding.OnBoardingScreen
import dev.mammet.jetpacknewsapp.presentation.onboarding.OnBoardingViewModel
import dev.mammet.jetpacknewsapp.ui.theme.JetpackNewsAppTheme
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }

        }
        enableEdgeToEdge()

        setContent {
            JetpackNewsAppTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                  val startDestination = viewModel.startDestination
                    NavGraph(
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}
