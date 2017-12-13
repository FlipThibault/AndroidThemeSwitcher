package com.flip.themeswitcher

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.graphics.drawable.Animatable2Compat
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab1 : ImageView = findViewById(R.id.fab1)

        var moonSun = AnimatedVectorDrawableCompat.create(this, R.drawable.moon_sun) as AnimatedVectorDrawableCompat
        var sunMoon = AnimatedVectorDrawableCompat.create(this, R.drawable.sun_moon) as AnimatedVectorDrawableCompat

        fab1.setImageDrawable(moonSun)

        moonSun.registerAnimationCallback(object:Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                super.onAnimationEnd(drawable)
                fab1.setImageDrawable(sunMoon)
                moonSun = AnimatedVectorDrawableCompat.create(this@MainActivity, R.drawable.moon_sun) as AnimatedVectorDrawableCompat
                moonSun.registerAnimationCallback(this)
            }

            override fun onAnimationStart(drawable: Drawable?) {
                super.onAnimationStart(drawable)
            }
        })

        sunMoon.registerAnimationCallback(object:Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                super.onAnimationEnd(drawable)
                fab1.setImageDrawable(moonSun)
                sunMoon = AnimatedVectorDrawableCompat.create(this@MainActivity, R.drawable.sun_moon) as AnimatedVectorDrawableCompat
                sunMoon.registerAnimationCallback(this)
            }

            override fun onAnimationStart(drawable: Drawable?) {
                super.onAnimationStart(drawable)
            }
        })

        fab1.setOnClickListener(object:View.OnClickListener {
            override fun onClick(v: View?) {
                if((fab1.drawable as AnimatedVectorDrawableCompat).isRunning) {
                    Toast.makeText(this@MainActivity, "STAHP!... the animation is still running", Toast.LENGTH_SHORT).show()
                }
                (fab1.drawable as AnimatedVectorDrawableCompat).start()
            }
        })

    }
}
