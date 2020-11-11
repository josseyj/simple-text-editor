package org.josseyjacob.experiments.texteditor.commands;

public class CommandFactory {

    public Command createCommand(String[] commandInput) {
        if (commandInput == null || commandInput.length < 2) {
            throw new IllegalArgumentException("Invalid command input.");
        }
        final String commandKey = commandInput[1];
        switch (commandKey) {
            case "APPEND":
                if (commandInput.length < 3) {
                    throw new IllegalArgumentException("Invalid APPEND command format.");
                }
                final String value = commandInput[2];
                return new AppendCommand(value);

            case "BACKSPACE":
                return new BackSpaceCommand();

            case "UNDO":
                return new UndoCommand();

            case "REDO":
                return new RedoCommand();

            default:
                throw new UnsupportedOperationException("Unsupported Command");
        }
    }

}
