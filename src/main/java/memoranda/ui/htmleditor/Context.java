package main.java.memoranda.ui.htmleditor;
import java.util.Hashtable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */
class Context {

    /**
     * The Hash.
     */
    static java.util.Hashtable hash = new Hashtable();

    /**
     * Get object.
     *
     * @param key the key
     * @return the object
     */
    public static Object get(Object key) {
    return hash.get(key);
  }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    public static void put(Object key, Object value) {
    hash.put(key, value);
  }

}