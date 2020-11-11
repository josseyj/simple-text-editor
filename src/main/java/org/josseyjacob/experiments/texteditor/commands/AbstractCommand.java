package org.josseyjacob.experiments.texteditor.commands;

public abstract class AbstractCommand implements Command {

    private final Long timestamp;

    protected AbstractCommand(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }
}
