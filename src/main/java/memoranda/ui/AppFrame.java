package main.java.memoranda.ui;

import main.java.memoranda.*;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.util.*;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/**
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
/*$Id: AppFrame.java,v 1.33 2005/07/05 08:17:24 alexeya Exp $*/

public class AppFrame extends JFrame {

    /**
     * The Content pane.
     */
    JPanel contentPane;
    /**
     * The Menu bar.
     */
    JMenuBar menuBar = new JMenuBar();
    /**
     * The J menu file.
     */
    JMenu jMenuFile = new JMenu();
    /**
     * The J menu file exit.
     */
    JMenuItem jMenuFileExit = new JMenuItem();

    /**
     * The Tool bar.
     */
    JToolBar toolBar = new JToolBar();
    /**
     * The J button 3.
     */
    JButton jButton3 = new JButton();
    /**
     * The Image 1.
     */
    ImageIcon image1;
    /**
     * The Image 2.
     */
    ImageIcon image2;
    /**
     * The Image 3.
     */
    ImageIcon image3;
    /**
     * The Status bar.
     */
    JLabel statusBar = new JLabel();
    /**
     * The Border layout 1.
     */
    BorderLayout borderLayout1 = new BorderLayout();
    /**
     * The Split pane.
     */
    JSplitPane splitPane = new JSplitPane();
    /**
     * The Projects panel.
     */
    ProjectsPanel projectsPanel = new ProjectsPanel();
    /**
     * The Pr panel expanded.
     */
    boolean prPanelExpanded = false;

    /**
     * The J menu edit.
     */
    JMenu jMenuEdit = new JMenu();
    /**
     * The J menu format.
     */
    JMenu jMenuFormat = new JMenu();
    /**
     * The J menu insert.
     */
    JMenu jMenuInsert = new JMenu();

    /**
     * The Work panel.
     */
    public WorkPanel workPanel = new WorkPanel();


    /**
     * The Exit listeners.
     */
    static Vector exitListeners = new Vector();

    /**
     * The Prj pack action.
     */
    public Action prjPackAction = new AbstractAction("Pack current project") {
        public void actionPerformed(ActionEvent e) {
            doPrjPack();
        }
    };

    /**
     * The Prj unpack action.
     */
    public Action prjUnpackAction = new AbstractAction("Unpack project") {
        public void actionPerformed(ActionEvent e) {
            doPrjUnPack();
        }
    };

    /**
     * The Minimize action.
     */
    public Action minimizeAction = new AbstractAction("Close the window") {
        public void actionPerformed(ActionEvent e) {
            doMinimize();
        }
    };

    /**
     * The Preferences action.
     */
    public Action preferencesAction = new AbstractAction("Preferences") {
        public void actionPerformed(ActionEvent e) {
            showPreferences();
        }
    };

    /**
     * The Export notes action.
     */
    public Action exportNotesAction =
                new AbstractAction(Local.getString("Export notes") + "...") {

                public void actionPerformed(ActionEvent e) {
                        ppExport_actionPerformed(e);
                }
        };

    /**
     * The Import notes action.
     */
    public Action importNotesAction =
                        new AbstractAction(Local.getString("Import multiple notes")) {

                        public void actionPerformed(ActionEvent e) {
                                ppImport_actionPerformed(e);
                        }
                };
    /**
     * The Import one note action.
     */
    public Action importOneNoteAction =
                new AbstractAction(Local.getString("Import one note")) {

                public void actionPerformed(ActionEvent e) {
                        p1Import_actionPerformed(e);
                }
        };

    /**
     * The J menu file new prj.
     */
    JMenuItem jMenuFileNewPrj = new JMenuItem();
    /**
     * The J menu file new note.
     */

