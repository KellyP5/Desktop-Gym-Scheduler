/**
 * ResourcesList.java
 * Created on 24.03.2003, 18:25:59 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import java.util.Vector;

import nu.xom.Document;

/**
 * The interface Resources list.
 */
/*$Id: ResourcesList.java,v 1.4 2007/03/20 06:21:46 alexeya Exp $*/
public interface ResourcesList {

    /**
     * Gets all resources.
     *
     * @return the all resources
     */
    Vector getAllResources();
    
    //Vector getResourcesForTask(String taskId);

    /**
     * Gets resource.
     *
     * @param path the path
     * @return the resource
     */
    Resource getResource(String path);

    /**
     * Add resource.
     *
     * @param path               the path
     * @param isInternetShortcut the is internet shortcut
     * @param isProjectFile      the is project file
     */
    void addResource(String path, boolean isInternetShortcut, boolean isProjectFile);

    /**
     * Add resource.
     *
     * @param path the path
     */
    void addResource(String path);
    
    //void addResource(String path, String taskId);

    /**
     * Remove resource.
     *
     * @param path the path
     */
    void removeResource(String path);

    /**
     * Gets all resources count.
     *
     * @return the all resources count
     */
    int getAllResourcesCount();

    /**
     * Gets xml content.
     *
     * @return the xml content
     */
    Document getXMLContent();

}
