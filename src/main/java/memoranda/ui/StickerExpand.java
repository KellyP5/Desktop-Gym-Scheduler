package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.DateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.ui.StickerDialog.ComboBoxRenderer;
import main.java.memoranda.util.Context;
import main.java.memoranda.util.Local;

/**
 * The type Sticker expand.
 */
public class StickerExpand extends JDialog{
    /**
     * The Txt.
     */
    String txt;
    /**
     * The Back ground color.
     */
    Color backGroundColor, /**
     * The Fore ground color.
     */
    foreGroundColor;
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
     * The J scroll pane 1.
     */
    JScrollPane jScrollPane1 = new JScrollPane();
    /**
     * The J panel 1.
     */
    JPanel jPanel1 = new JPanel();
    /**
     * The Sticker text.
     */
    JLabel stickerText = new JLabel();
    /**
     * The J label 1.
     */
    JLabel jLabel1 = new JLabel();
    /**
     * The Border layout 3.
     */
    BorderLayout borderLayout3 = new BorderLayout();

    /**
     * The Border 1.
     */
    Border border1;
    /**
     * The Border 2.
     */
    Border border2;

    /**
     * Instantiates a new Sticker expand.
     *
     * @param frame     the frame
     * @param txt       the txt
     * @param backcolor the backcolor
     * @param fontcolor the fontcolor
     * @param priority  the priority
     */
    public StickerExpand(Frame frame,String txt, String backcolor, String fontcolor, String priority) {
		super(frame, Local.getString("Sticker")+" ["+priority+"]" , true);
		this.txt=txt;
		this.backGroundColor=Color.decode(backcolor);
		this.foreGroundColor=Color.decode(fontcolor);
		try {
			jbInit();
			pack();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
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

		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBorder(new EmptyBorder(new Insets(0, 5, 0, 5)));
		topPanel.setBackground(Color.WHITE);

		jPanel1.setLayout(borderLayout3);
		panel1.setBorder(border1);
		jPanel1.setBorder(border2);

		getContentPane().add(panel1, BorderLayout.CENTER);
		panel1.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getViewport().add(stickerText, null);
		panel1.add(jPanel1, BorderLayout.SOUTH);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		this.getContentPane().add(topPanel, BorderLayout.NORTH);

		stickerText.setText(txt);
		stickerText.setOpaque(true);
		stickerText.setBackground(backGroundColor);
		stickerText.setForeground(foreGroundColor);
	}
}