    /**
     * The J menu file pack prj.
     */
    JMenuItem jMenuFilePackPrj = new JMenuItem(prjPackAction);
    /**
     * The J menu file unpack prj.
     */
    JMenuItem jMenuFileUnpackPrj = new JMenuItem(prjUnpackAction);
    /**
     * The J menu file export prj.
     */
    JMenuItem jMenuFileExportPrj = new JMenuItem(exportNotesAction);
    /**
     * The J menu file import prj.
     */
    JMenuItem jMenuFileImportPrj = new JMenuItem(importNotesAction);
    /**
     * The J menu file import note.
     */
    JMenuItem jMenuFileImportNote = new JMenuItem(importOneNoteAction);
    /**
     * The J menu file export note.
     */
    /**
     * The J menu file min.
     */
    JMenuItem jMenuFileMin = new JMenuItem(minimizeAction);

    /**
     * The J menu item 1.
     */
    JMenuItem jMenuItem1 = new JMenuItem();



    /**
     * The J menu go.
     */
    JMenu jMenuGo = new JMenu();
    /**
     * The J menu insert image.
     */

    /**
     * The J menu go h back.
     */
    JMenuItem jMenuGoHBack = new JMenuItem(History.historyBackAction);
    /**
     * The J menu go fwd.
     */
    JMenuItem jMenuGoFwd = new JMenuItem(History.historyForwardAction);

    /**
     * The J menu go day back.
     */
    JMenuItem jMenuGoDayBack = new JMenuItem(
            workPanel.dailyItemsPanel.calendar.dayBackAction);
    /**
     * The J menu go day fwd.
     */
    JMenuItem jMenuGoDayFwd = new JMenuItem(
            workPanel.dailyItemsPanel.calendar.dayForwardAction);
    /**
     * The J menu go today.
     */
    JMenuItem jMenuGoToday = new JMenuItem(
            workPanel.dailyItemsPanel.calendar.todayAction);

    /**
     * The J menu edit pref.
     */
    JMenuItem jMenuEditPref = new JMenuItem(preferencesAction);

    /**
     * The J menu insert special.
     */
    JMenu jMenuInsertSpecial = new JMenu();

    /**
     * The J menu help.
     */
    JMenu jMenuHelp = new JMenu();

    /**
     * The J menu help guide.
     */
    JMenuItem jMenuHelpGuide = new JMenuItem();
    /**
     * The J menu help web.
     */
    JMenuItem jMenuHelpWeb = new JMenuItem();
    /**
     * The J menu help bug.
     */
    JMenuItem jMenuHelpBug = new JMenuItem();
    /**
     * The J menu help about.
     */
    JMenuItem jMenuHelpAbout = new JMenuItem();

