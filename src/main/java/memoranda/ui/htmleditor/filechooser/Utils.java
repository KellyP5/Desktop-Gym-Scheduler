package main.java.memoranda.ui.htmleditor.filechooser;

import java.io.File;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * The constant jpeg.
     */
    public final static String jpeg = "jpeg";
    /**
     * The constant jpg.
     */
    public final static String jpg = "jpg";
    /**
     * The constant gif.
     */
    public final static String gif = "gif";
    /**
     * The constant png.
     */
    public final static String png = "png";

    /**
     * Gets extension.
     *
     * @param f the f
     * @return the extension
     */
    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}
