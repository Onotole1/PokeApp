import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.ScriptHandlerScope

fun Project.mainRepositories() = applyMainRepositories(repositories)

fun ScriptHandlerScope.mainRepositories() = applyMainRepositories(repositories)

private fun applyMainRepositories(handler: RepositoryHandler) = with(handler) {
	google()
	jcenter()
}