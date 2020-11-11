package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class AppendCommand implements UndoableCommand {

    private final String value;

    public AppendCommand(String value) {
        this.value = value;
    }

    @Override
    public void execute(TextEditor editor) {
        editor.append(value);
    }

    @Override
    public void undo(TextEditor editor) {
        editor.deleteLastCharacters(value.length());
    }
}
