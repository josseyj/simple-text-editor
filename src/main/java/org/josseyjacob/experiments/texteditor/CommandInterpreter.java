package org.josseyjacob.experiments.texteditor;

import org.josseyjacob.experiments.texteditor.commands.Command;
import org.josseyjacob.experiments.texteditor.commands.CommandFactory;

import java.util.stream.Stream;

/**
 * Interprets the commands.
 */
public class CommandInterpreter {

    /**
     * The command factory.
     */
    private final static CommandFactory COMMAND_FACTORY = new CommandFactory();

    /**
     * The text editor.
     */
    private final TextEditor textEditor;

    public CommandInterpreter(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    public void process(String[][] inputs) {
        Stream.of(inputs)
                .forEach(this::process);
    }

    public void process(String[] input) {
        Command command = COMMAND_FACTORY.createCommand(input);
        command.execute(textEditor);
    }
}
