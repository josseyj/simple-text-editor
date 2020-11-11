package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public interface Command {

    void execute(TextEditor editor);

}
