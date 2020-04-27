package main.java.memoranda.ui;

import main.java.memoranda.util.Configuration;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.MimeTypesList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

/**
 * The type Preferences dialog.
 */
/*$Id: PreferencesDialog.java,v 1.16 2006/06/28 22:58:31 alexeya Exp $*/
public class PreferencesDialog extends JDialog {
    /**
     * The Top panel.
     */
    JPanel topPanel = new JPanel(new BorderLayout());

    /**
     * The Tabbed panel.
     */
    JTabbedPane tabbedPanel = new JTabbedPane();

    /**
     * The General panel.
     */
    JPanel GeneralPanel = new JPanel(new GridBagLayout());

    /**
     * The Gbc.
     */
    GridBagConstraints gbc;

    /**
     * The J label 1.
     */
    JLabel jLabel1 = new JLabel();

    /**
     * The Min group.
     */
    ButtonGroup minGroup = new ButtonGroup();

    /**
     * The Min taskbar rb.
     */
    JRadioButton minTaskbarRB = new JRadioButton();

    /**
     * The Min hide rb.
     */
    JRadioButton minHideRB = new JRadioButton();

    /**
     * The Close group.
     */
    ButtonGroup closeGroup = new ButtonGroup();

    /**
     * The J label 2.
     */
    JLabel jLabel2 = new JLabel();

    /**
     * The Close exit rb.
     */
    JRadioButton closeExitRB = new JRadioButton();

    /**
     * The Ask confirm ch b.
     */
    JCheckBox askConfirmChB = new JCheckBox();

    /**
     * The Close hide rb.
     */
    JRadioButton closeHideRB = new JRadioButton();

    /**
     * The J label 3.
     */
    JLabel jLabel3 = new JLabel();

    /**
     * The Lf group.
     */
    ButtonGroup lfGroup = new ButtonGroup();

    /**
     * The Lf system rb.
     */
    JRadioButton lfSystemRB = new JRadioButton();

    /**
     * The Lf java rb.
     */
    JRadioButton lfJavaRB = new JRadioButton();

    /**
     * The Lf custom rb.
     */
    JRadioButton lfCustomRB = new JRadioButton();

    /**
     * The Class name label.
     */
    JLabel classNameLabel = new JLabel();

    /**
     * The Lf class name.
     */
    JTextField lfClassName = new JTextField();

    /**
     * The J label 4.
     */
    JLabel jLabel4 = new JLabel();

    /**
     * The En systray ch b.
     */
    JCheckBox enSystrayChB = new JCheckBox();

    /**
     * The Start minimized ch b.
     */
    JCheckBox startMinimizedChB = new JCheckBox();

    /**
     * The En splash ch b.
     */
    JCheckBox enSplashChB = new JCheckBox();

    /**
     * The En l 10 n ch b.
     */
    JCheckBox enL10nChB = new JCheckBox();

    /**
     * The Firstdow.
     */
    JCheckBox firstdow = new JCheckBox();

    /**
     * The Resource panel.
     */
    JPanel resourcePanel = new JPanel(new BorderLayout());


    /**
     * The Rst panel border.
     */
    Border rstPanelBorder;

    /**
     * The Rs bottom panel.
     */
    JPanel rsBottomPanel = new JPanel(new GridBagLayout());

    /**
     * The Rsbp border.
     */
    TitledBorder rsbpBorder;

    /**
     * The Ok b.
     */
    JButton okB = new JButton();

    /**
     * The Cancel b.
     */
    JButton cancelB = new JButton();

    /**
     * The Bottom panel.
     */
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

    /**
     * The J label 5.
     */
    JLabel jLabel5 = new JLabel();

    /**
     * The Browser path.
     */
    JTextField browserPath = new JTextField();

    /**
     * The Browse b.
     */
    JButton browseB = new JButton();

    /**
     * The Lbl exit.
     */
    JLabel lblExit = new JLabel();

    /**
     * The Sound panel.
     */
    JPanel soundPanel = new JPanel();

    /**
     * The Enable sound cb.
     */
    JCheckBox enableSoundCB = new JCheckBox();

    /**
     * The Border layout 1.
     */
    BorderLayout borderLayout1 = new BorderLayout();

    /**
     * The Titled border 1.
     */
    TitledBorder titledBorder1;

