package main.java.memoranda;

/**
 * The interface Project listener.
 */
/*$Id: ProjectListener.java,v 1.3 2004/01/30 12:17:41 alexeya Exp $*/
public interface ProjectListener {
    /**
     * Project change.
     *
     * @param prj the prj
     * @param nl  the nl
     * @param tl  the tl
     * @param rl  the rl
     */
    void projectChange(Project prj, NoteList nl, TaskList tl, ResourcesList rl);

    /**
     * Project was changed.
     */
    void projectWasChanged();
}