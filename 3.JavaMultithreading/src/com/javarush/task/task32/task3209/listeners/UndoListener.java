package com.javarush.task.task32.task3209.listeners;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * Добавь класс UndoListener реализующий интерфейс UndoableEditListener в пакет listeners.
 * Этот класс будет следить за правками, которые можно отменить или вернуть
 */
public class UndoListener implements UndoableEditListener {
    private UndoManager undoManager;

    public UndoListener(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
    //должен из переданного события получать правку(getEdit()) и добавлять ее в undoManager(addEdit()).
        undoManager.addEdit(e.getEdit());
    }
}
