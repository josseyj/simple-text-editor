package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class AppendCommand extends AbstractCommand implements UndoableCommand {

    private final String value;

    public AppendCommand(String value, long timestamp) {
        super(timestamp);
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
