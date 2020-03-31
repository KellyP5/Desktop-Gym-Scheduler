package main.java.memoranda.ui.htmleditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.memoranda.ui.htmleditor.util.Local;

/**
 * The type Continue search dialog.
 */
public class ContinueSearchDialog extends JPanel {
    /**
     * The Panel 1.
     */
    JPanel panel1 = new JPanel();
    /**
     * The Border layout 1.
     */
    BorderLayout borderLayout1 = new BorderLayout();
    /**
     * The Flow layout 1.
     */
    FlowLayout flowLayout1 = new FlowLayout();
    /**
     * The Cancel b.
     */
    JButton cancelB = new JButton();
    /**
     * The Continue b.
     */
    JButton continueB = new JButton();
    /**
     * The Buttons panel.
     */
    JPanel buttonsPanel = new JPanel();
    /**
     * The J label 1.
     */
    JLabel jLabel1 = new JLabel();
    /**
     * The Text f.
     */
    JTextField textF = new JTextField();
    /**
     * The Text.
     */
    String text;

    /**
     * The Thread.
     */
    Thread thread;

    /**
     * The Cont.
     */
    public boolean cont = false;
    /**
     * The Cancel.
     */
    public boolean cancel = false;

    /**
     * Instantiates a new Continue search dialog.
     *
     * @param t   the t
     * @param txt the txt
     */
    public ContinueSearchDialog(Thread t, String txt) {
    try {
      text = txt;   
      thread = t;
      jbInit();      
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }


    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
  
    this.setLayout(borderLayout1);
    textF.setEditable(false);
    textF.setText(text);
    cancelB.setMaximumSize(new Dimension(120, 26));
    cancelB.setMinimumSize(new Dimension(80, 26));
    cancelB.setPreferredSize(new Dimension(120, 26));
    cancelB.setText(Local.getString("Cancel"));
    cancelB.setFocusable(false);
    cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
    continueB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continueB_actionPerformed(e);
            }
        });
    continueB.setText(Local.getString("Find next"));
    continueB.setPreferredSize(new Dimension(120, 26));
    continueB.setMinimumSize(new Dimension(80, 26));
    continueB.setMaximumSize(new Dimension(120, 26));
    continueB.setFocusable(false);    
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    buttonsPanel.setLayout(flowLayout1);
    
    jLabel1.setText(" "+Local.getString("Search for")+":  ");
    jLabel1.setIcon(new ImageIcon(main.java.memoranda.ui.htmleditor.HTMLEditor.class.getResource("/htmleditor/icons/findbig.png"))) ;   
    this.add(jLabel1, BorderLayout.WEST);
    this.add(textF,BorderLayout.CENTER);    
    buttonsPanel.add(continueB, null);
    buttonsPanel.add(cancelB, null);
    this.add(buttonsPanel,  BorderLayout.EAST);
  }

    /**
     * Cancel b action performed.
     *
     * @param e the e
     */
    void cancelB_actionPerformed(ActionEvent e) {
    cont = true;
    cancel = true;    
    thread.resume();
  }

    /**
     * Continue b action performed.
     *
     * @param e the e
     */
    void continueB_actionPerformed(ActionEvent e) {
     cont = true;     
     thread.resume();
  }
}