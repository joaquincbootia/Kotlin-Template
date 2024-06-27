import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uruguayemergencia.network.NetworkModule
import com.uruguayemergencia.network.api.repositories.UserMockRepository
import com.uruguayemergencia.network.models.Result
import com.uruguayemergencia.network.models.MockUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    private val userRepository = UserMockRepository(NetworkModule.userMockService)

    private val _getUserResult = MutableStateFlow<Result<MockUser>>(Result.Loading)
    val getUserResult: MutableStateFlow<Result<MockUser>> get() = _getUserResult

    fun fetchUserData() {
        viewModelScope.launch {
            _getUserResult.value = Result.Loading
            try {
                val response = userRepository.fetchUserData()
                if (response.isSuccessful) {
                    val userData = response.body()
                    if (userData != null) {
                        _getUserResult.value = Result.Success(userData)
                    } else {
                        _getUserResult.value = Result.Error("Empty response")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    _getUserResult.value = Result.Error("Error: $errorBody")
                }
            } catch (e: Exception) {
                _getUserResult.value = Result.Error("Exception: ${e.message}")
            }
        }
    }
}