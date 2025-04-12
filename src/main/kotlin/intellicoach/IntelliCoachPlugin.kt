package intellicoach

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.util.messages.MessageBusConnection
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service(Service.Level.APP)
class IntelliCoachPlugin {

    private val logFile = File(System.getProperty("user.home"), "intelli-coach.log")
    private val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    private var connection: MessageBusConnection? = ApplicationManager.getApplication().messageBus.connect()

    init {
        connection?.subscribe(
            AnActionListener.TOPIC,
            object : AnActionListener {
                override fun beforeActionPerformed(action: AnAction, event: AnActionEvent) {
                    val actionText = action.templatePresentation.text
                    when (event.inputEvent) {
                        is java.awt.event.MouseEvent -> {
                            log("⚡ Action triggered: $actionText by mouse")
                        }
                        is java.awt.event.KeyEvent -> {
                            log("⚡ Action triggered: $actionText by shortcut")
                        }
                        else -> {
                            log("⚡ Action triggered: $actionText by mouse")
                        }
                    }
                }
            }
        )
    }

    private fun log(message: String) {
        val timestamp = LocalDateTime.now().format(timeFormatter)
        logFile.appendText("[$timestamp] $message\n")
    }
}
