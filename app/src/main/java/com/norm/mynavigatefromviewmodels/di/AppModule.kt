package com.norm.mynavigatefromviewmodels.di

import com.norm.mynavigatefromviewmodels.DefaultNavigator
import com.norm.mynavigatefromviewmodels.Destination
import com.norm.mynavigatefromviewmodels.DetailViewModel
import com.norm.mynavigatefromviewmodels.HomeViewModel
import com.norm.mynavigatefromviewmodels.LoginViewModel
import com.norm.mynavigatefromviewmodels.Navigator
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<Navigator> {
        DefaultNavigator(startDestination = Destination.AuthGraph)
    }

    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}