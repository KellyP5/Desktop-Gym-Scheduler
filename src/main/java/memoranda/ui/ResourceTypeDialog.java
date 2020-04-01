package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import main.java.memoranda.util.Local;

/**
 * The type Resource type dialog.
 */
/*$Id: ResourceTypeDialog.java,v 1.11 2004/07/01 14:44:10 pbielen Exp $*/
public class ResourceTypeDialog extends JDialog {
    /**
     * The Cancel b.
     */
    JButton cancelB = new JButton();
    /**
     * The Buttons panel.
     */
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    /**
     * The Header.
     */
    JLabel header = new JLabel();
    /**
     * The Dialog title panel.
     */
    JPanel dialogTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    /**
     * The Ok b.
     */
    JButton okB = new JButton();
    /**
     * The Area panel.
     */
    ResourceTypePanel areaPanel = new ResourceTypePanel();
    //JPanel mPanel = new JPanel(new BorderLayout());

    /**
     * The Border 2.
     */
    Border border2;
    /**
     * The Titled border 2.
     */
    TitledBorder titledBorder2;
    /**
     * The Ext.
     */
    public String ext = "";
    /**
     * The Cancelled.
     */
    boolean CANCELLED = true;

    /**
     * Instantiates a new Resource type dialog.
     *
     * @param frame the frame
     * @param title the title
     */
    public ResourceTypeDialog(JFrame frame, String title) {
        super(frame, title, true);
        try {
            jbInit();
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
	this.setResizable(false);
        dialogTitlePanel.setBackground(Color.WHITE);
        dialogTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Resource type"));
        header.setIcon(new ImageIcon(main.java.memoranda.ui.ResourceTypeDialog.class.getResource(
            "/ui/icons/resource48.png")));        
        dialogTitlePanel.add(header);
        this.getContentPane().add(dialogTitlePanel, BorderLayout.NORTH);
        
        //mPanel.add(areaPanel, BorderLayout.CENTER);
        this.getContentPane().add(areaPanel, BorderLayout.CENTER);
        
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
        cancelB.setText(Local.getString("Cancel"));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setMaximumSize(new Dimension(100, 26));

        
        okB.setMaximumSize(new Dimension(100, 26));
        okB.setMinimumSize(new Dimension(100, 26));
        okB.setPreferredSize(new Dimension(100, 26));
        okB.setText(Local.getString("Ok"));
        okB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okB_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(okB);
        buttonsPanel.add(okB, null);
        buttonsPanel.add(cancelB, null);
        
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }


    /**
     * Cancel b action performed.
     *
     * @param e the e
     */
    void cancelB_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    /**
     * Ok b action performed.
     *
     * @param e the e
     */
    void okB_actionPerformed(ActionEvent e) {
        CANCELLED = false;
        this.dispose();
    }

    /**
     * Gets types list.
     *
     * @return the types list
     */
    public JList getTypesList() {
      return areaPanel.typesList;
    }



}