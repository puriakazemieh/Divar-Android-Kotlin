plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
    id("convention.android.retrofit")
}

android {
    namespace = "com.kazemieh.network"

}

dependencies {
    implementation(project(":core:secure-shared-pref"))

}