package org.josseyjacob.experiments.texteditor.commands;

public class CommandFactory {

    public Command createCommand(String[] commandInput) {
        if (commandInput == null || commandInput.length < 2) {
            throw new IllegalArgumentException("Invalid command input.");
        }
        final long timestamp = Long.parseLong(commandInput[0]);
        final String commandKey = commandInput[1];
        switch (commandKey) {
            case "APPEND": {
                if (commandInput.length < 3) {
                    throw new IllegalArgumentException("Invalid APPEND command format.");
                }
                final String value = commandInput[2];
                return new AppendCommand(value, timestamp);
            }
            case "SELECT": {
                if (commandInput.length < 4) {
                    throw new IllegalArgumentException("Invalid SELECT command format.");
                }
                final int start = Integer.parseInt(commandInput[2]);
                final int end = Integer.parseInt(commandInput[3]);
                return new SelectCommand(timestamp, start, end);
            }
            case "BACKSPACE":
                return new BackSpaceCommand(timestamp);

            case "UNDO":
                return new UndoCommand(timestamp);

            case "REDO":
                return new RedoCommand(timestamp);

            default:
                throw new UnsupportedOperationException("Unsupported Command");
        }
    }

}
