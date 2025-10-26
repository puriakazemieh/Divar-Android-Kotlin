import modularization.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RetrofitConvention : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            applyDependencies()
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            implementation(libs.findLibrary("retrofit").get())
            implementation(libs.findLibrary("okhttp").get())
            implementation(libs.findLibrary("okhttp.logging").get())
        }
    }


}