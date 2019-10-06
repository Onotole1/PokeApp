plugins {
	`kotlin-dsl`
}

repositories {
	google()
	jcenter()
}

dependencies {
	implementation(gradleApi())
	implementation("com.android.tools.build:gradle:3.5.1")
}