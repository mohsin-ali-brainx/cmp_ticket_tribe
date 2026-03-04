package com.brainx.ticket_tribe.di

import com.brainx.ticket_tribe.presentation.screens.onboarding.viewmodel.OnboardingViewModel
import com.brainx.utils_extensions.enums.CoroutineDispatcherModuleEnums
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<OnboardingViewModel>{
        OnboardingViewModel(
            get(named(CoroutineDispatcherModuleEnums.IO.dispatcherName)),
            get())
    }
}