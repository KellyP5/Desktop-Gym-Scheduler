package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;

import main.java.memoranda.util.Local;

/**
 * The type Set app dialog.
 */
/*$Id: SetAppDialog.java,v 1.6 2004/04/05 10:05:44 alexeya Exp $*/
public class SetAppDialog extends JDialog {
    /**
     * The Flow layout 1.
     */
    FlowLayout flowLayout1 = new FlowLayout();
    /**
     * The Cancel b.
     */
    JButton cancelB = new JButton();
    /**
     * The Border layout 2.
     */
    BorderLayout borderLayout2 = new BorderLayout();
    /**
     * The Border layout 3.
     */
    BorderLayout borderLayout3 = new BorderLayout();
    /**
     * The Buttons panel.
     */
    JPanel buttonsPanel = new JPanel();
    /**
     * The App panel.
     */
    public SetApplicationPanel appPanel = new SetApplicationPanel();
    /**
     * The M panel.
     */
    JPanel mPanel = new JPanel();
    /**
     * The Ok b.
     */
    JButton okB = new JButton();
    /**
     * The Cancelled.
     */
    public boolean CANCELLED = true;

    /**
     * Instantiates a new Set app dialog.
     *
     * @param frame the frame
     * @param title the title
     */
    public SetAppDialog(Frame frame, String title) {
    super(frame, title, true);
    try {
      jbInit();
      pack();
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
    this.setResizable(false);
    cancelB.setMaximumSize(new Dimension(100, 26));
    cancelB.setMinimumSize(new Dimension(100, 26));
    cancelB.setPreferredSize(new Dimension(100, 26));
    cancelB.setText(Local.getString("Cancel"));
    cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    borderLayout3.setHgap(5);
    buttonsPanel.setLayout(flowLayout1);
    appPanel.applicationField.addCaretListener(new javax.swing.event.CaretListener() {
      public void caretUpdate(CaretEvent e) {
        applicationField_caretUpdate(e);
      }
    });

    mPanel.setLayout(borderLayout3);
    okB.setEnabled(false);
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
    mPanel.add(appPanel,  BorderLayout.NORTH);
    mPanel.add(buttonsPanel, BorderLayout.SOUTH);
    this.getContentPane().add(mPanel,  BorderLayout.CENTER);
  }

    /**
     * Sets directory.
     *
     * @param dir the dir
     */
    public void setDirectory(File dir) {
    appPanel.d = dir;
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
    File f = new File(appPanel.applicationField.getText());
    if (f.isFile()) {
      CANCELLED = false;
      this.dispose();
    }
    else {
      JOptionPane.showMessageDialog(App.getFrame(), Local.getString("File not found!"), "", JOptionPane.ERROR_MESSAGE);
      this.appPanel.applicationField.setText("");
      checkOkEnabled();
    }
  }

    /**
     * Application field caret update.
     *
     * @param e the e
     */
    void applicationField_caretUpdate(CaretEvent e) {
    checkOkEnabled();
  }

    /**
     * Check ok enabled.
     */
    void checkOkEnabled() {
    okB.setEnabled(appPanel.applicationField.getText().length() > 0);
  }
}