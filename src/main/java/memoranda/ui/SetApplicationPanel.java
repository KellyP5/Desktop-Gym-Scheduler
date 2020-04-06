package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;

import main.java.memoranda.util.Local;

/**
 * The type Set application panel.
 */
/*$Id: SetApplicationPanel.java,v 1.6 2004/04/05 10:05:44 alexeya Exp $*/
public class SetApplicationPanel extends JPanel {
    /**
     * The Border layout 1.
     */
    BorderLayout borderLayout1 = new BorderLayout();
    /**
     * The Application field.
     */
    public JTextField applicationField = new JTextField();
    /**
     * The Border layout 5.
     */
    BorderLayout borderLayout5 = new BorderLayout();
    /**
     * The Select app browse b.
     */
    JButton selectAppBrowseB = new JButton();
    /**
     * The J panel 3.
     */
    JPanel jPanel3 = new JPanel();
    /**
     * The Border 1.
     */
    Border border1;
    /**
     * The Titled border 1.
     */
    TitledBorder titledBorder1;
    /**
     * The Arguments field.
     */
    public JTextField argumentsField = new JTextField();
    /**
     * The Border layout 6.
     */
    BorderLayout borderLayout6 = new BorderLayout();
    /**
     * The J panel 4.
     */
    JPanel jPanel4 = new JPanel();
    /**
     * The Border 2.
     */
    Border border2;
    /**
     * The Titled border 2.
     */
    TitledBorder titledBorder2;
    /**
     * The Arg help label.
     */
    JLabel argHelpLabel = new JLabel();
    /**
     * The D.
     */
    public File d = null;

    /**
     * Instantiates a new Set application panel.
     */
    public SetApplicationPanel() {
        try {
            jbInit();
        }
        catch(Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
        border1 = BorderFactory.createEmptyBorder();
        titledBorder1 = new TitledBorder(BorderFactory.createEmptyBorder(),Local.getString("Path to executable"));
        border2 = BorderFactory.createEmptyBorder();
        titledBorder2 = new TitledBorder(border2, Local.getString("Arguments"));
        jPanel3.setLayout(borderLayout5);
        selectAppBrowseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectAppBrowseB_actionPerformed(e);
            }
        });
        selectAppBrowseB.setText(Local.getString("Browse"));
        applicationField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(CaretEvent e) {
                applicationField_caretUpdate(e);
            }
        });
        applicationField.setPreferredSize(new Dimension(300, 24));
        applicationField.setMinimumSize(new Dimension(4, 24));
        this.setLayout(borderLayout1);
        jPanel3.setBorder(titledBorder1);
        argumentsField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(CaretEvent e) {
                argumentsField_caretUpdate(e);
            }
        });
        argumentsField.setPreferredSize(new Dimension(300, 24));
        argumentsField.setText("$1");
        argumentsField.setMinimumSize(new Dimension(4, 24));
        jPanel4.setBorder(titledBorder2);
        jPanel4.setLayout(borderLayout6);
        argHelpLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        argHelpLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        argHelpLabel.setText(Local.getString("Use $1 as an alias of the file to open"));
        jPanel3.add(applicationField, BorderLayout.CENTER);
        jPanel3.add(selectAppBrowseB, BorderLayout.EAST);
        this.add(jPanel4,  BorderLayout.SOUTH);
        jPanel4.add(argumentsField, BorderLayout.CENTER);
        jPanel4.add(argHelpLabel,  BorderLayout.SOUTH);
        this.add(jPanel3,  BorderLayout.NORTH);
    }

    /**
     * Application field caret update.
     *
     * @param e the e
     */
    void applicationField_caretUpdate(CaretEvent e) {
        
    }

    /**
     * Select app browse b action performed.
     *
     * @param e the e
     */
    void selectAppBrowseB_actionPerformed(ActionEvent e) {
        // Fix until Sun's JVM supports more locales...
        UIManager.put("FileChooser.lookInLabelText", Local.getString("Look in:"));
        UIManager.put("FileChooser.upFolderToolTipText", Local.getString("Up One Level"));
        UIManager.put("FileChooser.newFolderToolTipText", Local.getString("Create New Folder"));
        UIManager.put("FileChooser.listViewButtonToolTipText", Local.getString("List"));
        UIManager.put("FileChooser.detailsViewButtonToolTipText", Local.getString("Details"));
        UIManager.put("FileChooser.fileNameLabelText", Local.getString("File Name:"));
        UIManager.put("FileChooser.filesOfTypeLabelText", Local.getString("Files of Type:"));
        UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
        UIManager.put("FileChooser.openButtonToolTipText", Local.getString("Open selected file"));
        UIManager.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
        UIManager.put("FileChooser.cancelButtonToolTipText", Local.getString("Cancel"));
        UIManager.put("FileChooser.acceptAllFileFilterText", Local.getString("All Files") + " (*.*)");
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(Local.getString("Path to executable"));
        chooser.setFileHidingEnabled(false);
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (System.getProperty("os.name").startsWith("Win")) {
            chooser.setFileFilter(new AllFilesFilter(AllFilesFilter.EXE));
            chooser.setCurrentDirectory(new File("C:\\Program Files"));
        }
        chooser.setPreferredSize(new Dimension(550, 375));
        /*
            java.io.File lastSel = (java.io.File) Context.get("LAST_SELECTED_IMPORT_FILE");
            if (lastSel != null)
                chooser.setCurrentDirectory(lastSel);
        */
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            applicationField.setText(chooser.getSelectedFile().getPath());
        }

    /**
     * Arguments field caret update.
     *
     * @param e the e
     */
    void argumentsField_caretUpdate(CaretEvent e) {
            
        }
}
