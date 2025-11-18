plugins {
    id("convention.android.application")
    id("convention.android.application.compose")
    id("convention.android.hilt")
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.compose.compiler)
}

android {


    namespace = "com.kazemieh.divar"

    defaultConfig {
        applicationId = "com.kazemieh.divar"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "BaseUrl", properties["BaseUrl"].toString())
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation(project(":core:database"))
//implementation(project(":core:utils"))
    implementation(project(":core:network"))
    implementation(project(":core:secure-shared-pref"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":feature:category"))
    implementation(project(":feature:home"))
    implementation(project(":feature:location"))
    implementation(project(":feature:Splash"))
    implementation(project(":feature:main"))
    implementation(project(":feature:chat"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:search"))
    implementation(project(":feature:ads"))




    implementation(libs.androidx.core.splashscreen)

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
}