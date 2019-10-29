plugins {
	`kotlin-dsl`
}

repositories {
	google()
	jcenter()
	maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}

dependencies {
	implementation(gradleApi())
	implementation("com.android.tools.build:gradle:4.0.0-alpha01")
}