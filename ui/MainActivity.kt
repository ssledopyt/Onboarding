@file:Suppress("TooManyFunctions")

package ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.MenuProvider
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import NomiaUnauthorizedNavHost
import NomiaThemeMaterial3
import useDarkTheme
import LocalResourcesProvider
import R
import ResourcesProvider
import components.*
import components.AuthorizedNavHost
import model.AppStartDestination
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    @Suppress("LongMethod")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val isTablet = resources.getBoolean(R.bool.isTablet)
        super.onCreate(savedInstanceState)

        requestedOrientation = if (isTablet) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass

            val applicationViewModel: ApplicationViewModel = hiltViewModel()
            val useDarkTheme = false
            val appStartDestination by applicationViewModel.appStartDestination.collectAsState()

            CompositionLocalProvider(
                LocalResourcesProvider provides resourcesProvider,
            ) {
                NomiaThemeMaterial3(useDarkTheme = useDarkTheme) {
                    Surface(
                        Modifier
                            .navigationBarsPadding()
                            .imePadding()
                    ) {
                        val systemUiController = rememberSystemUiController()
                        val surfaceColor = MaterialTheme.colorScheme.surface
                        val navBarColor =
                            MaterialTheme.colorScheme.surfaceColorAtElevation(NavigationBarDefaults.Elevation)

                        LaunchedEffect(useDarkTheme) {
                            systemUiController.run {
                                setStatusBarColor(color = surfaceColor)
                                setNavigationBarColor(color = navBarColor)
                            }
                        }

                        when (appStartDestination) {
                            AppStartDestination.Unauthorized ->
                                UnauthorizedNavHost(widthSizeClass = widthSizeClass)

                            AppStartDestination.Authorized ->
                                AuthorizedNavHost(widthSizeClass = widthSizeClass)
                        }
                    }
                }
            }
        }
    }
}
