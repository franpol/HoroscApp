package com.aristidevs.horoscapp.ui.detail
/**
 * La Lógica de la Vista
 *
 * Flujo:
 *  (3) ejecuta [GetPredictionUseCase]
 *  (11) actualiza [HoroscopeDetailState] con [PredictionModel] (o error/cargando)
 *  Para los Datos Estáticos (sin API): puede pedir a [HoroscopeProvider] (si necesita la lista
 *  inicial de horóscopos o datos predefinidos, a menudo vía RepositoryImpl o un UseCase específico)
 *
 * Responsabilidad: Actúa como intermediario entre la Activity y la lógica de negocio.
 * Recibe el signo de la Activity.
 * Su tarea es invocar el caso de uso para obtener la predicción,
 *  manejar el estado de la UI (ej. "cargando", "mostrar predicción", "error") y exponerlo a la Activity.
 *
 * Intervención de Dagger Hilt: Dagger Hilt inyecta el GetPredictionUseCase en el constructor
 *  del HoroscopeDetailViewModel.
 * El ViewModel solo sabe que necesita un GetPredictionUseCase para pedir la predicción; no sabe
 *  cómo se crea ese UseCase.
 *
 * Es el "intermediario" entre la lógica de negocio y la pantalla, manejando los datos para la UI.
 * Actualiza su HoroscopeDetailState con el PredictionModel (o un estado de error/cargando).
 *
 * Se ubica en ui/detail porque su propósito principal es preparar y gestionar los datos para la
 *  Activity o Fragment correspondiente a esta interfaz de usuario.
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aristidevs.horoscapp.domain.model.HoroscopeModel
import com.aristidevs.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase): ViewModel() {
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope:HoroscopeModel

    fun getHoroscope(sign: String) {

        viewModelScope.launch {
            //hilo principal
            _state.value = HoroscopeDetailState.Loading
            val result = withContext (Dispatchers.IO) { getPredictionUseCase(sign) } //hilo secundario
            if(result!=null) {
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign)
            } else {
                _state.value = HoroscopeDetailState.Error("Ha ocurrido un error, intentelo mas tarde")
            }
            //hilo principal
        }
    }
}