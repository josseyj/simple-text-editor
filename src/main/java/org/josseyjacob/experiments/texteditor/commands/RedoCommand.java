package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class RedoCommand extends AbstractCommand implements Command {
    protected RedoCommand(Long timestamp) {
        super(timestamp);
    }

    @Override
    public void execute(TextEditor editor) {
        editor.redo();
    }
}
