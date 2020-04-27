package main.java.memoranda.ui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import main.java.memoranda.util.Local;

/**
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
public class AppFrame extends JFrame {

    //main contain pane for the wholeeeee application
    private JPanel contentPane;

    //Status bar on the bottum showing the version
    JLabel statusBar = new JLabel();

    //top menu bar
    JMenuBar menuBar = new JMenuBar();
    JMenu jMenuFile = new JMenu();
    JMenuItem jMenuFileExit = new JMenuItem();

    //logout button top right main app
    Image logoutimg = ImageIO.read(getClass().getResource("/ui/icons/logoutbutton.png"));
    Image logoutButtonIcon = logoutimg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    JButton logoutButton = new JButton(new ImageIcon(logoutButtonIcon));

    //Not sure what this panel is for yet, but so far its really important!
    public WorkPanel workPanel = new WorkPanel();

    //This is the action event listener for the preferences tab in the top left under file
    public Action preferencesAction = new AbstractAction("Preferences") {
        public void actionPerformed(ActionEvent e) {
            showPreferences();
        }
    };

    //This is the button inside the toolbar drop down in file
    JMenuItem jMenuEditPref = new JMenuItem(preferencesAction);


    //Help menu that shows the about page inside top toolbar
    JMenu jMenuHelp = new JMenu();
    JMenuItem jMenuHelpAbout = new JMenuItem();


    public AppFrame() throws IOException {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        init();
    }

    /**
     * Initializes our JFrame
     */
    private void init() {

        this.setTitle("GloboGym");

        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());

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

        jMenuHelpAbout.setText(Local.getString("About Globo Gym"));
        jMenuHelpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed(e);
            }
        });

        workPanel.setBorder(null);
        workPanel.setPreferredSize(new Dimension(1073, 300));

        jMenuFile.add(jMenuEditPref);
        jMenuFile.add(jMenuFileExit);
        jMenuHelp.add(jMenuHelpAbout);

        menuBar.add(jMenuFile);
        menuBar.add(jMenuHelp);

        this.setJMenuBar(menuBar);



        logoutButton.setMaximumSize(new Dimension(5, 30));
        logoutButton.setOpaque(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(logoutButton);


        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO
                //LOGOUT FUNCTION WILL BE CALLED HERE.
                //System.out.println("DEBUG: Test");
            }
        });

        this.contentPane.add(statusBar, BorderLayout.SOUTH);
        this.contentPane.add(workPanel);


    }


    /**
     * Do exit is called when from the top left on the toolbar, file -> Exit
     */
    public void doExit() {
        System.exit(0);
    }

    /**
     * Do minimize is called when minimizing via the top right _ button.
     */
    public void doMinimize() {
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
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
            (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.setVisible(true);
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            doExit();
        } else if ((e.getID() == WindowEvent.WINDOW_ICONIFIED)) {
            doMinimize();
        } else {
            super.processWindowEvent(e);
        }
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

}
