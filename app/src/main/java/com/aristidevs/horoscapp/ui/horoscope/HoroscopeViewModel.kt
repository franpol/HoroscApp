package com.aristidevs.horoscapp.ui.horoscope
/**
 * La "inteligencia" de la pantalla
 *
 * Flujo:
 *  (2) Le pide la lista a [HoroscopeProvider]
 *  (4) Convierte esa lista de HoroscopeInfo a HoroscopeModel (los modelos de dominio)
 *      y se los da a MainActivity.
 *
 * Llama directamente a [Repository] (la interfaz en domain/Repository,
 *  a través de su implementación inyectada) para obtener la lista de horóscopos.
 */
import androidx.lifecycle.ViewModel
import com.aristidevs.horoscapp.data.providers.HoroscopeProvider
import com.aristidevs.horoscapp.domain.model.HoroscopeInfo
import com.aristidevs.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel //Asegúra que esta clase ViewModel esté lista para que la inyecten donde la necesiten
//las cosas que están dentro del constructor es lo que se va a necesitar para crear un objeto de esta clase.
//En este caso lo que se necesita es una instancia de HoroscopeProvider
//En resumen, se identifica que se va a inyectar HoroscopeViewModel, y que para funcionar, necesita que Dagger Hilt entregue un HoroscopeProvider".
//Basicamente la clase es la que recibe la inyeccion, y la inyeccion siempre recibe lo que dice el constructor
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) : ViewModel() {
    //Al tener prefijo "_" significa que es privada y al ser declarada coon "val" no puede cambiar la referencia de su objeto, pero si puede cambian el contenido o valor del objeto (el signo)
    //Es necesario que sea privada para que no se pueda cambiar el valor del signo desde afuera
    private val _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    //A diferencia de la anterior, esta variable no tiene un guion bajo y es pública.
    //Es la "puerta de entrada" para que otras clases (como la MainActivity) puedan ver el estado.
    //Pero la diferencia mas importante es que es un stateflow, y eso la hace de sololectura, no es mutable
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    //init es un bloque de inicialización de Kotlin.
    //El código que está dentro de este bloque se ejecuta automáticamente cada vez que se crea una nueva instancia de la clase
    init {
        //obtiene la lista de horoscopos
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }

}