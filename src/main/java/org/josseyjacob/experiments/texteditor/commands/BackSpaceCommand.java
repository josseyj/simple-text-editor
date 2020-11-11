package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

import java.util.Optional;

public class BackSpaceCommand extends AbstractCommand implements UndoableCommand {

    private String deletedValue;

    private int deletedPosition;

    public BackSpaceCommand(Long timestamp) {
        super(timestamp);
    }

    @Override
    public void execute(TextEditor editor) {
        this.deletedValue = editor.delete().orElse(null);
        this.deletedPosition = editor.getCursorPosition();
    }

    @Override
    public void undo(TextEditor editor) {
        Optional.ofNullable(deletedValue)
                .ifPresent(value -> {
                    editor.setCursorAt(deletedPosition);
                    editor.append(value);
                });
    }
}
