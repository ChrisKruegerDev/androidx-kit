package app.moviebase.androidx.widget.animation

import android.view.animation.Interpolator

/**
 * The first value 0.2 is the bounce amplitude. The higher value produces more pronounced bounces.
 * The second value 20 is the frequency of the bounces. The higher value creates more wobbles during the animation time period.
 */
class BounceInterpolator(private val amplitude: Double = 1.0, private val frequency: Double = 10.0) : Interpolator {

    override fun getInterpolation(time: Float): Float =
            (-1.0 * Math.pow(Math.E, -time / amplitude) * Math.cos(frequency * time) + 1).toFloat()

}
