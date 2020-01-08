package extensions

import Dependencies
import org.gradle.api.artifacts.dsl.DependencyHandler

private const val DEBUG_IMPLEMENTATION = "debugImplementation"
private const val IMPLEMENTATION = "implementation"
private const val KAPT = "kapt"
private const val TEST_IMPLEMENTATION = "testImplementation"
private const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"

fun DependencyHandler.leakCanary() {
    add(DEBUG_IMPLEMENTATION, Dependencies.Debug.LEAKCANARY_DEBUG)
}

fun DependencyHandler.androidx() {
    add(IMPLEMENTATION, Dependencies.Main.Androidx.CORE)
    add(IMPLEMENTATION, Dependencies.Main.Androidx.MATERIAL)
    add(IMPLEMENTATION, Dependencies.Main.Androidx.RECYCLER_VIEW)
    add(IMPLEMENTATION, Dependencies.Main.Androidx.CONSTRAINT_LAYOUT)
    add(IMPLEMENTATION, Dependencies.Main.Androidx.SWIPE_REFRESH_LAYOUT)
    add(IMPLEMENTATION, Dependencies.Main.Androidx.FRAGMENT)
}

fun DependencyHandler.dagger() {
    add(KAPT, Dependencies.Main.Dagger.COMPILER)
    add(KAPT, Dependencies.Main.Dagger.PROCESSOR)
    add(IMPLEMENTATION, Dependencies.Main.Dagger.ANDROID)
    add(IMPLEMENTATION, Dependencies.Main.Dagger.SUPPORT)
    add(IMPLEMENTATION, Dependencies.Main.Dagger.DAGGER)
}

fun DependencyHandler.room() {
    add(KAPT, Dependencies.Main.Room.COMPILER)
    add(IMPLEMENTATION, Dependencies.Main.Room.RUNTIME)
    add(IMPLEMENTATION, Dependencies.Main.Room.COROUTINES)
}

fun DependencyHandler.navigation() {
    add(IMPLEMENTATION, Dependencies.Main.Navigation.UI)
    add(IMPLEMENTATION, Dependencies.Main.Navigation.FRAGMENT)
}

fun DependencyHandler.lifecycle() {
    add(KAPT, Dependencies.Main.Lifecycle.COMPILER)
    add(IMPLEMENTATION, Dependencies.Main.Lifecycle.EXTENSIONS)
}

fun DependencyHandler.glide() {
    add(IMPLEMENTATION, Dependencies.Main.Glide.GLIDE)
    add(KAPT, Dependencies.Main.Glide.COMPILER)
}

fun DependencyHandler.gson() {
    add(IMPLEMENTATION, Dependencies.Main.GSON)
}

fun DependencyHandler.okhttp() {
    add(IMPLEMENTATION, Dependencies.Main.OkHttp.LOGGING)
    add(IMPLEMENTATION, Dependencies.Main.OkHttp.URLCONNECTION)
    add(IMPLEMENTATION, Dependencies.Main.OkHttp.OKHTTP)
}

fun DependencyHandler.retrofit() {
    add(IMPLEMENTATION, Dependencies.Main.Retrofit.RETROFIT)
    add(IMPLEMENTATION, Dependencies.Main.Retrofit.GSON_CONVERTER)
}

fun DependencyHandler.timber() {
    add(IMPLEMENTATION, Dependencies.Main.TIMBER)
}

fun DependencyHandler.kotlin() {
    add(IMPLEMENTATION, Dependencies.Main.Kotlin.STANDARD_LIBRARY)
    add(IMPLEMENTATION, Dependencies.Main.Kotlin.SERIALIZATION)
}

fun DependencyHandler.testing() {
    add(TEST_IMPLEMENTATION, Dependencies.Test.JUNIT)
    add(TEST_IMPLEMENTATION, Dependencies.Test.MOCKITO_KOTLIN)
    add(TEST_IMPLEMENTATION, Dependencies.Test.TRUTH)
    add(TEST_IMPLEMENTATION, Dependencies.Test.ANDROIDX_CORE_TESTING)
}

fun DependencyHandler.androidTesting() {
    add(ANDROID_TEST_IMPLEMENTATION, Dependencies.Test.JUNIT)
    add(ANDROID_TEST_IMPLEMENTATION, Dependencies.Test.SUPPORT_TEST_RULES)
    add(ANDROID_TEST_IMPLEMENTATION, Dependencies.Test.SUPPORT_TEST_RUNNER)
    add(ANDROID_TEST_IMPLEMENTATION, Dependencies.Test.ROOM)
    add(ANDROID_TEST_IMPLEMENTATION, Dependencies.Test.ANDROIDX_CORE_TESTING)
}

fun DependencyHandler.coroutines() {
    add(IMPLEMENTATION, Dependencies.Main.Coroutines.COROUTINES)
    add(IMPLEMENTATION, Dependencies.Main.Coroutines.COROUTINES_ANDROID)
}

fun DependencyHandler.savedState() {
    add(IMPLEMENTATION, Dependencies.Main.SAVED_STATE)
}