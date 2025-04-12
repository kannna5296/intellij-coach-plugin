package intellicoach

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.editor.event.EditorMouseAdapter
import com.intellij.openapi.editor.event.EditorMouseEvent
import com.intellij.util.messages.MessageBusConnection

@Service
class IntelliCoachPlugin {
    private val connection: MessageBusConnection = ApplicationManager.getApplication().messageBus.connect()

    init {
        subscribeToActions()
        setupKeyListener()
        setupEditorMouseListener() //← ここは必要ならエディタのライフサイクルを追う形に変更
    }

    private fun subscribeToActions() {
        connection.subscribe(AnActionListener.TOPIC, object : AnActionListener {
            override fun beforeActionPerformed(action: AnAction, event: AnActionEvent) {
                println("💡 Action performed: ${action.javaClass.simpleName} / ${event.place}")
            }

            override fun afterActionPerformed(action: AnAction, event: AnActionEvent, result: AnActionResult) {}
            override fun beforeEditorTyping(c: Char, dataContext: DataContext) {}
        })
    }

    private fun setupEditorMouseListener() {
        val editorMouseListener = object : EditorMouseAdapter() {
            override fun mouseClicked(e: EditorMouseEvent) {
                val area = e.area
                val offset = e.mouseEvent.point
                println("🖱 Click at $area on point $offset")
            }
        }

        // 各エディタに個別に登録する必要があるが、ここでは参考実装
        // 実際は EditorFactory.getInstance().eventMulticaster を使ってもOK
    }

    private fun setupKeyListener() {
        val frame = java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
        frame.addKeyEventDispatcher { event ->
            if (event.id == java.awt.event.KeyEvent.KEY_PRESSED) {
                val keyText = java.awt.event.KeyEvent.getKeyText(event.keyCode)
                println("⌨️ Key pressed: $keyText")
            }
            false
        }
    }
}
