package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class RedoCommand implements Command {
    @Override
    public void execute(TextEditor editor) {
        editor.redo();
    }
}
