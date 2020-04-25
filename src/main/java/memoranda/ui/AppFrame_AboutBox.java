package main.java.memoranda.ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.Border;

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
    
    image = new ImageIcon(AppFrame_AboutBox.class.getResource("/ui/globopeople1.png"));
    this.setTitle(Local.getString("About Globo Gym"));
    setResizable(false);
    // Initialize Objects
    lblText.setFont(new java.awt.Font("Dialog", 0, 11));
    lblText.setText(text);
    lblText.setBounds(70, 110, 300, 400);

    button1.setBounds(150, 515, 95, 30);
    button1.addActionListener(this);
    button1.setPreferredSize(new Dimension(95, 30));
    button1.setBackground(new Color(101, 46, 111));
    button1.setForeground(Color.white);
    button1.setText("Ok");
    button1.setFocusable(false);
    layeredPane = getLayeredPane();
    JButton imageButton = new JButton(image);
    imageButton.setBounds(width/2-(image.getIconWidth()/2), 0, image.getIconWidth(), image.getIconHeight());
    // Hide the button
    Border emptyBorder = BorderFactory.createEmptyBorder();
    imageButton.setBorder(emptyBorder);
    imageButton.setOpaque(false);
    imageButton.setContentAreaFilled(false);
    layeredPane.add(imageButton, new Integer(1));

    // Add Globo Gym sound clip that plays when the picture is clicked
    URL url = getClass().getResource("/ui/betterthanyou.wav");
    AudioClip clip = Applet.newAudioClip(url);

    imageButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            clip.play();
        }
    });

    layeredPane.add(lblText);
    layeredPane.add(button1, new Integer(2));
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
