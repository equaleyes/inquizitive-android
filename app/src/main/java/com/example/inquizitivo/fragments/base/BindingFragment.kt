package com.example.inquizitivo.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BindingFragment<B : ViewDataBinding> : Fragment() {

    fun <T> LiveData<T>.observe(observer: (t: T) -> Unit) {
        observe(this@BindingFragment, Observer {
            observer(it)
        })
    }

    fun <T> LiveData<T>.nonNullObserve(observer: (t: T) -> Unit) {
        this.observe(this@BindingFragment, Observer {
            it?.let(observer)
        })
    }

    protected lateinit var binding: B

    @get:LayoutRes
    protected abstract val layout: Int

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        setHasOptionsMenu(true)
        onBindingData()
        return binding.root
    }

    open fun onBindingData() {

    }
}