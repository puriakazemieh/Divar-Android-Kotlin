pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven {url = uri("https://srepo.tosantechno.net/repository/maven-group/") }
//        google {
//            content {
//                includeGroupByRegex("com\\.android.*")
//                includeGroupByRegex("com\\.google.*")
//                includeGroupByRegex("androidx.*")
//            }
//        }
//        mavenCentral()
//        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {url = uri("https://srepo.tosantechno.net/repository/maven-group/") }
//        google()
//        mavenCentral()
    }
}

rootProject.name = "Divar"
include(":app")
include(":core:database")
include(":core:utils")
include(":core:network")
include(":core:secure-shared-pref")
include(":core:ui")
include(":core:data")
include(":core:domain")
