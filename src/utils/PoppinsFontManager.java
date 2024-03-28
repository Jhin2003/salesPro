package utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PoppinsFontManager {

        private static Font regularFont;
        private static Font boldFont;

        public static void loadFonts(String fontDirectory) {
            try {
                regularFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontDirectory + "/Poppins-Regular.ttf"));
                boldFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontDirectory + "/Poppins-Bold.ttf"));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
                // Handle font loading exception
            }
        }

        public static void applyPoppinsFont(JLabel label, boolean isBold, int size) {
            Font font = isBold ? boldFont : regularFont;
            if (font != null) {
                label.setFont(font.deriveFont(Font.PLAIN, size));
            }
        }

    public static void applyPoppinsFont(JButton button, boolean isBold, int size) {
        Font font = isBold ? boldFont : regularFont;
        if (font != null) {
            button.setFont(font.deriveFont(Font.PLAIN, size));
        }
    }

    public static void applyPoppinsFont(JTextField field, boolean isBold, int size) {
        Font font = isBold ? boldFont : regularFont;
        if (font != null) {
            field.setFont(font.deriveFont(Font.PLAIN, size));
        }
    }
    }