    /**
     * Instantiates a new App frame.
     */
//Construct the frame
    public AppFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        }
        catch (Exception e) {
            new ExceptionDialog(e);
        }
    }
    //Component initialization
    private void jbInit() throws Exception {
        this.setIconImage(new ImageIcon(AppFrame.class.getResource(
                "/ui/icons/jnotes16.png"))
                .getImage());
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        //this.setSize(new Dimension(800, 500));
        this.setTitle("Memoranda - " + CurrentProject.get().getTitle());
        //Added a space to App.VERSION_INFO to make it look some nicer
        statusBar.setText(" Version:" + App.VERSION_INFO + " (Build "
                + App.BUILD_INFO + " )");

        jMenuFile.setText(Local.getString("File"));
        jMenuFileExit.setText(Local.getString("Exit"));
        jMenuFileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doExit();
            }
        });
        jMenuHelp.setText(Local.getString("Help"));
        
        jMenuHelpGuide.setText(Local.getString("Online user's guide"));
        jMenuHelpGuide.setIcon(new ImageIcon(AppFrame.class.getResource(
                "/ui/icons/help.png")));
        jMenuHelpGuide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpGuide_actionPerformed(e);
            }
        });
        
        jMenuHelpWeb.setText(Local.getString("Memoranda web site"));
        jMenuHelpWeb.setIcon(new ImageIcon(AppFrame.class.getResource(
                "/ui/icons/web.png")));
        jMenuHelpWeb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpWeb_actionPerformed(e);
            }
        });
        
        jMenuHelpBug.setText(Local.getString("Report a bug"));
        jMenuHelpBug.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpBug_actionPerformed(e);
            }
        });        
        
        jMenuHelpAbout.setText(Local.getString("About Globo Gym"));
        jMenuHelpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed(e);
            }
        });
        //jButton3.setIcon(image3);
        jButton3.setToolTipText(Local.getString("Help"));
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        splitPane.setContinuousLayout(true);
        splitPane.setDividerSize(3);
        //splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(28);
        //projectsPanel.setMaximumSize(new Dimension(2147483647, 200));
        projectsPanel.setMinimumSize(new Dimension(10, 28));
        projectsPanel.setPreferredSize(new Dimension(10, 28));
        /*workPanel.setMinimumSize(new Dimension(734, 300));
         workPanel.setPreferredSize(new Dimension(1073, 300));*/
        splitPane.setDividerLocation(28);

        /* jMenuFileNewPrj.setText(Local.getString("New project") + "...");
         jMenuFileNewPrj.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         ProjectDialog.newProject();
         }
         });
         */
        jMenuFileNewPrj.setAction(projectsPanel.newProjectAction);

        jMenuFileUnpackPrj.setText(Local.getString("Unpack project") + "...");

        jMenuFileImportNote.setText(Local.getString("Import one note")
                + "...");
        jMenuFilePackPrj.setText(Local.getString("Pack project") + "...");
        jMenuFileMin.setText(Local.getString("Close the window"));
        jMenuFileMin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,
                InputEvent.ALT_MASK));

        jMenuEdit.setText(Local.getString("Edit"));



        jMenuEditPref.setText(Local.getString("Preferences") + "...");

        jMenuInsert.setText(Local.getString("Insert"));


        jMenuInsertSpecial.setText(Local.getString("Special"));


        jMenuFormat.setText(Local.getString("Format"));

        jMenuGo.setText(Local.getString("Go"));
        jMenuGoHBack.setText(Local.getString("History back"));
        jMenuGoHBack.setToolTipText(Local.getString("History back"));
        jMenuGoFwd.setText(Local.getString("History forward"));
        jMenuGoFwd.setToolTipText(Local.getString("History forward"));
        jMenuGoDayBack.setText(Local.getString("One day back"));
        jMenuGoDayFwd.setText(Local.getString("One day forward"));
        jMenuGoToday.setText(Local.getString("To today"));

        jMenuInsertSpecial.setText(Local.getString("Special"));

        toolBar.add(jButton3);
        jMenuFile.add(jMenuFileNewPrj);

        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFilePackPrj);
        jMenuFile.add(jMenuFileUnpackPrj);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFileExportPrj);

        jMenuFile.add(jMenuFileImportNote);
        jMenuFile.add(jMenuFileImportPrj);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuEditPref);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFileMin);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFileExit);
        
        jMenuHelp.add(jMenuHelpGuide);
        jMenuHelp.add(jMenuHelpWeb);
        jMenuHelp.add(jMenuHelpBug);
        jMenuHelp.addSeparator();
        jMenuHelp.add(jMenuHelpAbout);
        
        menuBar.add(jMenuFile);
        menuBar.add(jMenuEdit);
        menuBar.add(jMenuInsert);
        menuBar.add(jMenuFormat);
        menuBar.add(jMenuGo);
        menuBar.add(jMenuHelp);
        this.setJMenuBar(menuBar);
        //contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(statusBar, BorderLayout.SOUTH);
        contentPane.add(splitPane, BorderLayout.CENTER);
        splitPane.add(projectsPanel, JSplitPane.TOP);
        splitPane.add(workPanel, JSplitPane.BOTTOM);

        jMenuInsert.addSeparator();


        jMenuGo.add(jMenuGoHBack);
        jMenuGo.add(jMenuGoFwd);
        jMenuGo.addSeparator();
        jMenuGo.add(jMenuGoDayBack);
        jMenuGo.add(jMenuGoDayFwd);
        jMenuGo.add(jMenuGoToday);

        splitPane.setBorder(null);
        workPanel.setBorder(null);

        setEnabledEditorMenus(false);

        projectsPanel.AddExpandListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (prPanelExpanded) {
                    prPanelExpanded = false;
                    splitPane.setDividerLocation(28);
                }
                else {
                    prPanelExpanded = true;
                    splitPane.setDividerLocation(0.2);
                }
            }
        });

        java.awt.event.ActionListener setMenusDisabled = new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnabledEditorMenus(false);
            }
        };

        this.workPanel.dailyItemsPanel.taskB
                .addActionListener(setMenusDisabled);


        this.workPanel.tasksB.addActionListener(setMenusDisabled);
        this.workPanel.classesB.addActionListener(setMenusDisabled);
        this.workPanel.filesB.addActionListener(setMenusDisabled);
        this.workPanel.agendaB.addActionListener(setMenusDisabled);

        this.workPanel.notesB.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setEnabledEditorMenus(true);
                    }
                });

        Object fwo = Context.get("FRAME_WIDTH");
        Object fho = Context.get("FRAME_HEIGHT");
        if ((fwo != null) && (fho != null)) {
            int w = new Integer((String) fwo).intValue();
            int h = new Integer((String) fho).intValue();
            this.setSize(w, h);
        }
        else
            this.setExtendedState(Frame.MAXIMIZED_BOTH);

        Object xo = Context.get("FRAME_XPOS");
        Object yo = Context.get("FRAME_YPOS");
        if ((xo != null) && (yo != null)) {
            int x = new Integer((String) xo).intValue();
            int y = new Integer((String) yo).intValue();
            this.setLocation(x, y);
        }

        String pan = (String) Context.get("CURRENT_PANEL");
        if (pan != null) {
            workPanel.selectPanel(pan);
            setEnabledEditorMenus(pan.equalsIgnoreCase("NOTES"));
        }

        CurrentProject.addProjectListener(new ProjectListener() {

            public void projectChange(Project prj, NoteList nl, TaskList tl,
                    ResourcesList rl) {
            }

            public void projectWasChanged() {
                setTitle("Memoranda - " + CurrentProject.get().getTitle());
            }
        });

    }

    /**
     * J menu help bug action performed.
     *
     * @param e the e
     */
    protected void jMenuHelpBug_actionPerformed(ActionEvent e) {
        Util.runBrowser(App.BUGS_TRACKER_URL);
    }

    /**
     * J menu help web action performed.
     *
     * @param e the e
     */
    protected void jMenuHelpWeb_actionPerformed(ActionEvent e) {
        Util.runBrowser(App.WEBSITE_URL);
    }

    /**
     * J menu help guide action performed.
     *
     * @param e the e
     */
    protected void jMenuHelpGuide_actionPerformed(ActionEvent e) {
        Util.runBrowser(App.GUIDE_URL);
    }

    /**
     * Do exit.
     */
