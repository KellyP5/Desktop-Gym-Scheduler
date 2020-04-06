/**
 * Resource.java
 * Created on 04.04.2003, 20:59:24 Alex
 * Package: net.sf.memoranda
 *  
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

/**
 * The type Resource.
 */
/*$Id: Resource.java,v 1.4 2007/03/20 06:21:46 alexeya Exp $*/
public class Resource {
    
    private String _path = null;	// the path to the file
    private boolean _isInetShortcut = false; // true if Internet shortcut
    private boolean _isProjectFile = false;	// true if file is in project directory 

    /**
     * Constructor for Resource.
     *
     * @param path           the path
     * @param isInetShortcut the is inet shortcut
     * @param isProjectFile  the is project file
     */
    public Resource(String path, boolean isInetShortcut, boolean isProjectFile) {
        _path = path;
        _isInetShortcut = isInetShortcut;
        _isProjectFile = isProjectFile;
    }

    /**
     * Instantiates a new Resource.
     *
     * @param path the path
     */
    public Resource(String path) {
        _path = path;         
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return _path;
    }

    /**
     * Is inet shortcut boolean.
     *
     * @return the boolean
     */
    public boolean isInetShortcut() {
        return _isInetShortcut;
    }

    /**
     * Is project file boolean.
     *
     * @return the boolean
     */
    public boolean isProjectFile() {
    	return _isProjectFile;
    }

}
