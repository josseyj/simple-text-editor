package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class NoOpCommand implements Command {
    @Override
    public void execute(TextEditor editor) {
        //ignore
    }
}
