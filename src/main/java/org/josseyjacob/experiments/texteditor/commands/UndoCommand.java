package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class UndoCommand extends AbstractCommand implements Command {
    protected UndoCommand(Long timestamp) {
        super(timestamp);
    }

    @Override
    public void execute(TextEditor editor) {
        editor.undo();
    }
}
