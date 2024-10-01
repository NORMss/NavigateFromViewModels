package com.norm.mynavigatefromviewmodels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import java.util.UUID
import com.norm.mynavigatefromviewmodels.ui.theme.MyNavigateFromViewModelsTheme
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNavigateFromViewModelsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val navigator = koinInject<Navigator>()
                    NavHost(
                        navController = navController,
                        startDestination = navigator.startDestination,
                    ) {
                        navigation<Destination.AuthGraph>(
                            startDestination = Destination.LoginScreen
                        ) {
                            composable<Destination.LoginScreen> {
                                val viewModel = koinViewModel<LoginViewModel>()
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Button(
                                        onClick = viewModel::login
                                    ) {
                                        Text(
                                            text = "Login"
                                        )
                                    }
                                }
                            }
                            navigation<Destination.HomeGraph>(
                                startDestination = Destination.LoginScreen
                            ) {
                                composable<Destination.LoginScreen> {
                                    val viewModel = koinViewModel<HomeViewModel>()
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Button(
                                            onClick = {
                                                viewModel.navigateToDetail(
                                                    UUID.randomUUID().toString()
                                                )
                                            }
                                        ) {
                                            Text(
                                                text = "Go to detail"
                                            )
                                        }
                                    }
                                }
                                composable<Destination.DetailScreen> {
                                    val viewModel = koinViewModel<DetailViewModel>()
                                    val args = it.toRoute<Destination.DetailScreen>()
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Text(
                                            text = "ID: ${args.id}"
                                        )
                                        Button(
                                            onClick = viewModel::goBack
                                        ) {
                                            Text(
                                                text = "Go back"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}