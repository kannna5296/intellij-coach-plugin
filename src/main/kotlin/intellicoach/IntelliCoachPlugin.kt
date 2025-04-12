package intellicoach

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.EditorMouseEvent
import com.intellij.openapi.editor.event.EditorMouseListener
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.application.ApplicationManager

@Service(Service.Level.APP)
class IntelliCoachPlugin {

    private val logger = Logger.getInstance(IntelliCoachPlugin::class.java)

    init {
        logger.info("✅ IntelliCoachPlugin loaded!")
        println("✅ IntelliCoachPlugin loaded!")

        val multicaster = EditorFactory.getInstance().eventMulticaster

        multicaster.addEditorMouseListener(object : EditorMouseListener {
            //ファイルの中をクリックしたらその内容をクリックしてくれるだけ
            override fun mouseClicked(event: EditorMouseEvent) {
                logger.info("🖱️ Mouse clicked: ${event.mouseEvent.point}")
                println("🖱️ Mouse clicked: ${event.mouseEvent.point}")
            }
        }, ApplicationManager.getApplication())
    }
}