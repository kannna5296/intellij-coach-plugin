package intellicoach

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.EditorMouseEvent
import com.intellij.openapi.editor.event.EditorMouseListener
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.application.ApplicationManager
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service(Service.Level.APP)
class IntelliCoachPlugin {

    private val logFile = File(System.getProperty("user.home"), "intelli-coach.log")
    private val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    init {
        val multicaster = EditorFactory.getInstance().eventMulticaster

        multicaster.addEditorMouseListener(object : EditorMouseListener {
            //ファイルの中をクリックしたらその内容をクリックしてくれるだけ
            override fun mouseClicked(event: EditorMouseEvent) {
                log("Mouse clicked: ${event.mouseEvent.point}")
            }
        }, ApplicationManager.getApplication())
    }

    private fun log(message: String) {
        val timestamp = LocalDateTime.now().format(timeFormatter)
        logFile.appendText("[$timestamp] $message\n")
    }
}