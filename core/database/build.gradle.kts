plugins {
    id("convention.android.library")
    id("convention.android.hilt")
    id("convention.android.serialization")
    id("convention.android.room")
    id("androidx.room") version ("2.8.2")
}

android {
    namespace = "com.kazemieh.database"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
//    implementation(project(":core:utils"))
}