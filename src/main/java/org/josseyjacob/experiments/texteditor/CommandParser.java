package org.josseyjacob.experiments.texteditor;

import org.josseyjacob.experiments.texteditor.commands.Command;
import org.josseyjacob.experiments.texteditor.commands.CommandFactory;

import java.util.stream.Stream;

/**
 * Parses the string array as commands.
 */
public class CommandParser {

    /**
     * The command factory.
     */
    private final static CommandFactory COMMAND_FACTORY = new CommandFactory();

    public Stream<Command> parse(String[][] inputs) {
        return Stream.of(inputs)
                .map(this::parse);
    }

    public Command parse(String[] input) {
        return COMMAND_FACTORY.createCommand(input);
    }
}
