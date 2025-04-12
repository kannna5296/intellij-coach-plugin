package intellicoach

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class MyStartupActivity : ProjectActivity {
    private val logger = Logger.getInstance(MyStartupActivity::class.java)

    override suspend fun execute(project: Project) {
        logger.info("✅ MyStartupActivity: 起動しました！")
        println("✅ MyStartupActivity: 起動しました！")

        // ここで明示的にロードすることで IntelliCoachPlugin の init が呼ばれる
        val plugin = project.getService(IntelliCoachPlugin::class.java)
        println("👀 IntelliCoachPlugin loaded by MyStartupActivity: $plugin")
    }
}