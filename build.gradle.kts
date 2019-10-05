buildscript {
	mainRepositories()

	dependencies {
		classpath(Dependencies.Build.ANDROID_GRADLE_PLUGIN)
		classpath(Dependencies.Build.KOTLIN_GRADLE_PLUGIN)
		classpath(Dependencies.Build.KOTLIN_EXTENSIONS)
		classpath(Dependencies.Build.GOOGLE_SERVICES)
		classpath(Dependencies.Build.SERIALIZATION)
		classpath(Dependencies.Build.NAVIGATION_SAFE_ARGS)
	}
}

allprojects {
	mainRepositories()
}