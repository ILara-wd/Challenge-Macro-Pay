package mx.macropay.challenge.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.macropay.challenge.data.model.entity.Movie
import mx.macropay.challenge.data.model.network.AuthRepository
import mx.macropay.challenge.data.model.network.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val loginFlow: MutableLiveData<Resource<FirebaseUser>?> = MutableLiveData()
    val signupFlow: MutableLiveData<Resource<FirebaseUser>?> = MutableLiveData()
    var messageError: MutableLiveData<String>? = MutableLiveData()

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        loginFlow.value = Resource.Loading
        if (email.isNotEmpty() && password.isNotEmpty()) {
            val result = repository.login(email, password)
            loginFlow.value = result
        } else {
            messageError?.value = "Todos los campos son requeridos"
        }

    }

    fun signupUser(name: String, email: String, password: String) = viewModelScope.launch {
        signupFlow.value = Resource.Loading
        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
            val result = repository.signup(name, email, password)
            signupFlow.value = result
        } else {
            messageError?.value = "Todos los campos son requeridos"
        }
    }

    fun logout() {
        repository.logout()
        loginFlow.value = null
        signupFlow.value = null
    }
}