//File | Exit action performed
    public void doExit() {
        if (Configuration.get("ASK_ON_EXIT").equals("yes")) {
                        Dimension frmSize = this.getSize();
                        Point loc = this.getLocation();
                        
                        ExitConfirmationDialog dlg = new ExitConfirmationDialog(this,Local.getString("Exit"));
                        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
                        dlg.setVisible(true);
                        if(dlg.CANCELLED) return;
        }

        Context.put("FRAME_WIDTH", new Integer(this.getWidth()));
        Context.put("FRAME_HEIGHT", new Integer(this.getHeight()));
        Context.put("FRAME_XPOS", new Integer(this.getLocation().x));
        Context.put("FRAME_YPOS", new Integer(this.getLocation().y));
        exitNotify();
        System.exit(0);
    }

    /**
     * Do minimize.
     */
    public void doMinimize() {
        exitNotify();
        this.setState(Frame.ICONIFIED);
    }

    /**
     * J menu help about action performed.
     *
     * @param e the e
     */
//Help | About action performed
    public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
         AppFrame_AboutBox dlg = new AppFrame_AboutBox(this);        
         Dimension dlgSize = dlg.getSize();
         Dimension frmSize = getSize();
         Point loc = getLocation();
         dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
         dlg.setModal(true);
         dlg.setVisible(true);
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (Configuration.get("ON_CLOSE").equals("exit"))
                doExit();
            else
                doMinimize();
        }
        else if ((e.getID() == WindowEvent.WINDOW_ICONIFIED)) {

            doMinimize();
        }
        else
            super.processWindowEvent(e);
    }

    /**
     * Add exit listener.
     *
     * @param al the al
     */
    public static void addExitListener(ActionListener al) {
        exitListeners.add(al);
    }

    /**
     * Gets exit listeners.
     *
     * @return the exit listeners
     */
    public static Collection getExitListeners() {
        return exitListeners;
    }

    private static void exitNotify() {
        for (int i = 0; i < exitListeners.size(); i++)
            ((ActionListener) exitListeners.get(i)).actionPerformed(null);
    }

    /**
     * Sets enabled editor menus.
     *
     * @param enabled the enabled
     */
    public void setEnabledEditorMenus(boolean enabled) {
        this.jMenuEdit.setEnabled(enabled);
        this.jMenuFormat.setEnabled(enabled);
        this.jMenuInsert.setEnabled(enabled);

    }

    /**
     * Do prj pack.
     */
    public void doPrjPack() {
        // Fix until Sun's JVM supports more locales...
        UIManager.put("FileChooser.saveInLabelText", Local
                .getString("Save in:"));
        UIManager.put("FileChooser.upFolderToolTipText", Local.getString(
                "Up One Level"));
        UIManager.put("FileChooser.newFolderToolTipText", Local.getString(
                "Create New Folder"));
        UIManager.put("FileChooser.listViewButtonToolTipText", Local
                .getString("List"));
        UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
                .getString("Details"));
        UIManager.put("FileChooser.fileNameLabelText", Local.getString(
                "File Name:"));
        UIManager.put("FileChooser.filesOfTypeLabelText", Local.getString(
                "Files of Type:"));
        UIManager.put("FileChooser.saveButtonText", Local.getString("Save"));
        UIManager.put("FileChooser.saveButtonToolTipText", Local.getString(
                "Save selected file"));
        UIManager
                .put("FileChooser.cancelButtonText", Local.getString("Cancel"));
        UIManager.put("FileChooser.cancelButtonToolTipText", Local.getString(
                "Cancel"));

        JFileChooser chooser = new JFileChooser();
        chooser.setFileHidingEnabled(false);
        chooser.setDialogTitle(Local.getString("Pack project"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.RTF));
        chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.ZIP));
        // fixes XP style look cosmetical problems JVM 1.4.2 and 1.4.2_01
        chooser.setPreferredSize(new Dimension(550, 375));

        //Added to fix the problem with packing a file then deleting that file.
        //(jcscoobyrs) 17-Nov-2003 at 14:57:06 PM
        //---------------------------------------------------------------------
        File lastSel = null;

        try {
            lastSel = (java.io.File) Context.get("LAST_SELECTED_PACK_FILE");
        }
        catch (ClassCastException cce) {
            lastSel = new File(System.getProperty("user.dir") + File.separator);
        }
        //---------------------------------------------------------------------

        if (lastSel != null)
            chooser.setCurrentDirectory(lastSel);
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
            return;
        Context.put("LAST_SELECTED_PACK_FILE", chooser.getSelectedFile());        
        java.io.File f = chooser.getSelectedFile();
        ProjectPackager.pack(CurrentProject.get(), f);
    }

    /**
     * Do prj un pack.
     */
    public void doPrjUnPack() {
        // Fix until Sun's JVM supports more locales...
        UIManager.put("FileChooser.lookInLabelText", Local
                .getString("Look in:"));
        UIManager.put("FileChooser.upFolderToolTipText", Local.getString(
                "Up One Level"));
        UIManager.put("FileChooser.newFolderToolTipText", Local.getString(
                "Create New Folder"));
        UIManager.put("FileChooser.listViewButtonToolTipText", Local
                .getString("List"));
        UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
                .getString("Details"));
        UIManager.put("FileChooser.fileNameLabelText", Local.getString(
                "File Name:"));
        UIManager.put("FileChooser.filesOfTypeLabelText", Local.getString(
                "Files of Type:"));
        UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
        UIManager.put("FileChooser.openButtonToolTipText", Local.getString(
                "Open selected file"));
        UIManager
                .put("FileChooser.cancelButtonText", Local.getString("Cancel"));
        UIManager.put("FileChooser.cancelButtonToolTipText", Local.getString(
                "Cancel"));

        JFileChooser chooser = new JFileChooser();
        chooser.setFileHidingEnabled(false);
        chooser.setDialogTitle(Local.getString("Unpack project"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.ZIP));
        //chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.RTF));
        // fixes XP style look cosmetical problems JVM 1.4.2 and 1.4.2_01
        chooser.setPreferredSize(new Dimension(550, 375));

        //Added to fix the problem with packing a file then deleting that file.
        //(jcscoobyrs) 17-Nov-2003 at 14:57:06 PM
        //---------------------------------------------------------------------
        File lastSel = null;

        try {
            lastSel = (java.io.File) Context.get("LAST_SELECTED_PACK_FILE");
        }
        catch (ClassCastException cce) {
            lastSel = new File(System.getProperty("user.dir") + File.separator);
        }
        //---------------------------------------------------------------------

        if (lastSel != null)
            chooser.setCurrentDirectory(lastSel);
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
            return;
        Context.put("LAST_SELECTED_PACK_FILE", chooser.getSelectedFile());        
        java.io.File f = chooser.getSelectedFile();
        ProjectPackager.unpack(f);
        projectsPanel.prjTablePanel.updateUI();
    }

    /**
     * Show preferences.
     */
    public void showPreferences() {
        PreferencesDialog dlg = new PreferencesDialog(this);
        dlg.pack();
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
    }

    /**
     * Pp export action performed.
     *
     * @param e the e
     */
    protected void ppExport_actionPerformed(ActionEvent e) {
                // Fix until Sun's JVM supports more locales...
                UIManager.put(
                        "FileChooser.lookInLabelText",
                        Local.getString("Save in:"));
                UIManager.put(
                        "FileChooser.upFolderToolTipText",
                        Local.getString("Up One Level"));
                UIManager.put(
                        "FileChooser.newFolderToolTipText",
                        Local.getString("Create New Folder"));
                UIManager.put(
                        "FileChooser.listViewButtonToolTipText",
                        Local.getString("List"));
                UIManager.put(
                        "FileChooser.detailsViewButtonToolTipText",
                        Local.getString("Details"));
                UIManager.put(
                        "FileChooser.fileNameLabelText",
                        Local.getString("File Name:"));
                UIManager.put(
                        "FileChooser.filesOfTypeLabelText",
                        Local.getString("Files of Type:"));
                UIManager.put("FileChooser.saveButtonText", Local.getString("Save"));
                UIManager.put(
                        "FileChooser.saveButtonToolTipText",
                        Local.getString("Save selected file"));
                UIManager.put(
                        "FileChooser.cancelButtonText",
                        Local.getString("Cancel"));
                UIManager.put(
                        "FileChooser.cancelButtonToolTipText",
                        Local.getString("Cancel"));

                JFileChooser chooser = new JFileChooser();
                chooser.setFileHidingEnabled(false);
                chooser.setDialogTitle(Local.getString("Export notes"));
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.addChoosableFileFilter(
                        new AllFilesFilter(AllFilesFilter.XHTML));
                chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.HTML));

                String lastSel = (String) Context.get("LAST_SELECTED_EXPORT_FILE");
                if (lastSel != null)
                        chooser.setCurrentDirectory(new File(lastSel));

                ProjectExportDialog dlg =
                        new ProjectExportDialog(
                                App.getFrame(),
                                Local.getString("Export notes"),
                                chooser);
                String enc = (String) Context.get("EXPORT_FILE_ENCODING");
                if (enc != null)
                        dlg.encCB.setSelectedItem(enc);
                String spl = (String) Context.get("EXPORT_SPLIT_NOTES");
                if (spl != null)
                        dlg.splitChB.setSelected(spl.equalsIgnoreCase("true"));
                String ti = (String) Context.get("EXPORT_TITLES_AS_HEADERS");
                if (ti != null)
                        dlg.titlesAsHeadersChB.setSelected(ti.equalsIgnoreCase("true"));
                Dimension dlgSize = new Dimension(550, 500);
                dlg.setSize(dlgSize);
                Dimension frmSize = App.getFrame().getSize();
                Point loc = App.getFrame().getLocation();
                dlg.setLocation(
                        (frmSize.width - dlgSize.width) / 2 + loc.x,
                        (frmSize.height - dlgSize.height) / 2 + loc.y);
                dlg.setVisible(true);
                if (dlg.CANCELLED)
                        return;
                
                        Context.put(
                                "LAST_SELECTED_EXPORT_FILE",
                                chooser.getSelectedFile().getPath());
                        Context.put("EXPORT_SPLIT_NOTES", new Boolean(dlg.splitChB.isSelected()).toString());
                        Context.put("EXPORT_TITLES_AS_HEADERS", new Boolean(dlg.titlesAsHeadersChB.isSelected()).toString());
                
                int ei = dlg.encCB.getSelectedIndex();
                enc = null;
                if (ei == 1)
                        enc = "UTF-8";
                boolean nument = (ei == 2);
                File f = chooser.getSelectedFile();
                boolean xhtml =
                        chooser.getFileFilter().getDescription().indexOf("XHTML") > -1;
                 CurrentProject.save();
                 ProjectExporter.export(CurrentProject.get(), chooser.getSelectedFile(), enc, xhtml, 
                                 dlg.splitChB.isSelected(), true, nument, dlg.titlesAsHeadersChB.isSelected(), false); 
                }

    /**
     * Pp import action performed.
     *
     * @param e the e
     */
    protected void ppImport_actionPerformed(ActionEvent e) {
            
            UIManager.put("FileChooser.lookInLabelText", Local
                    .getString("Look in:"));
            UIManager.put("FileChooser.upFolderToolTipText", Local.getString(
                    "Up One Level"));
            UIManager.put("FileChooser.newFolderToolTipText", Local.getString(
                    "Create New Folder"));
            UIManager.put("FileChooser.listViewButtonToolTipText", Local
                    .getString("List"));
            UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
                    .getString("Details"));
            UIManager.put("FileChooser.fileNameLabelText", Local.getString(
                    "File Name:"));
            UIManager.put("FileChooser.filesOfTypeLabelText", Local.getString(
                    "Files of Type:"));
            UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
            UIManager.put("FileChooser.openButtonToolTipText", Local.getString(
                    "Open selected file"));
            UIManager.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
            UIManager.put("FileChooser.cancelButtonToolTipText", Local.getString(
                    "Cancel"));

            JFileChooser chooser = new JFileChooser();
            chooser.setFileHidingEnabled(false);
            chooser.setDialogTitle(Local.getString("Import notes"));
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.HTML));
            chooser.setPreferredSize(new Dimension(550, 375));

            File lastSel = null;

            try {
                lastSel = (java.io.File) Context.get("LAST_SELECTED_NOTE_FILE");
            }
            catch (ClassCastException cce) {
                lastSel = new File(System.getProperty("user.dir") + File.separator);
            }
            //---------------------------------------------------------------------

            if (lastSel != null)
                chooser.setCurrentDirectory(lastSel);
            if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
                return;
            Context.put("LAST_SELECTED_NOTE_FILE", chooser.getSelectedFile());        
            java.io.File f = chooser.getSelectedFile();
            HashMap<String,String> notesName = new HashMap<String,String>();
                HashMap<String,String> notesContent = new HashMap<String,String>();
            Builder parser = new Builder();
            String id="", name="", content = "";
            try{
                    Document document = parser.build(f);
                    Element body = document.getRootElement().getFirstChildElement("body");
                    Element names = body.getFirstChildElement("div").getFirstChildElement("ul");
                    Elements namelist = names.getChildElements("li");
                    Element item;
                    
                    for(int i = 0;i<namelist.size();i++){
                            item = namelist.get(i);
                            id = item.getFirstChildElement("a").getAttributeValue("href").replace("\"","").replace("#","");
                            name = item.getValue();
                            notesName.put(id,name);
                    }
                    System.out.println("id: "+id+" name: "+name);
                    
                    Elements contlist = body.getChildElements("a");
                    for(int i = 0;i<(contlist.size()-1);i++){
                            item = contlist.get(i);
                            id = item.getAttributeValue("name").replace("\"","");
                            content = item.getFirstChildElement("div").getValue();
                            notesContent.put(id,content);
                    }

                    JEditorPane p = new JEditorPane();
                    p.setContentType("text/html");
                    for (Map.Entry<String,String> entry : notesName.entrySet()){
                            id = entry.getKey();
                            name = entry.getValue().substring(11);
                            content = notesContent.get(id);
                            p.setText(content);
                            HTMLDocument doc = (HTMLDocument)p.getDocument();
                            Note note = CurrentProject.getNoteList().createNoteForDate(CurrentDate.get());
                    note.setTitle(name);
                            note.setId(Util.generateId());
                    CurrentStorage.get().storeNote(note, doc);
                    }
                    workPanel.dailyItemsPanel.notesControlPane.refresh();
                    
            }catch(Exception exc){
                    exc.printStackTrace();
            }
        }

    /**
     * P 1 import action performed.
     *
     * @param e the e
     */
    protected void p1Import_actionPerformed(ActionEvent e) {
                
            UIManager.put("FileChooser.lookInLabelText", Local
                    .getString("Look in:"));
            UIManager.put("FileChooser.upFolderToolTipText", Local.getString(
                    "Up One Level"));
            UIManager.put("FileChooser.newFolderToolTipText", Local.getString(
                    "Create New Folder"));
            UIManager.put("FileChooser.listViewButtonToolTipText", Local
                    .getString("List"));
            UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
                    .getString("Details"));
            UIManager.put("FileChooser.fileNameLabelText", Local.getString(
                    "File Name:"));
            UIManager.put("FileChooser.filesOfTypeLabelText", Local.getString(
                    "Files of Type:"));
            UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
            UIManager.put("FileChooser.openButtonToolTipText", Local.getString(
                    "Open selected file"));
            UIManager.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
            UIManager.put("FileChooser.cancelButtonToolTipText", Local.getString(
                    "Cancel"));

            JFileChooser chooser = new JFileChooser();
            chooser.setFileHidingEnabled(false);

            chooser.setDialogTitle(Local.getString("Import notes"));
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.HTML));
            chooser.setPreferredSize(new Dimension(550, 375));

            File lastSel = null;

            try {
                lastSel = (java.io.File) Context.get("LAST_SELECTED_NOTE_FILE");
            }
            catch (ClassCastException cce) {
                lastSel = new File(System.getProperty("user.dir") + File.separator);
            }
            //---------------------------------------------------------------------

            if (lastSel != null)
                chooser.setCurrentDirectory(lastSel);
            if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
                return;
            Context.put("LAST_SELECTED_NOTE_FILE", chooser.getSelectedFile());        
            java.io.File f = chooser.getSelectedFile();
            HashMap<String,String> notesName = new HashMap<String,String>();
            HashMap<String,String> notesContent = new HashMap<String,String>();
            Builder parser = new Builder();
            String id="", name="", content = "";
            try{
                    Document document = parser.build(f);
                    content = document.getRootElement().getFirstChildElement("body").getValue();
                    content = content.substring(content.indexOf("\n", content.indexOf("-")));
                    content = content.replace("<p>","").replace("</p>","\n");
                    name = f.getName().substring(0,f.getName().lastIndexOf("."));	
                    Element item;
                    id=Util.generateId();
                    System.out.println(id+" "+name+" "+content);
                    notesName.put(id, name);
                    notesContent.put(id, content);
                    JEditorPane p = new JEditorPane();
                    p.setContentType("text/html");
                    
                    for (Map.Entry<String,String> entry : notesName.entrySet()){
                            id = entry.getKey();
                            System.out.println(id+" "+name+" "+content);
                            p.setText(content);
                            HTMLDocument doc = (HTMLDocument)p.getDocument();
                            Note note = CurrentProject.getNoteList().createNoteForDate(CurrentDate.get());
                    note.setTitle(name);
                            note.setId(Util.generateId());
                    CurrentStorage.get().storeNote(note, doc);
                    }
                    workPanel.dailyItemsPanel.notesControlPane.refresh();
                    
            }catch(Exception exc){
                    exc.printStackTrace();
            }
        }

}