plugins {
    id("convention.android.feature")
}
android {
    namespace = "com.kazemieh.Splash"
}

dependencies {
    implementation(libs.androidx.core.splashscreen)
}