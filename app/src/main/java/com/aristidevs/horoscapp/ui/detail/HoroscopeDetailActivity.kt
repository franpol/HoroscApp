package com.aristidevs.horoscapp.ui.detail

/**
 * La Pantalla
 *
 * Flujo:
 *  (1) muestra la UI y el usuario interactúa
 *  (2) observa / interactúa con [HoroscopeDetailViewModel]
 *  (12) observa los cambios en [HoroscopeDetailState] (y actualiza la UI)
 *
 * Es la "pantalla" donde el usuario ve los detalles de un horóscopo.
 * Observa el HoroscopeDetailState actualizado y muestra la predicción al usuario.
 *
 * Responsabilidad: Es la parte visual. Recibe un signo (ej. "Aries") que le llega de la MainActivity (probablemente a través de un Intent).
 * Su trabajo es pedirle al ViewModel la predicción para ese signo y mostrarla cuando la reciba.
 *
 * Intervención de Dagger Hilt: Dagger Hilt inyecta el HoroscopeDetailViewModel en la HoroscopeDetailActivity.
 * Esto permite que la Activity acceda al ViewModel sin tener que crearlo manualmente ni preocuparse de sus dependencias internas.
 *
 * Se ubica en ui porque es la representación visual directa para el usuario.
 */
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.aristidevs.horoscapp.R
import com.aristidevs.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.aristidevs.horoscapp.domain.model.HoroscopeModel
import com.aristidevs.horoscapp.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHoroscopeDetailBinding

    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            // Llamar a este método para simular el comportamiento del botón de retroceso
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction
        val image = when(state.horoscopeModel) {
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius -> R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }
        binding.ivDetail.setImageResource(image)
    }
}