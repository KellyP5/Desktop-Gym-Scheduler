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
import java.io.File;
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
 * Main JFrame for the application.
 */
public class AppFrame extends JFrame {

    //main contain pane for the wholeeeee application
    private JPanel contentPane;

    //Status bar on the bottum showing the version
    private JLabel statusBar;

    //top menu bar
    private JMenuBar menuBar;
    private JMenu jMenuFile;
    private JMenuItem jMenuFileExit;

    //logout button top right main app
    private Image logoutimg;
    private Image logoutButtonIcon;
    private JButton logoutButton;

    //Not sure what this panel is for yet, but so far its really important!
    public WorkPanel workPanel;

    //This is the action event listener for the preferences tab in the top left under file
    public Action preferencesAction;

    //This is the button inside the toolbar drop down in file
    private JMenuItem jMenuEditPref;

    //Help menu that shows the about page inside top toolbar
    private JMenu jMenuHelp = new JMenu();
    private JMenuItem jMenuHelpAbout = new JMenuItem();

    public AppFrame() throws IOException {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        init();
        initActionEvents();
    }

    /**
     * Initializes our JFrame
     */
    private void init() throws IOException {

        //Instantiation
        this.statusBar = new JLabel();
        this.menuBar = new JMenuBar();
        this.jMenuFile = new JMenu();
        this.jMenuFileExit = new JMenuItem();
        this.logoutimg = ImageIO.read(getClass().getResource("/ui/icons/logoutbutton.png"));
        this.logoutButtonIcon = logoutimg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.logoutButton = new JButton(new ImageIcon(logoutButtonIcon));
        this.workPanel = new WorkPanel();

        this.preferencesAction = new AbstractAction("Preferences") {
            public void actionPerformed(ActionEvent e) {
                showPreferences();
            }
        };
        this.jMenuEditPref = new JMenuItem(preferencesAction);

        this.jMenuHelp = new JMenu();
        this.jMenuHelpAbout = new JMenuItem();
        //End instantiation

        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setLayout(new BorderLayout());

        this.setTitle("GloboGym");
        this.statusBar.setText(" Version:" + App.VERSION_INFO + " (Build "
            + App.BUILD_INFO + " )");
        this.jMenuFile.setText(Local.getString("File"));
        this.jMenuFileExit.setText(Local.getString("Exit"));
        this.jMenuHelp.setText(Local.getString("Help"));
        this.jMenuHelpAbout.setText(Local.getString("About Globo Gym"));

        this.workPanel.setBorder(null);
        this.workPanel.setPreferredSize(new Dimension(1073, 300));

        this.jMenuFile.add(jMenuEditPref);
        this.jMenuFile.add(jMenuFileExit);
        this.jMenuHelp.add(jMenuHelpAbout);
        this.menuBar.add(jMenuFile);
        this.menuBar.add(jMenuHelp);

        this.setJMenuBar(menuBar);

        this.logoutButton.setMaximumSize(new Dimension(5, 30));
        this.logoutButton.setOpaque(false);
        this.logoutButton.setContentAreaFilled(false);
        this.logoutButton.setBorderPainted(false);
        this.menuBar.add(Box.createHorizontalGlue());
        this.menuBar.add(logoutButton);

        this.contentPane.add(statusBar, BorderLayout.SOUTH);
        this.contentPane.add(workPanel);
        workPanel.selectPanel("AGENDA");

    }

    /**
     * Contains most action event handlers.
     */
    private void initActionEvents() {

        this.jMenuHelpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed(e);
            }
        });

        this.jMenuFileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doExit();
            }
        });

        this.logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO
                //LOGOUT FUNCTION WILL BE CALLED HERE.
                //System.out.println("DEBUG: Test");
            }
        });
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

    /**
     * Closes and minmizes the program based on the window event.
     *
     * @param e the event.
     */
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
