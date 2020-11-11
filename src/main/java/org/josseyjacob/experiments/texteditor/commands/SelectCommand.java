package org.josseyjacob.experiments.texteditor.commands;

import org.josseyjacob.experiments.texteditor.TextEditor;

public class SelectCommand extends AbstractCommand implements Command {
    private final int start;
    private final int end;

    protected SelectCommand(Long timestamp, int start, int end) {
        super(timestamp);
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TextEditor editor) {
        editor.select(start, end);
    }
}