    /**
     * The Sound group.
     */
    ButtonGroup soundGroup = new ButtonGroup();

    /**
     * The J panel 2.
     */
    JPanel jPanel2 = new JPanel();

    /**
     * The Sound file browse b.
     */
    JButton soundFileBrowseB = new JButton();

    /**
     * The Grid layout 1.
     */
    GridLayout gridLayout1 = new GridLayout();

    /**
     * The J panel 1.
     */
    JPanel jPanel1 = new JPanel();

    /**
     * The Sound beep rb.
     */
    JRadioButton soundBeepRB = new JRadioButton();

    /**
     * The J label 6.
     */
    JLabel jLabel6 = new JLabel();

    /**
     * The Sound file.
     */
    JTextField soundFile = new JTextField();

    /**
     * The Sound default rb.
     */
    JRadioButton soundDefaultRB = new JRadioButton();

    /**
     * The Border layout 3.
     */
    BorderLayout borderLayout3 = new BorderLayout();

    /**
     * The J panel 3.
     */
    JPanel jPanel3 = new JPanel();

    /**
     * The Sound custom rb.
     */
    JRadioButton soundCustomRB = new JRadioButton();

    /**
     * The Border layout 2.
     */
    BorderLayout borderLayout2 = new BorderLayout();

    /**
     * The Editor config panel.
     */
    JPanel editorConfigPanel = new JPanel(new BorderLayout());
    /**
     * The Econf panel.
     */
    JPanel econfPanel = new JPanel(new GridLayout(5, 2));
    /**
     * The Fontnames.
     */
    Vector fontnames = getFontNames();
    /**
     * The Normal font cb.
     */
    JComboBox normalFontCB = new JComboBox(fontnames);
    /**
     * The Header font cb.
     */
    JComboBox headerFontCB = new JComboBox(fontnames);
    /**
     * The Mono font cb.
     */
    JComboBox monoFontCB = new JComboBox(fontnames);
    /**
     * The Base font size.
     */
    JSpinner baseFontSize = new JSpinner();
    /**
     * The Antialias ch b.
     */
    JCheckBox antialiasChB = new JCheckBox();
    /**
     * The Normal font label.
     */
    JLabel normalFontLabel = new JLabel();
    /**
     * The Header font label.
     */
    JLabel headerFontLabel = new JLabel();
    /**
     * The Mono font label.
     */
    JLabel monoFontLabel = new JLabel();
    /**
     * The Base font size label.
     */
    JLabel baseFontSizeLabel = new JLabel();

