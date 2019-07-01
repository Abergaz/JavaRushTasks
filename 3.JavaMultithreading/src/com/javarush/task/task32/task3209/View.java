package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    //панель с двумя вкладками.
    private JTabbedPane tabbedPane = new JTabbedPane();

    //компонент для визуального редактирования html. Он будет размещен на первой вкладке
    private JTextPane htmlTextPane = new JTextPane();

    //компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое)на второй странице.

    //компонент для отслежевания отмены операций
    private UndoManager undoManager = new UndoManager();

    private UndoListener undoListener = new UndoListener(undoManager);

    private JEditorPane plainTextPane = new JEditorPane();

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Получи из события команду с помощью метода getActionCommand(). Это будет обычная строка.
        //По этой строке ты можешь понять какой пункт меню создал данное событие.
        String command = e.getActionCommand();
        switch (command) {
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
    }

    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar() {
        //Создавать новый объект типа JMenuBar. Это и будет наша панель меню.
        JMenuBar jMenuBar = new JMenuBar();
        //С помощью MenuHelper инициализировать меню в следующем порядке: Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь.
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        //Добавлять в верхнюю часть панели контента текущего фрейма нашу панель меню, аналогично тому, как это мы делали с панелью вкладок.
        getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollHtmlTextPane = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", jScrollHtmlTextPane);
        JScrollPane jScrollPlainTextPane = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", jScrollPlainTextPane);
        tabbedPane.setPreferredSize(new Dimension(200, 400));
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }


    //Этот метод вызывается, когда произошла смена выбранной вкладки.
    public void selectedTabChanged() {

        if (tabbedPane.getSelectedIndex() == 0) {
            //Если выбрана вкладка с индексом 0 (html вкладка), значит нам нужно получить текст из plainTextPane и установить его в контроллер с помощью метода setPlainText.
            controller.setPlainText(plainTextPane.getText());
        } else {
            //Если выбрана вкладка с индексом 1 (вкладка с html текстом), то необходимо получить текст у контроллера с помощью метода getPlainText() и установить его в панель plainTextPane.
            plainTextPane.setText(controller.getPlainText());
        }
        //бросить правки (вызвать метод resetUndo представления).
        resetUndo();
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    //можем ли отменить дейчтвие
    public boolean canUndo() {
        return undoManager.canUndo();
    }

    //можем ли повторить действия
    public boolean canRedo() {
        return undoManager.canRedo();
    }

    //отменяет последнее действие. Реализуй его используя undoManager
    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    //возвращает ранее отмененное действие
    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    //который должен сбрасывать все правки в менеджере undoManager.
    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    //Пункты меню, отвечающие за стиль, шрифт, цвет и т.д. должны быть доступны только тогда, когда в нашем редакторе выбрана первая вкладка.
    public boolean isHtmlTabSelected() {
        //Он должен возвращать true, если выбрана вкладка, отображающая html в панели вкладок (подсказка: ее индекс 0).
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    //должен получать документ у контроллера и устанавливать его в панель редактирования htmlTextPane.
    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    //должен показывать диалоговое окно с информацией о программе. Информацию придумай сам, а вот тип сообщения должен быть JOptionPane.INFORMATION_MESSAGE.
    public void showAbout() {
        JOptionPane.showMessageDialog(this, "Сообщение ", "Заголовок", JOptionPane.INFORMATION_MESSAGE);
    }

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }


}
