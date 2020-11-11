package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class AppendCommand extends AbstractCommand implements UndoableCommand {

    private final String value;

    private int appendedPosition;

    public AppendCommand(String value, long timestamp) {
        super(timestamp);
        this.value = value;
    }

    @Override
    public void execute(TextEditor editor) {
        this.appendedPosition = editor.getCursorPosition();
        editor.append(value);
    }

    @Override
    public void undo(TextEditor editor) {
        editor.select(appendedPosition, appendedPosition + value.length());
        editor.delete();
    }
}
