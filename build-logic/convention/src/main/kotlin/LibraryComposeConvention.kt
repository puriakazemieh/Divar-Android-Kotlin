import modularization.composeGradleExtension
import modularization.libraryGradle
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class LibraryComposeConvention : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            applyPlugins()
            libraryGradle {
                composeGradleExtension(this)
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply(plugin ="com.android.library")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
        }
    }

}