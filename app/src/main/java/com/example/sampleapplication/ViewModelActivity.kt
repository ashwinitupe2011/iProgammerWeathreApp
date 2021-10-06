package com.example.sampleapplication

import androidx.lifecycle.ViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class ViewModelActivity<T : ViewModel> : AppCompatActivity() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    protected var isPaused: Boolean = true

    open val fragmentContainerId: Int = 0

    @Suppress("UNCHECKED_CAST")
    protected val viewModel: T by lazy {
        val viewModelClass = this::class.java.getGenericArgument()
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass) as T
    }

    @Suppress("UNCHECKED_CAST")
    private fun Class<*>.getGenericArgument(): Class<ViewModel> {
        return (this.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<ViewModel>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        isPaused = true
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        isPaused = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}
