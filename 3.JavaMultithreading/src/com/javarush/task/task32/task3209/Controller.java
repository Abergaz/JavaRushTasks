package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document; //модель
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();

    }

    public void init() {
        //должен просто вызывать метод создания нового документа.
        createNewDocument();
    }

    //будет сбрасывать текущий документ
    public void resetDocument() {
        //Удалять у текущего документа document слушателя правок которые можно отменить/вернуть (найди подходящий для этого метод, унаследованный от AbstractDocument).
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        // Создавать новый документ по умолчанию и присваивать его полю document. воспользуйся подходящим методом класса HTMLEditorKit.
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        document = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public HTMLDocument getDocument() {
        return document;
    }

    //Он будет записывать переданный текст с html тегами в документ document
    public void setPlainText(String text) {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.read(stringReader, document, 0);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    //Он должен получать текст из документа со всеми html тегами.
    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.write(stringWriter, document, 0, document.getLength());
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void exit() {
        System.exit(0);
    }

    public void createNewDocument() {
        //Выбирать html вкладку у представления.
        view.selectHtmlTab();
        //Сбрасывать текущий документ.
        resetDocument();
        //Устанавливать новый заголовок окна, например: "HTML редактор". Воспользуйся методом setTitle(), который унаследован в нашем представлении.
        view.setTitle("HTML редактор");
        //Сбрасывать правки в Undo менеджере. Используй метод resetUndo представления.
        view.resetUndo();
        //Обнулить переменную currentFile.
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        //Создавать новый объект для выбора файла JFileChooser.
        JFileChooser jFileChooser = new JFileChooser();
        //Устанавливать ему в качестве фильтра объект HTMLFileFilter
        jFileChooser.setFileFilter(new HTMLFileFilter());
        //Показывать диалоговое окно "Save File" для выбора файла.
        int choose = jFileChooser.showOpenDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION){
            //Установить новое значение currentFile
            currentFile = jFileChooser.getSelectedFile();
            //Сбросить документ
            resetDocument();
            //Установить имя файла в заголовок у представления.
            view.setTitle(currentFile.getName());
            //Создать FileReader, используя currentFile
            try {
                FileReader fileReader = new FileReader(currentFile);
                //Вычитать данные из FileReader-а в документ document с помощью объекта класса HTMLEditorKit.
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                try {
                    htmlEditorKit.read(fileReader,document,0);
                    //Сбросить правки (вызвать метод resetUndo представления).
                    view.resetUndo();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BadLocationException e) {
                    ExceptionHandler.log(e);;
                }
            } catch (FileNotFoundException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {
        //Метод должен работать также, как saveDocumentAs(), но не запрашивать файл у пользователя, а использовать currentFile.
        //Если currentFile равен null, то вызывать метод saveDocumentAs().
        if (currentFile!=null){
            view.selectHtmlTab();
            //Устанавливать имя файла в качестве заголовка окна представления.
            view.setTitle(currentFile.getName());
            //Создавать FileWriter на базе currentFile.
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                //Переписывать данные из документа document в объекта FileWriter-а аналогично тому, как мы это делали в методе getPlainText().
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                try {
                    htmlEditorKit.write(fileWriter,document,0,document.getLength());
                } catch (BadLocationException e) {
                    ExceptionHandler.log(e);
                }
            } catch (IOException e) {
                ExceptionHandler.log(e);
            }

        }else {
            saveDocumentAs();
        }
    }

    public void saveDocumentAs() {
        //Переключать представление на html вкладку
        view.selectHtmlTab();
        //Создавать новый объект для выбора файла JFileChooser.
        JFileChooser jFileChooser = new JFileChooser();
        //Устанавливать ему в качестве фильтра объект HTMLFileFilter
        jFileChooser.setFileFilter(new HTMLFileFilter());
        //Показывать диалоговое окно "Save File" для выбора файла.
        int choose = jFileChooser.showSaveDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION) {
            //Если пользователь подтвердит выбор файла:
            //22.5.1. Сохранять выбранный файл в поле currentFile.
            currentFile = jFileChooser.getSelectedFile();
            //Устанавливать имя файла в качестве заголовка окна представления.
            view.setTitle(currentFile.getName());
            //Создавать FileWriter на базе currentFile.
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                //Переписывать данные из документа document в объекта FileWriter-а аналогично тому, как мы это делали в методе getPlainText().
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                try {
                    htmlEditorKit.write(fileWriter,document,0,document.getLength());
                } catch (BadLocationException e) {
                  ExceptionHandler.log(e);
                }
            } catch (IOException e) {
                ExceptionHandler.log(e);
            }
        }

    }

    public void showAbout() {
    }
}
