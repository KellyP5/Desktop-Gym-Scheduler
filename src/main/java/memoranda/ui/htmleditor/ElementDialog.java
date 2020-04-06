package main.java.memoranda.ui.htmleditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class ElementDialog extends JDialog {
    /**
     * The Area panel.
     */
    JPanel areaPanel = new JPanel(new GridBagLayout());
    /**
     * The Buttons panel.
     */
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    /**
     * The Cancel b.
     */
    JButton cancelB = new JButton();
    /**
     * The Ok b.
     */
    JButton okB = new JButton();
    /**
     * The Lbl class.
     */
    JLabel lblClass = new JLabel();
    /**
     * The Class field.
     */
    public JTextField classField = new JTextField();
    /**
     * The Header panel.
     */
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    /**
     * The Header.
     */
    public JLabel header = new JLabel();
    /**
     * The Cancelled.
     */
    public boolean CANCELLED = false;
    /**
     * The Lbl id.
     */
    JLabel lblID = new JLabel();
    /**
     * The Id field.
     */
    public JTextField idField = new JTextField();
    /**
     * The Lbl style.
     */
    JLabel lblStyle = new JLabel();
    /**
     * The Style field.
     */
    public JTextField styleField = new JTextField();
    /**
     * The Gbc.
     */
    GridBagConstraints gbc;

    /**
     * Instantiates a new Element dialog.
     *
     * @param frame the frame
     */
    public ElementDialog(Frame frame) {
    super(frame, Local.getString("Object properties"), true);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

    /**
     * Instantiates a new Element dialog.
     */
    public ElementDialog() {
    this(null);
  }


    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
	this.setResizable(false);
	headerPanel.setBackground(Color.WHITE);
	header.setFont(new java.awt.Font("Dialog", 0, 20));
	header.setForeground(new Color(0, 0, 124));
	header.setText(Local.getString("Object properties"));
	header.setIcon(new ImageIcon(
		main.java.memoranda.ui.htmleditor.ElementDialog.class.getResource(
		"/htmleditor/icons/textbig.png")));
    headerPanel.add(header);
    this.getContentPane().add(headerPanel, BorderLayout.NORTH);
    
    areaPanel.setBorder(BorderFactory.createEtchedBorder(Color.white,
    	new Color(142, 142, 142)));
	lblID.setText(Local.getString("ID"));
	gbc = new GridBagConstraints();
	gbc.gridx = 0; gbc.gridy = 0;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.insets = new Insets(10, 10, 5, 5);
    areaPanel.add(lblID, gbc);
	idField.setPreferredSize(new Dimension(300, 25));
	gbc = new GridBagConstraints();
	gbc.gridx = 1; gbc.gridy = 0;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.insets = new Insets(10, 5, 5, 10);
	areaPanel.add(idField, gbc);
	lblClass.setText(Local.getString("Class"));
	gbc = new GridBagConstraints();
	gbc.gridx = 0; gbc.gridy = 1;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.insets = new Insets(5, 10, 5, 5);
	areaPanel.add(lblClass, gbc);		
	classField.setPreferredSize(new Dimension(300, 25));
	gbc = new GridBagConstraints();
	gbc.gridx = 1; gbc.gridy = 1;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.insets = new Insets(5, 5, 5, 10);
	areaPanel.add(classField, gbc);
	lblStyle.setText(Local.getString("Style"));
	gbc = new GridBagConstraints();
	gbc.gridx = 0; gbc.gridy = 2;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.insets = new Insets(5, 10, 10, 5);
	areaPanel.add(lblStyle, gbc);
	styleField.setPreferredSize(new Dimension(300, 25));
	gbc = new GridBagConstraints();
	gbc.gridx = 1; gbc.gridy = 2;
	gbc.anchor = GridBagConstraints.WEST;
	gbc.insets = new Insets(5, 5, 10, 10);
	areaPanel.add(styleField, gbc);
	this.getContentPane().add(areaPanel, BorderLayout.CENTER);				
		
    cancelB.setMaximumSize(new Dimension(100, 26));
    cancelB.setMinimumSize(new Dimension(100, 26));
    cancelB.setPreferredSize(new Dimension(100, 26));
    cancelB.setText(Local.getString("Cancel"));
    cancelB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelB_actionPerformed(e);
      }
    });
    okB.setMaximumSize(new Dimension(100, 26));
    okB.setMinimumSize(new Dimension(100, 26));
    okB.setPreferredSize(new Dimension(100, 26));
    okB.setText(Local.getString("Ok"));
    okB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        okB_actionPerformed(e);
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