    /**
     * Instantiates a new Preferences dialog.
     *
     * @param frame the frame
     */
    public PreferencesDialog(Frame frame) {
		super(frame, Local.getString("Preferences"), true);
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

    /**
     * Instantiates a new Preferences dialog.
     */
    public PreferencesDialog() {
		this(null);
	}

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
		titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(
				Color.white, new Color(156, 156, 158)), Local
				.getString("Sound"));
		this.setResizable(false);
		// Build Tab1
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setText(Local.getString("Window minimize action:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		enableSoundCB.setText(Local.getString("Enable sound notifications"));
		enableSoundCB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableSoundCB_actionPerformed(e);
			}
		});
		soundPanel.setLayout(borderLayout1);
		soundFileBrowseB.setText(Local.getString("Browse"));
		soundFileBrowseB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundFileBrowseB_actionPerformed(e);
			}
		});
		gridLayout1.setRows(4);
		jPanel1.setBorder(titledBorder1);
		jPanel1.setLayout(gridLayout1);
		soundBeepRB.setText(Local.getString("System beep"));
		soundBeepRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundBeepRB_actionPerformed(e);
			}
		});
		jLabel6.setText(Local.getString("Sound file") + ":");
		soundDefaultRB.setText(Local.getString("Default"));
		soundDefaultRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundDefaultRB_actionPerformed(e);
			}
		});
		jPanel3.setLayout(borderLayout3);
		soundCustomRB.setText(Local.getString("Custom"));
		soundCustomRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundCustomRB_actionPerformed(e);
			}
		});
		jPanel2.setLayout(borderLayout2);
		soundPanel.add(jPanel2, BorderLayout.CENTER);
		jPanel2.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(soundDefaultRB, null);
		jPanel1.add(soundBeepRB, null);
		jPanel1.add(soundCustomRB, null);
		this.soundGroup.add(soundDefaultRB);
		this.soundGroup.add(soundBeepRB);
		this.soundGroup.add(soundCustomRB);
		jPanel1.add(jPanel3, null);
		jPanel3.add(soundFile, BorderLayout.CENTER);
		jPanel3.add(soundFileBrowseB, BorderLayout.EAST);
		jPanel3.add(jLabel6, BorderLayout.WEST);
		GeneralPanel.add(jLabel1, gbc);
		minGroup.add(minTaskbarRB);
		minTaskbarRB.setSelected(true);
		minTaskbarRB.setText(Local.getString("Minimize to taskbar"));
		minTaskbarRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minTaskbarRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(minTaskbarRB, gbc);
		minGroup.add(minHideRB);
		minHideRB.setText(Local.getString("Hide"));
		minHideRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minHideRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(minHideRB, gbc);
		jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel2.setText(Local.getString("Window close action:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(2, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel2, gbc);
		closeGroup.add(closeExitRB);
		closeExitRB.setSelected(true);
		closeExitRB.setText(Local.getString("Close and exit"));
		closeExitRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeExitRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(closeExitRB, gbc);

		closeGroup.add(closeHideRB);
		closeHideRB.setText(Local.getString("Hide"));
		closeHideRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeHideRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(closeHideRB, gbc);
		jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel4.setText(Local.getString("Startup:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(2, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel4, gbc);
		enSystrayChB.setText(Local.getString("Enable system tray icon"));
		enSystrayChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enSystrayChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enSystrayChB, gbc);
		startMinimizedChB.setText(Local.getString("Start minimized"));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(startMinimizedChB, gbc);
		enSplashChB.setText(Local.getString("Show splash screen"));
		enSplashChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enSplashChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 12;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enSplashChB, gbc);
		enL10nChB.setText(Local.getString("Enable localization"));
		enL10nChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enL10nChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 13;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enL10nChB, gbc);
		firstdow.setText(Local.getString("First day of week - Monday"));
		firstdow.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 14;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(firstdow, gbc);
		lblExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExit.setText(Local.getString("Exit") + ":");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 15;
		gbc.insets = new Insets(2, 10, 10, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(lblExit, gbc);
		askConfirmChB.setSelected(true);
		askConfirmChB.setText(Local.getString("Ask confirmation"));
		askConfirmChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				askConfirmChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 15;
		gbc.insets = new Insets(2, 0, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(askConfirmChB, gbc);

		// Build Tab2
		rstPanelBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		rsbpBorder = new TitledBorder(BorderFactory.createEmptyBorder(5, 5, 5,
				5), Local.getString("Web browser executable"));
		rsBottomPanel.setBorder(rsbpBorder);
		jLabel5.setText(Local.getString("Path") + ":");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.anchor = GridBagConstraints.WEST;
		rsBottomPanel.add(jLabel5, gbc);
		browserPath.setPreferredSize(new Dimension(250, 25));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 5, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rsBottomPanel.add(browserPath, gbc);
		browseB.setText(Local.getString("Browse"));
		browseB.setPreferredSize(new Dimension(110, 25));
		browseB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		// gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.EAST;
		rsBottomPanel.add(browseB, gbc);

		resourcePanel.add(rsBottomPanel, BorderLayout.SOUTH);
		
		// Build editorConfigPanel
		normalFontLabel.setText(Local.getString("Normal text font"));
		normalFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		headerFontLabel.setText(Local.getString("Header font"));
		headerFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		monoFontLabel.setText(Local.getString("Monospaced font"));
		monoFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		baseFontSizeLabel.setText(Local.getString("Base font size"));
		baseFontSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		antialiasChB.setText(Local.getString("Antialias text"));
		JPanel bfsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		bfsPanel.add(baseFontSize);
		econfPanel.add(normalFontLabel);
		econfPanel.add(normalFontCB);
		econfPanel.add(headerFontLabel);
		econfPanel.add(headerFontCB);
		econfPanel.add(monoFontLabel);
		econfPanel.add(monoFontCB);
		econfPanel.add(baseFontSizeLabel);
		econfPanel.add(bfsPanel);
		econfPanel.add(antialiasChB);
		econfPanel.setBorder(BorderFactory.createEmptyBorder(10,5,10,10));
		((GridLayout)econfPanel.getLayout()).setHgap(10);
		((GridLayout)econfPanel.getLayout()).setVgap(5);
		editorConfigPanel.add(econfPanel, BorderLayout.NORTH);
		// Build TabbedPanel
		tabbedPanel.add(GeneralPanel, Local.getString("General"));
		tabbedPanel.add(resourcePanel, Local.getString("Resource types"));
		tabbedPanel.add(soundPanel, Local.getString("Sound"));
		tabbedPanel.add(editorConfigPanel, Local.getString("Editor"));

		// Build TopPanel
		topPanel.add(tabbedPanel, BorderLayout.CENTER);

		// Build BottomPanel
		okB.setMaximumSize(new Dimension(100, 25));
		okB.setPreferredSize(new Dimension(100, 25));
		okB.setText(Local.getString("Ok"));
		okB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okB_actionPerformed(e);
			}
		});
		this.getRootPane().setDefaultButton(okB);
		bottomPanel.add(okB);
		cancelB.setMaximumSize(new Dimension(100, 25));
		cancelB.setPreferredSize(new Dimension(100, 25));
		cancelB.setText(Local.getString("Cancel"));
		cancelB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelB_actionPerformed(e);
			}
		});
		bottomPanel.add(cancelB);

		// Build Preferences-Dialog
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		soundPanel.add(enableSoundCB, BorderLayout.NORTH);

		// set all config-values
		setValues();

	}

    /**
     * Sets values.
     */
    void setValues() {
		enL10nChB.setSelected(!Configuration.get("DISABLE_L10N").toString()
				.equalsIgnoreCase("yes"));
		enSplashChB.setSelected(!Configuration.get("SHOW_SPLASH").toString()
				.equalsIgnoreCase("no"));
		enSystrayChB.setSelected(!Configuration.get("DISABLE_SYSTRAY")
				.toString().equalsIgnoreCase("yes"));
		startMinimizedChB.setSelected(Configuration.get("START_MINIMIZED")
				.toString().equalsIgnoreCase("yes"));
		firstdow.setSelected(Configuration.get("FIRST_DAY_OF_WEEK").toString()
				.equalsIgnoreCase("mon"));

		enableCustomLF(false);
		String lf = Configuration.get("LOOK_AND_FEEL").toString();
		if (lf.equalsIgnoreCase("system"))
			lfSystemRB.setSelected(true);
		else if (lf.equalsIgnoreCase("default"))
			lfJavaRB.setSelected(true);
		else if (lf.length() > 0) {
			lfCustomRB.setSelected(true);
			enableCustomLF(true);
			lfClassName.setText(lf);
		} else
			lfJavaRB.setSelected(true);

		askConfirmChB.setSelected(!Configuration.get("ASK_ON_EXIT").toString()
				.equalsIgnoreCase("no"));
		String onclose = Configuration.get("ON_CLOSE").toString();
		if (onclose.equals("exit")) {
			this.closeExitRB.setSelected(true);
			// this.askConfirmChB.setEnabled(true);
		} else {
			this.closeHideRB.setSelected(true);
			// this.askConfirmChB.setEnabled(false);
		}

		String onmin = Configuration.get("ON_MINIMIZE").toString();
		this.minTaskbarRB.setSelected(true);

		if (!System.getProperty("os.name").startsWith("Win"))
			this.browserPath.setText(MimeTypesList.getAppList()
					.getBrowserExec());
		if (Configuration.get("NOTIFY_SOUND").equals("")) {
			Configuration.put("NOTIFY_SOUND", "DEFAULT");
		}

		boolean enableSnd = !Configuration.get("NOTIFY_SOUND").toString()
				.equalsIgnoreCase("DISABLED");
		enableSoundCB.setSelected(enableSnd);
		if (Configuration.get("NOTIFY_SOUND").toString().equalsIgnoreCase(
				"DEFAULT")
				|| Configuration.get("NOTIFY_SOUND").toString()
						.equalsIgnoreCase("DISABLED")) {
			this.soundDefaultRB.setSelected(true);
			this.enableCustomSound(false);
		} else if (Configuration.get("NOTIFY_SOUND").toString()
				.equalsIgnoreCase("BEEP")) {
			this.soundBeepRB.setSelected(true);
			this.enableCustomSound(false);
		} else {
			System.out.println(Configuration.get("NOTIFY_SOUND").toString());
			this.soundCustomRB.setSelected(true);
			this.soundFile
					.setText(Configuration.get("NOTIFY_SOUND").toString());
			this.enableCustomSound(true);
		}
		this.enableSound(enableSnd);
		
		antialiasChB.setSelected(Configuration.get("ANTIALIAS_TEXT")
				.toString().equalsIgnoreCase("yes"));
		if (Configuration.get("NORMAL_FONT").toString().length() >0)
			normalFontCB.setSelectedItem(Configuration.get("NORMAL_FONT").toString());
		else
			normalFontCB.setSelectedItem("serif");
		if (Configuration.get("HEADER_FONT").toString().length() >0)
			headerFontCB.setSelectedItem(Configuration.get("HEADER_FONT").toString());
		else
			headerFontCB.setSelectedItem("sans-serif");
		if (Configuration.get("MONO_FONT").toString().length() >0)
			monoFontCB.setSelectedItem(Configuration.get("MONO_FONT").toString());
		else
			monoFontCB.setSelectedItem("monospaced");
		if (Configuration.get("BASE_FONT_SIZE").toString().length() >0)
			baseFontSize.setValue(Integer.decode(Configuration.get("BASE_FONT_SIZE").toString()));
		else
			baseFontSize.setValue(new Integer(16));
	}

    /**
     * Apply.
     */
    void apply() {
		if (this.firstdow.isSelected())
			Configuration.put("FIRST_DAY_OF_WEEK", "mon");
		else
			Configuration.put("FIRST_DAY_OF_WEEK", "sun");

		if (this.enL10nChB.isSelected())
			Configuration.put("DISABLE_L10N", "no");
		else
			Configuration.put("DISABLE_L10N", "yes");

		if (this.enSplashChB.isSelected())
			Configuration.put("SHOW_SPLASH", "yes");
		else
			Configuration.put("SHOW_SPLASH", "no");

		if (this.enSystrayChB.isSelected())
			Configuration.put("DISABLE_SYSTRAY", "no");
		else
			Configuration.put("DISABLE_SYSTRAY", "yes");

		if (this.startMinimizedChB.isSelected())
			Configuration.put("START_MINIMIZED", "yes");
		else
			Configuration.put("START_MINIMIZED", "no");

		if (this.askConfirmChB.isSelected())
			Configuration.put("ASK_ON_EXIT", "yes");
		else
			Configuration.put("ASK_ON_EXIT", "no");

		if (this.closeExitRB.isSelected())
			Configuration.put("ON_CLOSE", "exit");
		else
			Configuration.put("ON_CLOSE", "minimize");

		Configuration.put("ON_MINIMIZE", "normal");

		String lf = Configuration.get("LOOK_AND_FEEL").toString();
		String newlf = "";

		if (this.lfSystemRB.isSelected())
			newlf = "system";
		else if (this.lfJavaRB.isSelected())
			newlf = "default";
		else if (this.lfCustomRB.isSelected())
			newlf = this.lfClassName.getText();

		if (!lf.equalsIgnoreCase(newlf)) {
			Configuration.put("LOOK_AND_FEEL", newlf);
			try {
				if (Configuration.get("LOOK_AND_FEEL").equals("system"))
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				else if (Configuration.get("LOOK_AND_FEEL").equals("default"))
					UIManager.setLookAndFeel(UIManager
							.getCrossPlatformLookAndFeelClassName());
				else if (Configuration.get("LOOK_AND_FEEL").toString().length() > 0)
					UIManager.setLookAndFeel(Configuration.get("LOOK_AND_FEEL")
							.toString());

				SwingUtilities.updateComponentTreeUI(App.getFrame());

			} catch (Exception e) {
				Configuration.put("LOOK_AND_FEEL", lf);
				new ExceptionDialog(
						e,
						"Error when initializing a pluggable look-and-feel. Default LF will be used.",
						"Make sure that specified look-and-feel library classes are on the CLASSPATH.");
			}
		}
		String brPath = this.browserPath.getText();
		if (new java.io.File(brPath).isFile()) {
			MimeTypesList.getAppList().setBrowserExec(brPath);
			CurrentStorage.get().storeMimeTypesList();
		}

		if (!this.enableSoundCB.isSelected())
			Configuration.put("NOTIFY_SOUND", "DISABLED");
		else if (this.soundDefaultRB.isSelected())
			Configuration.put("NOTIFY_SOUND", "DEFAULT");
		else if (this.soundBeepRB.isSelected())
			Configuration.put("NOTIFY_SOUND", "BEEP");
		else if ((this.soundCustomRB.isSelected())
				&& (this.soundFile.getText().trim().length() > 0))
			Configuration.put("NOTIFY_SOUND", this.soundFile.getText().trim());

		if (antialiasChB.isSelected())
			Configuration.put("ANTIALIAS_TEXT", "yes");
		else
			Configuration.put("ANTIALIAS_TEXT", "no");
		
		Configuration.put("NORMAL_FONT", normalFontCB.getSelectedItem());
		Configuration.put("HEADER_FONT", headerFontCB.getSelectedItem());
		Configuration.put("MONO_FONT", monoFontCB.getSelectedItem());
		Configuration.put("BASE_FONT_SIZE", baseFontSize.getValue());

		
		Configuration.saveConfig();
		
	}

    /**
     * Enable custom lf.
     *
     * @param is the is
     */
    void enableCustomLF(boolean is) {
		this.classNameLabel.setEnabled(is);
		this.lfClassName.setEnabled(is);
	}

    /**
     * Enable custom sound.
     *
     * @param is the is
     */
    void enableCustomSound(boolean is) {
		this.soundFile.setEnabled(is);
		this.soundFileBrowseB.setEnabled(is);
		this.jLabel6.setEnabled(is);
	}

    /**
     * Enable sound.
     *
     * @param is the is
     */
    void enableSound(boolean is) {
		this.soundDefaultRB.setEnabled(is);
		this.soundBeepRB.setEnabled(is);
		this.soundCustomRB.setEnabled(is);
		enableCustomSound(is);

		this.soundFileBrowseB.setEnabled(is && soundCustomRB.isSelected());
		this.soundFile.setEnabled(is && soundCustomRB.isSelected());
		this.jLabel6.setEnabled(is && soundCustomRB.isSelected());

	}

    /**
     * Ok b action performed.
     *
     * @param e the e
     */
    void okB_actionPerformed(ActionEvent e) {
		apply();
		this.dispose();
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
     * Min taskbar rb action performed.
     *
     * @param e the e
     */
    void minTaskbarRB_actionPerformed(ActionEvent e) {

	}

    /**
     * Min hide rb action performed.
     *
     * @param e the e
     */
    void minHideRB_actionPerformed(ActionEvent e) {

	}

    /**
     * Close exit rb action performed.
     *
     * @param e the e
     */
    void closeExitRB_actionPerformed(ActionEvent e) {
		// this.askConfirmChB.setEnabled(true);
	}

    /**
     * Ask confirm ch b action performed.
     *
     * @param e the e
     */
    void askConfirmChB_actionPerformed(ActionEvent e) {

	}

    /**
     * Close hide rb action performed.
     *
     * @param e the e
     */
    void closeHideRB_actionPerformed(ActionEvent e) {
		// this.askConfirmChB.setEnabled(false);
	}

    /**
     * Lf system rb action performed.
     *
     * @param e the e
     */
    void lfSystemRB_actionPerformed(ActionEvent e) {
		this.enableCustomLF(false);
	}

    /**
     * Lf java rb action performed.
     *
     * @param e the e
     */
    void lfJavaRB_actionPerformed(ActionEvent e) {
		this.enableCustomLF(false);
	}

    /**
     * Lf custom rb action performed.
     *
     * @param e the e
     */
    void lfCustomRB_actionPerformed(ActionEvent e) {
		this.enableCustomLF(true);
	}

    /**
     * En systray ch b action performed.
     *
     * @param e the e
     */
    void enSystrayChB_actionPerformed(ActionEvent e) {

	}

    /**
     * En splash ch b action performed.
     *
     * @param e the e
     */
    void enSplashChB_actionPerformed(ActionEvent e) {

	}

    /**
     * En l 10 n ch b action performed.
     *
     * @param e the e
     */
    void enL10nChB_actionPerformed(ActionEvent e) {

	}

    /**
     * Browse b action performed.
     *
     * @param e the e
     */
    void browseB_actionPerformed(ActionEvent e) {
		// Fix until Sun's JVM supports more locales...
		UIManager.put("FileChooser.lookInLabelText", Local
				.getString("Look in:"));
		UIManager.put("FileChooser.upFolderToolTipText", Local
				.getString("Up One Level"));
		UIManager.put("FileChooser.newFolderToolTipText", Local
				.getString("Create New Folder"));
		UIManager.put("FileChooser.listViewButtonToolTipText", Local
				.getString("List"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
				.getString("Details"));
		UIManager.put("FileChooser.fileNameLabelText", Local
				.getString("File Name:"));
		UIManager.put("FileChooser.filesOfTypeLabelText", Local
				.getString("Files of Type:"));
		UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
		UIManager.put("FileChooser.openButtonToolTipText", Local
				.getString("Open selected file"));
		UIManager
				.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", Local
				.getString("Cancel"));

		JFileChooser chooser = new JFileChooser();
		chooser.setFileHidingEnabled(false);
		chooser.setDialogTitle(Local
				.getString("Select the web-browser executable"));
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setPreferredSize(new Dimension(550, 375));
		if (System.getProperty("os.name").startsWith("Win")) {
			chooser.setFileFilter(new AllFilesFilter(AllFilesFilter.EXE));
			chooser.setCurrentDirectory(new File("C:\\Program Files"));
		}
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			this.browserPath.setText(chooser.getSelectedFile().getPath());
	}

    /**
     * Enable sound cb action performed.
     *
     * @param e the e
     */
    void enableSoundCB_actionPerformed(ActionEvent e) {
		enableSound(enableSoundCB.isSelected());
	}

    /**
     * Sound file browse b action performed.
     *
     * @param e the e
     */
    void soundFileBrowseB_actionPerformed(ActionEvent e) {
		// Fix until Sun's JVM supports more locales...
		UIManager.put("FileChooser.lookInLabelText", Local
				.getString("Look in:"));
		UIManager.put("FileChooser.upFolderToolTipText", Local
				.getString("Up One Level"));
		UIManager.put("FileChooser.newFolderToolTipText", Local
				.getString("Create New Folder"));
		UIManager.put("FileChooser.listViewButtonToolTipText", Local
				.getString("List"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
				.getString("Details"));
		UIManager.put("FileChooser.fileNameLabelText", Local
				.getString("File Name:"));
		UIManager.put("FileChooser.filesOfTypeLabelText", Local
				.getString("Files of Type:"));
		UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
		UIManager.put("FileChooser.openButtonToolTipText", Local
				.getString("Open selected file"));
		UIManager
				.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", Local
				.getString("Cancel"));

		JFileChooser chooser = new JFileChooser();
		chooser.setFileHidingEnabled(false);
		chooser.setDialogTitle(Local.getString("Select the sound file"));
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setPreferredSize(new Dimension(550, 375));
		chooser.setFileFilter(new AllFilesFilter(AllFilesFilter.WAV));
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			this.soundFile.setText(chooser.getSelectedFile().getPath());
	}

    /**
     * Sound default rb action performed.
     *
     * @param e the e
     */
    void soundDefaultRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(false);
	}

    /**
     * Sound beep rb action performed.
     *
     * @param e the e
     */
    void soundBeepRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(false);
	}

    /**
     * Sound custom rb action performed.
     *
     * @param e the e
     */
    void soundCustomRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(true);
	}

    /**
     * Gets font names.
     *
     * @return the font names
     */
    Vector getFontNames() {
		GraphicsEnvironment gEnv = 
        	GraphicsEnvironment.getLocalGraphicsEnvironment();
        String envfonts[] = gEnv.getAvailableFontFamilyNames();
        Vector fonts = new Vector();
        fonts.add("serif");
        fonts.add("sans-serif");
        fonts.add("monospaced");
        for (int i = 0; i < envfonts.length; i++)
            fonts.add(envfonts[i]);
		return fonts;
	}
}