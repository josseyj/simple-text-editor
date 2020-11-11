package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

import java.util.Optional;

public class BackSpaceCommand extends AbstractCommand implements UndoableCommand {

    private Character deletedChar;

    public BackSpaceCommand(Long timestamp) {
        super(timestamp);
    }

    @Override
    public void execute(TextEditor editor) {
        this.deletedChar = editor.deleteLastCharacter().orElse(null);
    }

    @Override
    public void undo(TextEditor editor) {
        Optional.ofNullable(deletedChar)
                .map(String::valueOf)
                .ifPresent(editor::append);
    }
}
