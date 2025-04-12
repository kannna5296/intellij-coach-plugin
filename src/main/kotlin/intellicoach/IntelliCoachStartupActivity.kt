package intellicoach

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class MyStartupActivity : StartupActivity {
    private val logger = Logger.getInstance(MyStartupActivity::class.java)

    override fun runActivity(project: Project) {
        logger.info("✅ MyStartupActivity: 起動しました！")
        println("✅ MyStartupActivity: 起動しました！")
    }
}