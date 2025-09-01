package com.aristidevs.horoscapp.ui.core.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * Esta clase es un "oyente" de gestos de deslizamiento (swipe).
 *
 * Hereda de 'View.OnTouchListener', lo que le permite detectar toques en la pantalla.
 * La palabra clave 'open' significa que otras clases pueden heredar de ella para
 * sobrescribir el comportamiento de los métodos 'onSwipe', por ejemplo, para
 * definir qué sucede al deslizar a la derecha, izquierda, etc.
 */
open class OnSwipeTouchListener(context: Context) : View.OnTouchListener {

    // companion object es un bloque de código que contiene propiedades y funciones
    // que pertenecen a la clase, no a una instancia de la clase.
    // Es como tener variables estáticas o constantes en otros lenguajes.
    companion object {
        // SWIPE_THRESHOLD: La distancia mínima en píxeles que el usuario debe
        // deslizar el dedo para que se considere un 'swipe'.
        private const val SWIPE_THRESHOLD = 100

        // SWIPE_VELOCITY_THRESHOLD: La velocidad mínima con la que el usuario
        // debe deslizar el dedo para que se considere un 'swipe'.
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    // GestureDetector es una clase de Android que ayuda a detectar gestos.
    // La declaramos como 'private' para que solo esta clase pueda acceder a ella.
    private val gestureDetector: GestureDetector

    // El bloque 'init' se ejecuta inmediatamente después de que se crea
    // una instancia de la clase. Es donde inicializamos nuestro 'gestureDetector'.
    init {
        // Le pasamos el 'context' (información sobre la app) y una instancia
        // de nuestra clase interna 'GestureListener' para que sepa qué gestos debe escuchar.
        gestureDetector = GestureDetector(context, GestureListener())
    }

    // Este es el método principal que se llama cada vez que el usuario toca la vista.
    // Por ser 'override', significa que estamos implementando un método de 'View.OnTouchListener'.
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        // Aquí le decimos a nuestro 'gestureDetector' que procese el evento de toque.
        // Él se encargará de determinar si el toque es parte de un 'swipe' o no.
        return gestureDetector.onTouchEvent(event)
    }

    /**
     * Esta es una clase interna. Solo existe dentro de 'OnSwipeTouchListener'.
     * 'inner' le permite acceder a las propiedades y métodos de la clase principal.
     * Hereda de 'GestureDetector.SimpleOnGestureListener', que es una clase
     * que nos da métodos listos para sobrescribir (como 'onFling', 'onDown', etc.)
     */
    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        // Este método se llama cada vez que el usuario presiona la pantalla.
        // Debe devolver 'true' para indicar que hemos manejado el evento.
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        /**
         * Este es el corazón de la clase. Se llama cuando el usuario realiza un gesto de 'flinging'
         * (un movimiento rápido y de liberación, como un lanzamiento).
         *
         * @param e1: El primer toque (cuando el dedo tocó la pantalla).
         * @param e2: El segundo toque (la posición final del dedo).
         * @param velocityX: La velocidad del movimiento en el eje horizontal (X).
         * @param velocityY: La velocidad del movimiento en el eje vertical (Y).
         */
        override fun onFling(
            e1: MotionEvent?, // El primer toque, puede ser nulo, por eso lleva '?'
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                // Aquí se calcula la diferencia de posición entre el inicio y el final del gesto.
                // Como 'e1' es un tipo 'MotionEvent?', se usa '!!' para asumir que no es nulo.
                // Esta es una práctica riesgosa.
                val diffY = e2.y - e1!!.y
                val diffX = e2.x - e1.x

                // Se usa 'Math.abs' para obtener el valor absoluto de la diferencia,
                // ignorando si el movimiento es positivo o negativo.
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    // Si el movimiento horizontal es mayor que el vertical, significa que es un 'swipe' horizontal.
                    // Se verifica que la distancia y la velocidad superen los umbrales definidos.
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            // Si la diferencia en X es positiva, el 'swipe' es hacia la derecha.
                            onSwipeRight()
                        } else {
                            // Si es negativa, es hacia la izquierda.
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    // Si el movimiento vertical es mayor que el horizontal, es un 'swipe' vertical.
                    // Se verifica que la distancia y la velocidad superen los umbrales.
                    if (diffY > 0) {
                        // Si la diferencia en Y es positiva, el 'swipe' es hacia abajo.
                        onSwipeBottom()
                    } else {
                        // Si es negativa, es hacia arriba.
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                // Un bloque 'try-catch' se usa para manejar cualquier error que pueda ocurrir,
                // como un 'NullPointerException' si 'e1' fuera nulo.
                exception.printStackTrace()
            }

            return result
        }


    }

    // Estos son los métodos que las clases que hereden de 'OnSwipeTouchListener' pueden
    // sobrescribir para definir la acción que quieren ejecutar.
    // Son 'open' porque pueden ser sobrescritos. Por defecto, no hacen nada.
    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeTop() {}

    open fun onSwipeBottom() {}
}