package utils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class TextFieldLengthLimit {

    public static void addLengthLimit(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int insertLength = text.length();
                if (currentLength + insertLength - length > maxLength) {
                    int newLength = maxLength - currentLength + length;
                    if (newLength > 0) {
                        text = text.substring(0, newLength);
                    } else {
                        text = "";
                    }
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
    }
}