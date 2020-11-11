package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

import java.util.Optional;

public class BackSpaceCommand implements UndoableCommand {

    private Character deletedChar;

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
