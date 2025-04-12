package intellicoach

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class IntelliCoachStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        // ここで明示的にロードすることで IntelliCoachPlugin の init が呼ばれる
        val plugin = project.getService(IntelliCoachPlugin::class.java)
    }
}
