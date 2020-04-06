/**
 * Local.java
 * Created on 05.09.2003, 16:43:39 Alex
 * Package: org.openmechanics.htmleditor
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 OpenMechanics.org
 */
package main.java.memoranda.ui.htmleditor.util;
import java.util.Hashtable;

/**
 * The type Local.
 */
public class Local {

    /**
     * The Messages.
     */
    static Hashtable messages = null;

    /**
     * Sets messages.
     *
     * @param msgs the msgs
     */
    public static void setMessages(Hashtable msgs) {
        messages = msgs;
   }

    /**
     * Gets string.
     *
     * @param key the key
     * @return the string
     */
    public static String getString(String key) {
        if (messages == null){
            return key;
        }
        String msg = (String) messages.get(key.trim().toUpperCase());
        if ((msg != null) && (msg.length() > 0)){
            return msg;
        }
        else {
            return key;
        }
   }

}
