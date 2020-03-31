package main.java.memoranda.ui.htmleditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.memoranda.ui.htmleditor.util.Local;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class LinkDialog extends JDialog {
    /**
     * The Top panel.
     */
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    /**
     * The Header.
     */
    public JLabel header = new JLabel();
    /**
     * The Area panel.
     */
    JPanel areaPanel = new JPanel(new GridBagLayout());
    /**
     * The Gbc.
     */
    GridBagConstraints gbc;
    /**
     * The Lbl url.
     */
    JLabel lblURL = new JLabel();
    /**
     * The Txt url.
     */
    public JTextField txtURL = new JTextField();
    /**
     * The Lbl name.
     */
    JLabel lblName = new JLabel();
    /**
     * The Txt name.
     */
    public JTextField txtName = new JTextField();
    /**
     * The Lbl title.
     */
    JLabel lblTitle = new JLabel();
    /**
     * The Txt title.
     */
    public JTextField txtTitle = new JTextField();
    /**
     * The Lbl desc.
     */
    JLabel lblDesc = new JLabel();
    /**
     * The Txt desc.
     */
    JTextField txtDesc = new JTextField();
    /**
     * The Chk new win.
     */
    JCheckBox chkNewWin = new JCheckBox();
    /**
     * The Buttons panel.
     */
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    /**
     * The Ok b.
     */
    JButton okB = new JButton();
    /**
     * The Cancel b.
     */
    JButton cancelB = new JButton();
    /**
     * The Aligns.
     */
    String[] aligns = {"", Local.getString("left"),
  Local.getString("center"), Local.getString("right")};
    /**
     * The Cancelled.
     */
    public boolean CANCELLED = false;

    /**
     * Instantiates a new Link dialog.
     *
     * @param frame the frame
     */
    public LinkDialog(Frame frame) {
    super(frame, Local.getString("Insert hyperlink"), true);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

    /**
     * Instantiates a new Link dialog.
     */
    public LinkDialog() {
    this(null);
  }


    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
	this.setResizable(false);
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Insert hyperlink"));
        header.setIcon(new ImageIcon(
            main.java.memoranda.ui.htmleditor.ImageDialog.class.getResource(
            "/htmleditor/icons/linkbig.png")));
        topPanel.setBackground(Color.WHITE);
        //topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(header);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        
        lblURL.setText(Local.getString("URL"));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(lblURL, gbc);
        txtURL.setPreferredSize(new Dimension(300, 25));
        txtURL.setText("http://");
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(txtURL, gbc);
        lblName.setText(Local.getString("Name"));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(lblName, gbc);
        txtName.setPreferredSize(new Dimension(300, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(txtName, gbc);
        lblTitle.setText(Local.getString("Title"));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(lblTitle, gbc);
        txtTitle.setPreferredSize(new Dimension(300, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(txtTitle, gbc);
        lblDesc.setText(Local.getString("Description"));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(lblDesc, gbc);
        txtDesc.setPreferredSize(new Dimension(300, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(txtDesc, gbc);
        chkNewWin.setText(Local.getString("Open in a new window"));
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(chkNewWin, gbc);
        areaPanel.setBorder(BorderFactory.createEtchedBorder(Color.white,
            new Color(142, 142, 142)));
        this.getContentPane().add(areaPanel, BorderLayout.CENTER);
        
        okB.setMaximumSize(new Dimension(100, 26));
        okB.setMinimumSize(new Dimension(100, 26));
        okB.setPreferredSize(new Dimension(100, 26));
        okB.setText("Ok");
        okB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okB_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(okB);
    cancelB.setMaximumSize(new Dimension(100, 26));
    cancelB.setMinimumSize(new Dimension(100, 26));
    cancelB.setPreferredSize(new Dimension(100, 26));
    cancelB.setText(Local.getString("Cancel"));
    cancelB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelB_actionPerformed(e);
      }
    });
        buttonsPanel.add(okB);
        buttonsPanel.add(cancelB);
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        
  }

    /**
     * Ok b action performed.
     *
     * @param e the e
     */
    void okB_actionPerformed(ActionEvent e) {
    this.dispose();
  }

    /**
     * Cancel b action performed.
     *
     * @param e the e
     */
    void cancelB_actionPerformed(ActionEvent e) {
    CANCELLED = true;
    this.dispose();
  }
}