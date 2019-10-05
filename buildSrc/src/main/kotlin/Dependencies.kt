object Dependencies {

	object Build {

		const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:${Versions.GRADLE}"
		const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
		const val KOTLIN_EXTENSIONS = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.KOTLIN}"
		const val GOOGLE_SERVICES = "com.google.gms:google-services:${Versions.GOOGLE_SERVICES}"
		const val SERIALIZATION = "org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}"
		const val NAVIGATION_SAFE_ARGS = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION_SAFE_ARGS_PLUGIN}"
	}

	object Debug {

		const val LEAKCANARY_DEBUG = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAKCANARY}"
	}

	object Main {

		object Kotlin {

			const val STANDARD_LIBRARY = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
			const val SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.KOTLINX_SERIALIZATION_RUNTIME}"
		}

		object Androidx {

			const val CORE = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE}"
			const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
			const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}"
			const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
			const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT}"
			const val FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_VERSION}"
		}

		object Dagger {

			const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
			const val COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
			const val ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER}"
			const val SUPPORT = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"
			const val PROCESSOR = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
		}

		object Room {

			const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
			const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
			const val RX_JAVA2 = "androidx.room:room-rxjava2:${Versions.ROOM}"
		}

		object Navigation {

			const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
			const val UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
		}

		const val PICASSO = "com.squareup.picasso:picasso:${Versions.PICASSO}"
		const val GSON = "com.google.code.gson:gson:${Versions.GSON}"

		object OkHttp {

			const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OK_HTTP}"
			const val LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP}"
			const val URLCONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.OK_HTTP}"
			const val DOWNLOADER = "com.jakewharton.picasso:picasso2-okhttp3-downloader:${Versions.OK_HTTP_DOWNLOADER}"
		}

		object Retrofit {

			const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
			const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
			const val RX_JAVA_2_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}"
		}

		const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

		object Lifecycle {

			const val EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_EXTENSION}"
			const val COMPILER = "androidx.lifecycle:lifecycle-compiler:${Versions.LIFECYCLE_EXTENSION}"
		}

		object Coroutines {

			const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
			const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"
		}
	}

	object Test {

		const val JUNIT = "junit:junit:${Versions.JUNIT}"
		const val ANDROIDX_CORE_TESTING = "androidx.arch.core:core-testing:${Versions.CORE_TESTING}"
		const val SUPPORT_TEST_RUNNER = "androidx.test:runner:${Versions.SUPPORT_TEST_RUNNER}"
		const val SUPPORT_TEST_RULES = "androidx.test:rules:${Versions.SUPPORT_TEST_RULES}"
		const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO_KOTLIN}"
		const val TRUTH = "com.google.truth:truth:${Versions.TRUTH}"
		const val ROOM = "androidx.room:room-testing:${Versions.ROOM}"
	}
}