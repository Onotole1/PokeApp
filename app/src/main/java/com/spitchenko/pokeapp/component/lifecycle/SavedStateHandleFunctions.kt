package com.spitchenko.pokeapp.component.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> SavedStateHandle.delegate(default: () -> T): ReadWriteProperty<Any, T> =
    SavedStateDelegateDefault(this, default)

fun <T> SavedStateHandle.savedStateLiveData(): ReadOnlyProperty<Any, MutableLiveData<T>> =
    SavedStateLiveDataDelegate(this)

fun <T> SavedStateHandle.savedStateLiveData(default: T): ReadOnlyProperty<Any, MutableLiveData<T>> =
    SavedStateLiveDataDelegateDefault(this, default)

private class SavedStateLiveDataDelegate<T>(
    private val savedStateHandle: SavedStateHandle
) : ReadOnlyProperty<Any, MutableLiveData<T>> {

    override fun getValue(thisRef: Any, property: KProperty<*>): MutableLiveData<T> =
        savedStateHandle.getLiveData(property.name)
}

private class SavedStateLiveDataDelegateDefault<T>(
    private val savedStateHandle: SavedStateHandle,
    private val defaultValue: T
) : ReadOnlyProperty<Any, MutableLiveData<T>> {

    override fun getValue(thisRef: Any, property: KProperty<*>): MutableLiveData<T> =
        savedStateHandle.getLiveData(property.name, defaultValue)
}

private class SavedStateDelegateDefault<T>(
    private val savedStateHandle: SavedStateHandle,
    private val defaultValue: () -> T
) : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        savedStateHandle[property.name] ?: defaultValue().let {
            setValue(thisRef, property, it)
            it
        }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        savedStateHandle[property.name] = value
    }
}