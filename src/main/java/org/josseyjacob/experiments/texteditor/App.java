package org.josseyjacob.experiments.texteditor;

/**
 * Hello world!
 */
public class App {

    final TextEditor textEditor = new TextEditor();

    final CommandInterpreter commandInterpreter = new CommandInterpreter(textEditor);

    /**
     * Processes the text editor command inputs.
     *
     * @param input the command inputs
     * @return the current text
     */
    public String process(String[][] input) {
        commandInterpreter.process(input);
        return textEditor.getCurrentText();
    }
}
