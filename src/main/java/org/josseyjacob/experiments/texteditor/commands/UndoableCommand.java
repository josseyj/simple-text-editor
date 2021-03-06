package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public interface UndoableCommand extends Command {

    void undo(TextEditor editor);
}
