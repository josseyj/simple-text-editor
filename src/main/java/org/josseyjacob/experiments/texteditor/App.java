package org.josseyjacob.experiments.texteditor;

import org.josseyjacob.experiments.texteditor.commands.Command;

import java.util.Comparator;

/**
 * Hello world!
 */
public class App {

    final TextEditor textEditor = new TextEditor();

    final CommandParser commandInterpreter = new CommandParser();

    /**
     * Processes the text editor command inputs.
     *
     * @param input the command inputs
     * @return the current text
     */
    public String process(String[][] input) {
        commandInterpreter.parse(input)
                .sorted(Comparator.comparing(Command::getTimestamp))
                .forEach(textEditor::execute);
        return textEditor.getCurrentText();
    }

}
