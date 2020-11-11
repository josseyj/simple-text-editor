package org.josseyjacob.experiments.texteditor;

import org.josseyjacob.experiments.texteditor.commands.Command;
import org.josseyjacob.experiments.texteditor.commands.NoOpCommand;
import org.josseyjacob.experiments.texteditor.commands.UndoableCommand;

import java.util.Optional;

public class TextEditor {

    private final StringBuilder text;

    private Node<Command> lastCommandNode = new Node<>(new NoOpCommand(), null);


    public TextEditor() {
        text = new StringBuilder();
    }

    /**
     * Appends text to the end.
     *
     * @param value the value to be appended
     */
    public void append(String value) {
        this.text.append(value);
    }

    /**
     * Returns the current text value.
     *
     * @return the current text
     */
    public String getCurrentText() {
        return text.toString();
    }

    public Optional<Character> deleteLastCharacter() {
        if (text.length() > 0) {
            char charToBeDeleted = text.charAt(text.length() - 1);
            text.deleteCharAt(text.length() - 1);
            return Optional.of(charToBeDeleted);
        }
        return Optional.empty();
    }

    public void deleteLastCharacters(int length) {
        if (text.length() < length) {
            throw new IllegalArgumentException("Not enough characters present.");
        }
        text.delete(text.length() - length, text.length());
    }

    public void execute(Command command) {
        command.execute(this);
        if (command instanceof UndoableCommand) {
            addToCommandHistory(command);
        }
    }

    public void undo() {
        Command lastCommand = lastCommandNode.getValue();
        if (lastCommand instanceof UndoableCommand) {
            ((UndoableCommand)lastCommand).undo(this);
            if (lastCommandNode.getPrevious() != null) {
                lastCommandNode = lastCommandNode.getPrevious();
            }
        }
    }

    public void redo() {
        Optional.ofNullable(lastCommandNode)
                .map(Node::getNext)
                .ifPresent(commandNode -> {
                    commandNode.getValue().execute(this);
                    lastCommandNode = commandNode;
                });
    }

    private void addToCommandHistory(Command command) {
        lastCommandNode = lastCommandNode.nextValue(command);
    }
}
