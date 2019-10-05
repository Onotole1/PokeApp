import extensions.*

plugins {
	id("com.android.application")
	id("kotlin-android")
	id("kotlin-android-extensions")
	id("androidx.navigation.safeargs.kotlin")
	id("kotlin-kapt")
	id("kotlinx-serialization")
}

android {

	compileSdkVersion(Versions.MAX_API)
	buildToolsVersion = Versions.BUILD_TOOLS_VERSION

	defaultConfig {
		minSdkVersion(Versions.MIN_API)
		targetSdkVersion(Versions.MAX_API)

		applicationId = "com.spitchenko.pokeapp"
		versionCode = Versions.VERSION_CODE
		versionName = Versions.VERSION_NAME

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

		buildConfigFieldString("SERVER_API_URL", "\"https://pokeapi.co/api/v2/\"")

		kapt {
			arguments {
				arg("room.schemaLocation", "$projectDir/schemas")
				arg("room.incremental", "true")
			}
		}
	}

	buildTypes {
		val proguardFiles = arrayOf(
			getDefaultProguardFile("proguard-android.txt"),
			"proguard-rules.pro"
		)

		getByName(BuildTypes.RELEASE) {
			isMinifyEnabled = true
			proguardFiles(*proguardFiles)
		}
		getByName(BuildTypes.DEBUG) {
			applicationIdSuffix = ".debug"
			isDebuggable = true
			isMinifyEnabled = false
			proguardFiles(*proguardFiles)
		}
	}

	dataBinding {
		isEnabled = true
	}
}

dependencies {
	leakCanary()
	androidx()
	dagger()
	room()
	coroutines()
	navigation()
	picasso()
	gson()
	okhttp()
	retrofit()
	timber()
	kotlin()
	lifecycle()
	testing()
	androidTesting()
}