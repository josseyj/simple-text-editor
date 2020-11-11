package org.josseyjacob.experiments.texteditor;

import org.josseyjacob.experiments.texteditor.commands.Command;
import org.josseyjacob.experiments.texteditor.commands.NoOpCommand;
import org.josseyjacob.experiments.texteditor.commands.UndoableCommand;

import java.util.LinkedList;
import java.util.Optional;

public class TextEditor {

    private final StringBuilder text;

    private Node<Command> lastCommandNode = new Node<>(new NoOpCommand(), null);

    private int selectionStart;

    private int selectionEnd;


    public TextEditor() {
        text = new StringBuilder();
    }

    /**
     * Appends text to the end.
     *
     * @param value the value to be appended
     */
    public void append(String value) {
        if (isTextSelected()) {
            delete();
        }
        this.text.insert(this.selectionStart, value);
        this.setCursorAt(this.selectionStart + value.length());
    }

    public void select(int start, int end) {
        if (start >= 0 && start <= text.length() - 1) {
            this.selectionStart = start;
            this.selectionEnd = Math.min(end, text.length());
        }
    }

    public void setCursorAt(int index) {
        if (index >= 0 && index <= text.length()) {
            this.selectionStart = index;
            this.selectionEnd = index;
        }
    }

    public boolean isTextSelected() {
        return selectionEnd > selectionStart;
    }

    /**
     * Returns the current text value.
     *
     * @return the current text
     */
    public String getCurrentText() {
        return text.toString();
    }

    public Optional<String> delete() {
        if (isTextSelected()) {
            String selectedText = text.substring(selectionStart, selectionEnd);
            text.delete(selectionStart, selectionEnd);
            setCursorAt(selectionStart);
            return Optional.of(selectedText);
        } else if (selectionStart > 0) {
            char charToBeDeleted = text.charAt(selectionStart - 1);
            text.deleteCharAt(selectionStart - 1);
            setCursorAt(selectionStart - 1);
            return Optional.of(String.valueOf(charToBeDeleted));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Character> deleteLastCharacter() {
        if (text.length() > 0) {
            char charToBeDeleted = text.charAt(text.length() - 1);
            text.deleteCharAt(text.length() - 1);
            setCursorAt(text.length());
            return Optional.of(charToBeDeleted);
        }
        return Optional.empty();
    }

    public void deleteLastCharacters(int length) {
        if (text.length() < length) {
            throw new IllegalArgumentException("Not enough characters present.");
        }
        text.delete(text.length() - length, text.length());
        setCursorAt(text.length());
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

    public int getCursorPosition() {
        return selectionStart;
    }
}
