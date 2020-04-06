/**
 * MimeType.java
 * Created on 24.03.2003, 13:55:46 Alex
 * Package: net.sf.memoranda.util
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.util;
import java.util.Vector;

import javax.swing.ImageIcon;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * The type Mime type.
 */
/*$Id: MimeType.java,v 1.3 2004/01/30 12:17:42 alexeya Exp $*/
public class MimeType {

    /**
     * The Root.
     */
    public Element _root = null;

    /**
     * Instantiates a new Mime type.
     *
     * @param root the root
     */
    public MimeType(Element root) {
        _root = root;
    }

    /**
     * Instantiates a new Mime type.
     */
    public MimeType() {
      _root = new Element("default-type");
      _root.addAttribute(new Attribute("id", "__UNKNOWN"));
      _root.addAttribute(new Attribute("label", "Unknown"));
    }

    /**
     * Gets mime type id.
     *
     * @return the mime type id
     */
    public String getMimeTypeId() {
        return _root.getAttribute("id").getValue();
    }

    /**
     * Gets extension.
     *
     * @return the extension
     */
    public String getExtension() {
        Elements exts = _root.getChildElements("ext");
        if (exts.size() > 0)
            return exts.get(0).getValue();
        return null;
    }

    /**
     * Get extensions string [ ].
     *
     * @return the string [ ]
     */
    public String[] getExtensions() {
        Vector v = new Vector();
        String[] ss = {};
        Elements exts = _root.getChildElements("ext");
        for (int i = 0; i < exts.size(); i++)
            v.add(exts.get(i).getValue());
        return (String[]) v.toArray(ss);
    }

    /**
     * Add extension.
     *
     * @param ext the ext
     */
    public void addExtension(String ext) {
        Element exe = new Element("ext");
        exe.appendChild(ext);
        _root.appendChild(exe);
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
        if ((_root.getAttribute("label") != null) && (_root.getAttribute("label").getValue().length() >0))
          return _root.getAttribute("label").getValue();
        else
           return _root.getAttribute("id").getValue();
    }

    /**
     * Sets label.
     *
     * @param label the label
     */
    public void setLabel(String label) {
        if (_root.getAttribute("label") != null)
         _root.getAttribute("label").setValue(label);
        else
        _root.addAttribute(new Attribute("label", label));
    }

    /**
     * Gets app id.
     *
     * @param plafCode the plaf code
     * @return the app id
     */
    public String getAppId(String plafCode) {
        Elements apps = _root.getChildElements("app");
        for (int i = 0; i < apps.size(); i++)
            if (apps.get(i).getAttribute("platform").getValue().toLowerCase().equals(plafCode.toLowerCase()))
                return apps.get(i).getAttribute("appId").getValue();
        return null;
    }

    /**
     * Sets app.
     *
     * @param plafCode the plaf code
     * @param appId    the app id
     */
    public void setApp(String plafCode, String appId) {
        if (getAppId(plafCode) != null) {
            Elements apps = _root.getChildElements("app");
            for (int i = 0; i < apps.size(); i++)
                if (apps.get(i).getAttribute("platform").getValue().toLowerCase().equals(plafCode.toLowerCase()))
                    apps.get(i).getAttribute("appId").setValue(appId);
        }
        else {
            Element app = new Element("app");
            app.addAttribute(new Attribute("appId", appId));
            app.addAttribute(new Attribute("platform", plafCode));
            _root.appendChild(app);
        }
    }

    /**
     * Sets app.
     *
     * @param appId the app id
     */
    public void setApp(String appId) {
        setApp(AppList.getPlafCode(System.getProperty("os.name")), appId);
    }

    /**
     * Gets app id.
     *
     * @return the app id
     */
    public String getAppId() {
        String plaf = AppList.getPlafCode(System.getProperty("os.name"));
        return getAppId(plaf);
    }

    /**
     * Gets icon path.
     *
     * @return the icon path
     */
    public String getIconPath() {
        if (_root.getAttribute("icon") != null)
          return _root.getAttribute("icon").getValue();
        else
          return "";
    }

    /**
     * Sets icon path.
     *
     * @param path the path
     */
    public void setIconPath(String path) {
         if (_root.getAttribute("icon") != null)
          _root.getAttribute("icon").setValue(path);
        else
        _root.addAttribute(new Attribute("icon", path));
    }

    /**
     * Gets icon.
     *
     * @return the icon
     */
    public ImageIcon getIcon() {
        String ip = getIconPath();
        ImageIcon icon = null;

        if (ip.equals("")) {
            ip = "/ui/icons/mimetypes/"+getMimeTypeId()+".png";
            System.out.println("[DEBUG] String ip: " + ip);
            try {
                icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource(ip));
            }
            catch (Exception ex) {
                System.out.println("[DEBUG] String ip: " + ip);
                ip = "/ui/icons/mimetypes/"+getMimeTypeId().split("/")[0]+"/default.png";
                try {
                    System.out.println("[DEBUG] String ip: " + ip);
                    icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource(ip));
                }
                catch (Exception ex2) {
                    icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/util/icons/mimetypes/default.png"));
                }
            }
        }
        else
            try {
                icon = new ImageIcon(ip);
            }
            catch (Exception ex) {
                ip = "/util/icons/mimetypes/"+getMimeTypeId().split("/")[0]+"/default.png";
                try {
                    icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource(ip));
                }
                catch (Exception ex2) {
                    System.out.println("[DEBUG] String ip: " + ip);
                    icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/mimetypes/default.png"));
                }
            }
        return icon;
    }
}
