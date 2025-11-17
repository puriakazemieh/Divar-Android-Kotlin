package com.kazemieh.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.usecase.location.GetUserCityUseCase
import com.kazemieh.ui.model.UiMessage
import com.kazemieh.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getUserCityUseCase: GetUserCityUseCase
) : BaseViewModel<SplashUiState, SplashUiEvent>() {

    init {
        getUserCity()
    }

    private fun getUserCity() {
        viewModelScope.launch {
            getUserCityUseCase.invoke().collect {
                it.onSuccess {
                    setState { copy(userIsSelectedCity = true) }
                }.onFailure { apiError ->
                    setState { copy(userIsSelectedCity = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
//                currentState.userIsSelectedCity.dLog("userIsSelectedCity: ")
            }
        }
    }

    override fun createInitialState() = SplashUiState()

    override fun onTriggerEvent(event: SplashUiEvent) {
    }

}
