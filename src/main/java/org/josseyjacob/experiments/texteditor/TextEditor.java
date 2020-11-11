package org.josseyjacob.experiments.texteditor;

public class TextEditor {

    private final StringBuilder text;

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

    public void deleteLastCharacter() {
        text.deleteCharAt(text.length() - 1);
    }
}
