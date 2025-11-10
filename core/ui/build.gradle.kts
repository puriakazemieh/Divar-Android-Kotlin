plugins {
//    id("convention.android.feature")
//    alias(libs.plugins.compose.compiler)
    id("org.jetbrains.kotlin.plugin.compose")
    id("convention.android.library")
    id("convention.android.library.compose")
    id("convention.android.hilt")
    id("convention.android.serialization")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.kazemieh.ui"
}

dependencies {
//    implementation(project(":core:utils"))
    implementation(project(":core:domain"))
}