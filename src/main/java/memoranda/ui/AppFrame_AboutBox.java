package main.java.memoranda.ui;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.java.memoranda.util.Local;

/**
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
/*$Id: AppFrame_AboutBox.java,v 1.13 2005/11/09 22:38:07 alexeya Exp $*/
public class AppFrame_AboutBox extends JDialog implements ActionListener {

    final int width = 400;
    final int height = 600;
    JButton button1 = new JButton();
    JLabel lblText = new JLabel();
    String copyright = "Copyright (c) 2020 Globo Gym Team";
    String url = App.WEBSITE_URL;
    String developersHead = Local.getString("Developers") + ":";
    String[] developers = {
              "Alex Mack - ajmack5@asu.edu",
              "Kevin Wilkinson - kmwilki4@asu.edu",
              "Kelly Ellis - klellis4@asu.edu",
              "Rita Barrilleaux - mbarrill@asu.edu",
              "Kelly Petrone - kjpetron@asu.edu",
  			  "Kevin Somers - ksomers3@asu.edu"
    };
    String finalHead = Local.getString("Final Note") + ":";
    String[] finalBody = {
              "We'd also like to thank our helpful",
              "UGTA's and instructor for answering questions",
              "<html><center>and guiding us on this project.</center></html>",
    };
    JLayeredPane layeredPane;
    ImageIcon image;
    JLabel imgLabel;

    /**
     * Instantiates a new App frame about box.
     * @param parent the parent
     */
    public AppFrame_AboutBox(Frame parent) {
        super(parent);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
          jbInit();
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        setSize(width, height);
  }
  //Component initialization
  private void jbInit() throws Exception  {    
    String text = "<html>";
    text += "<br><br><br><br><br><br><br><br><br><center>";
    text += copyright + "<br>" + url + "<br><br>";
    text += "<b>" + developersHead + "</b><br>";    
    for (int i = 0; i < developers.length; i++)
        text += developers[i]+"<br>";    
    text += "<br><b>" + finalHead + "</b><br>";
    for (int i = 0; i < finalBody.length; i++)
        text += finalBody[i]+"<br></center>";
    
    text += "</html>";
    
    image = new ImageIcon(AppFrame_AboutBox.class.getResource("/ui/globopeople.jpg"));
    this.setTitle(Local.getString("About Globo Gym"));
    setResizable(false);
    // Initialize Objects
    lblText.setFont(new java.awt.Font("Dialog", 0, 11));
    lblText.setText(text);
    lblText.setBounds(70, 110, 300, 400);

    
    //button1.setText(Local.getString("Ok"));
    button1.setBounds(150, 515, 95, 30);
    button1.addActionListener(this);
    button1.setPreferredSize(new Dimension(95, 30));
    button1.setBackground(new Color(101, 46, 111));
    button1.setForeground(Color.white);
    button1.setText("Ok");
    button1.setFocusable(false);
    layeredPane = getLayeredPane();
    //layeredPane.setPreferredSize(new Dimension(300, 300));
    imgLabel = new JLabel(image);
    imgLabel.setBounds(width/2-(image.getIconWidth()/2), 0, image.getIconWidth(), image.getIconHeight());
    layeredPane.add(imgLabel, new Integer(1));
    //layeredPane.add(lblText, new Integer(2));
    layeredPane.add(lblText);
    layeredPane.add(button1, new Integer(2));
    //this.getContentPane().setBackground(new Color(182, 47, 242));
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }


//Close the dialog
  void cancel() {
    dispose();
  }
  //Close the dialog on a button event
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      cancel();
    }
  }
}
