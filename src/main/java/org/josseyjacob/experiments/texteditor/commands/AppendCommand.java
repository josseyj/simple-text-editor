package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class AppendCommand implements Command {

    private final String value;

    public AppendCommand(String value) {
        this.value = value;
    }

    @Override
    public void execute(TextEditor editor) {
        editor.append(value);
    }
}
