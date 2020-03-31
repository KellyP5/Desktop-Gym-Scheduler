/**
 * MimeTypesList.java
 * Created on 24.03.2003, 13:54:52 Alex
 * Package: net.sf.memoranda.util
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.util;
import java.util.Vector;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * The type Mime types list.
 */
/*$Id: MimeTypesList.java,v 1.3 2004/01/30 12:17:42 alexeya Exp $*/
public class MimeTypesList {
    /**
     * The constant _doc.
     */
    public static Document _doc = null;
    /**
     * The Root.
     */
    static Element _root = null;

    static {
        CurrentStorage.get().openMimeTypesList();
        _root = _doc.getRootElement();
    }

    /**
     * Gets all mime types.
     *
     * @return the all mime types
     */
    public static Vector getAllMimeTypes() {
        Vector v = new Vector();
        Elements els = _root.getChildElements("mime-type");
        for (int i = 0; i < els.size(); i++)
            v.add(new MimeType(els.get(i)));
        return v;
    }

    /**
     * Gets mime type for file.
     *
     * @param path the path
     * @return the mime type for file
     */
    public static MimeType getMimeTypeForFile(String path) {
        return getMimeTypeByExt(getExtension(path));
    }

    /**
     * Gets mime type.
     *
     * @param mimeId the mime id
     * @return the mime type
     */
    public static MimeType getMimeType(String mimeId) {
        Elements els = _root.getChildElements("mime-type");
        for (int i = 0; i < els.size(); i++)
            if (els.get(i).getAttribute("id").getValue().equals(mimeId))
                return new MimeType(els.get(i));
        return new MimeType();
    }

    /**
     * Gets mime type by ext.
     *
     * @param ext the ext
     * @return the mime type by ext
     */
    public static MimeType getMimeTypeByExt(String ext) {
        Elements els = _root.getChildElements("mime-type");
        for (int i = 0; i < els.size(); i++) {
            Element el = els.get(i);
            Elements exts = el.getChildElements("ext");
            for (int j = 0; j < exts.size(); j++)
                if (exts.get(j).getValue().toLowerCase().equals(ext.toLowerCase()))
                    return new MimeType(el);
        }
        return new MimeType();
    }

    /**
     * Add mime type mime type.
     *
     * @param mimeId the mime id
     * @return the mime type
     */
    public static MimeType addMimeType(String mimeId) {
        Element el = new Element("mime-type");
        el.addAttribute(new Attribute("id", mimeId));
        _root.appendChild(el);
        return new MimeType(el);
    }

    /**
     * Remove mime type.
     *
     * @param mimeId the mime id
     */
    public static void removeMimeType(String mimeId) {
        Elements els = _root.getChildElements("mime-type");
        for (int i = 0; i < els.size(); i++)
            if (els.get(i).getAttribute("id").getValue().equals(mimeId)) {
                _root.removeChild(els.get(i));
                return;
            }
    }

    /**
     * Gets app list.
     *
     * @return the app list
     */
    public static AppList getAppList() {
        return new AppList(_root.getChildElements("applications").get(0));
    }

    /**
     * Gets extension.
     *
     * @param s the s
     * @return the extension
     */
    public static String getExtension(String s) {
        String ext = null;
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }


}
