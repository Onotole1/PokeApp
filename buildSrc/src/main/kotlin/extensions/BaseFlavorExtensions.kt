package extensions

import com.android.build.gradle.internal.dsl.BaseFlavor

private const val RES_VALUE_STRING = "string"
private const val BUILD_CONFIG_FIELD_INT = "int"
private const val BUILD_CONFIG_FIELD_STRING = "String"

fun BaseFlavor.resValueString(name: String, value: String): Unit = resValue(RES_VALUE_STRING, name, value)

fun BaseFlavor.buildConfigFieldInt(name: String, value: String): Unit = buildConfigField(BUILD_CONFIG_FIELD_INT, name, value)

fun BaseFlavor.buildConfigFieldString(name: String, value: String): Unit = buildConfigField(BUILD_CONFIG_FIELD_STRING, name, value)