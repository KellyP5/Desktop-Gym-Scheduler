package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.DateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.util.Context;
import main.java.memoranda.util.Local;


/**
 * The type Sticker confirmation.
 */
public class StickerConfirmation extends JDialog {
    /**
     * The Cancelled.
     */
    public boolean CANCELLED = true;
    /**
     * The Panel 1.
     */
    JPanel panel1 = new JPanel();
    /**
     * The Border layout 1.
     */
    BorderLayout borderLayout1 = new BorderLayout();
    /**
     * The Border layout 2.
     */
    BorderLayout borderLayout2 = new BorderLayout();
    /**
     * The Cancel button.
     */
    JButton cancelButton = new JButton();
    /**
     * The Ok button.
     */
    JButton okButton = new JButton();
    /**
     * The Bottom panel.
     */
    JPanel bottomPanel = new JPanel();
    /**
     * The Top panel.
     */
    JPanel topPanel = new JPanel();
    /**
     * The Header.
     */
    JLabel header = new JLabel();
    /**
     * The J panel 1.
     */
    JPanel jPanel1 = new JPanel();
    /**
     * The J label 1.
     */
    JLabel jLabel1 = new JLabel();

    /**
     * The Border 1.
     */
    Border border1;
    /**
     * The Border 2.
     */
    Border border2;

    /**
     * Instantiates a new Sticker confirmation.
     *
     * @param frame the frame
     */
    public StickerConfirmation(Frame frame) {
                super(frame, Local.getString("Sticker"), true);
                try {
                        jbInit();
                        pack();
                } catch (Exception ex) {
                        new ExceptionDialog(ex);
                }
        }

    /**
     * Instantiates a new Sticker confirmation.
     */
    public StickerConfirmation() {
                this(null);
        }

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
                border1 =
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createEtchedBorder(
                                        Color.white,
                                        new Color(156, 156, 158)),
                                BorderFactory.createEmptyBorder(5, 5, 5, 5));
                border2 = BorderFactory.createEmptyBorder(5, 0, 5, 0);
                panel1.setLayout(borderLayout1);
                this.getContentPane().setLayout(borderLayout2);
                cancelButton.setMaximumSize(new Dimension(100, 25));
                cancelButton.setMinimumSize(new Dimension(100, 25));
                cancelButton.setPreferredSize(new Dimension(100, 25));
                cancelButton.setText(Local.getString("Cancel"));
                cancelButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                cancelButton_actionPerformed(e);
                        }
                });
                okButton.setMaximumSize(new Dimension(100, 25));
                okButton.setMinimumSize(new Dimension(100, 25));
                okButton.setPreferredSize(new Dimension(100, 25));
                okButton.setText(Local.getString("Ok"));
                okButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                okButton_actionPerformed(e);
                        }
                });
                this.getRootPane().setDefaultButton(okButton);
                
                bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                topPanel.setBorder(new EmptyBorder(new Insets(0, 5, 0, 5)));
                topPanel.setBackground(Color.WHITE);

                header.setFont(new java.awt.Font("Dialog", 0, 20));
                header.setForeground(new Color(0, 0, 124));
                header.setText(Local.getString("Sticker"));
                header.setIcon(new ImageIcon(main.java.memoranda.ui.StickerDialog.class.getResource(
            "/ui/icons/sticker48.png")));


                jLabel1.setText(Local.getString("DELETE THIS STICKER?"));
                panel1.setBorder(border1);
                jPanel1.setBorder(border2);
                getContentPane().add(panel1, BorderLayout.CENTER);
                panel1.add(jPanel1, BorderLayout.SOUTH);
                this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
                bottomPanel.add(okButton);
                bottomPanel.add(cancelButton);
                this.getContentPane().add(topPanel, BorderLayout.NORTH);
                topPanel.add(header);
                jPanel1.add(jLabel1, BorderLayout.WEST);
                
        }


    /**
     * Cancel button action performed.
     *
     * @param e the e
     */
    void cancelButton_actionPerformed(ActionEvent e) {
                this.dispose();
        }

    /**
     * Ok button action performed.
     *
     * @param e the e
     */
    void okButton_actionPerformed(ActionEvent e) {
                CANCELLED = false;
                this.dispose();
        }

        
}