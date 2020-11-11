package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class UndoCommand implements Command {
    @Override
    public void execute(TextEditor editor) {
        editor.undo();
    }
}
