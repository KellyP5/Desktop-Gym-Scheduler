package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

import main.java.memoranda.util.AppList;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.MimeType;
import main.java.memoranda.util.MimeTypesList;
import main.java.memoranda.util.Util;

/**
 * The type Resource type panel.
 */
/*$Id: ResourceTypePanel.java,v 1.8 2004/10/18 19:09:10 ivanrise Exp $*/
public class ResourceTypePanel extends JPanel {
    /**
     * The Border 1.
     */
    Border border1;
    /**
     * The Titled border 1.
     */
    TitledBorder titledBorder1;

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
     * The J panel 1.
     */
    JPanel jPanel1 = new JPanel();
    /**
     * The New type b.
     */
    JButton newTypeB = new JButton();
    /**
     * The J scroll pane 1.
     */
    JScrollPane jScrollPane1 = new JScrollPane();
    /**
     * The Border layout 2.
     */
    BorderLayout borderLayout2 = new BorderLayout();
    /**
     * The Area panel.
     */
    JPanel areaPanel = new JPanel();
    /**
     * The J panel 2.
     */
    JPanel jPanel2 = new JPanel();
    /**
     * The Edit b.
     */
    JButton editB = new JButton();
    /**
     * The Delete b.
     */
    JButton deleteB = new JButton();
    /**
     * The Border layout 1.
     */
    BorderLayout borderLayout1 = new BorderLayout();
    /**
     * The Types list.
     */
    public JList typesList = new JList();
    /**
     * The Border layout 3.
     */
    BorderLayout borderLayout3 = new BorderLayout();
    /**
     * The Border 3.
     */
    Border border3;

    /**
     * Instantiates a new Resource type panel.
     */
    public ResourceTypePanel() {
        super();
        try {
            jbInit();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
        border1 = BorderFactory.createLineBorder(SystemColor.controlText, 2);
        titledBorder1 = new TitledBorder(BorderFactory.createEmptyBorder(),Local.getString("Registered types"));
        border2 = BorderFactory.createLineBorder(Color.gray, 1);
        titledBorder2 = new TitledBorder(BorderFactory.createLineBorder(Color.gray, 1), Local.getString("Details"));
        border3 = BorderFactory.createEmptyBorder(0, 10, 0, 0);


        jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(borderLayout1);
    newTypeB.setMaximumSize(new Dimension(110, 25));
    newTypeB.setPreferredSize(new Dimension(110, 25));
    newTypeB.setText(Local.getString("New"));
    newTypeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newTypeB_actionPerformed(e);
            }
        });
    areaPanel.setLayout(borderLayout2);
    jPanel2.setMaximumSize(new Dimension(120, 32767));
    jPanel2.setMinimumSize(new Dimension(120, 36));
    jPanel2.setPreferredSize(new Dimension(120, 36));
    jPanel2.setBorder(border3);
    editB.setText(Local.getString("Edit"));
    editB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editB_actionPerformed(e);
            }
        });
    editB.setEnabled(false);
    editB.setMaximumSize(new Dimension(110, 25));
    editB.setPreferredSize(new Dimension(110, 25));
    deleteB.setEnabled(false);
    deleteB.setMaximumSize(new Dimension(110, 25));
    deleteB.setPreferredSize(new Dimension(110, 25));
    deleteB.setText(Local.getString("Delete"));
    deleteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteB_actionPerformed(e);
            }
        });
    typesList.setCellRenderer(new TypesListRenderer());
    typesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    typesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        typesList_valueChanged(e);
      }
    });
        initTypesList();

        //typesList.setCellRenderer(new TypesListRenderer());
    this.setLayout(borderLayout3);
    this.add(areaPanel, BorderLayout.CENTER);
    areaPanel.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(typesList, null);
    jPanel1.add(jPanel2, BorderLayout.EAST);
    jPanel2.add(newTypeB, null);
    jPanel2.add(editB, null);
    jPanel2.add(deleteB, null);
    typesList.setListData(MimeTypesList.getAllMimeTypes());
    //typesList.updateUI();

    }

    /**
     * Init types list.
     */
    public void initTypesList() {
        /*Vector v = new Vector();
        icons = new Vector();
        Vector t = MimeTypesList.getAllMimeTypes();
        for (int i = 0; i < t.size(); i++) {
            MimeType mt = (MimeType)t.get(i);
            v.add(mt.getLabel());
            icons.add(mt.getIcon());
        }*/
        typesList.setListData(MimeTypesList.getAllMimeTypes());
        typesList.updateUI();
    }


    /**
     * New type b action performed.
     *
     * @param e the e
     */
    void newTypeB_actionPerformed(ActionEvent e) {
        EditTypeDialog dlg = new EditTypeDialog(App.getFrame(), Local.getString("New resource type"));
        Dimension dlgSize = new Dimension(420, 420);
        dlg.setSize(dlgSize);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.extField.setText(ext);
        dlg.descField.setText(ext);
        dlg.appPanel.argumentsField.setText("$1");
        dlg.iconLabel.setIcon(
            new ImageIcon(
                main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/mimetypes/default.png")));
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        String typeId = Util.generateId();
        MimeType mt = MimeTypesList.addMimeType(typeId);
        String[] exts = dlg.extField.getText().trim().split(" ");
        for (int i = 0; i < exts.length; i++)
            mt.addExtension(exts[i]);
        mt.setLabel(dlg.descField.getText());
        AppList appList = MimeTypesList.getAppList();
        if (dlg.appPanel.applicationField.getText().length() > 0) {
            File f = new File(dlg.appPanel.applicationField.getText());
            String appId = Util.generateId();
            appList.addApp(
                appId,
                f.getParent().replace('\\', '/'),
                f.getName().replace('\\', '/'),
                dlg.appPanel.argumentsField.getText());
            mt.setApp(appId);
        }
        if (dlg.iconPath.length() > 0)
            mt.setIconPath(dlg.iconPath);
        CurrentStorage.get().storeMimeTypesList();
        this.initTypesList();
        typesList.setSelectedValue(mt, true);
    }

    /**
     * Delete b action performed.
     *
     * @param e the e
     */
    void deleteB_actionPerformed(ActionEvent e) {
        MimeType mt = (MimeType) typesList.getSelectedValue();
        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                Local.getString("Delete resource type")
                    + "\n'"
                    + mt.getLabel()
                    + "'\n"
                    + Local.getString("Are you sure?"),
                Local.getString("Delete resource type"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION)
            return;
        MimeTypesList.removeMimeType(mt.getMimeTypeId());
        CurrentStorage.get().storeMimeTypesList();
        this.initTypesList();
    }

    /**
     * Edit b action performed.
     *
     * @param e the e
     */
    void editB_actionPerformed(ActionEvent e) {
        EditTypeDialog dlg = new EditTypeDialog(App.getFrame(), Local.getString("Edit resource type"));
        Dimension dlgSize = new Dimension(420, 450);
        dlg.setSize(dlgSize);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        MimeType mt = (MimeType) typesList.getSelectedValue();
        String[] exts = mt.getExtensions();
        String extss = "";
        for (int i = 0; i < exts.length; i++)
            extss += exts[i] + " ";
        dlg.extField.setText(extss);
        dlg.descField.setText(mt.getLabel());
        dlg.iconLabel.setIcon(mt.getIcon());
        AppList appList = MimeTypesList.getAppList();
        dlg.appPanel.applicationField.setText(
            appList.getFindPath(mt.getAppId()) + "/" + appList.getExec(mt.getAppId()));
        dlg.appPanel.argumentsField.setText(appList.getCommandLinePattern(mt.getAppId()));
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        String typeId = mt.getMimeTypeId();
        MimeTypesList.removeMimeType(typeId);
        mt = MimeTypesList.addMimeType(typeId);
        exts = dlg.extField.getText().trim().split(" ");
        for (int i = 0; i < exts.length; i++)
            mt.addExtension(exts[i]);
        mt.setLabel(dlg.descField.getText());
        if (dlg.appPanel.applicationField.getText().length() > 0) {
            File f = new File(dlg.appPanel.applicationField.getText());
            String appId = Util.generateId();
            appList.addApp(
                appId,
                f.getParent().replace('\\', '/'),
                f.getName().replace('\\', '/'),
                dlg.appPanel.argumentsField.getText());
            mt.setApp(appId);
        }
        if (dlg.iconPath.length() > 0)
            mt.setIconPath(dlg.iconPath);
        CurrentStorage.get().storeMimeTypesList();
        this.initTypesList();
        typesList.setSelectedValue(mt, true);

    }


    /**
     * The type Types list renderer.
     */
    class TypesListRenderer extends JLabel implements ListCellRenderer {

        /**
         * Instantiates a new Types list renderer.
         */
        public TypesListRenderer() {
            super();
        }

        public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

            MimeType mt = (MimeType) value;
            String[] exts = mt.getExtensions();
            String extstr = "";
            for (int j = 0; j < exts.length; j++) {
                extstr += "*." + exts[j];
                if (j != exts.length - 1)
                    extstr += ", ";
            }

            setOpaque(true);
            setText(mt.getLabel() + " (" + extstr + ")");
            setIcon(mt.getIcon());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }

    /**
     * Types list value changed.
     *
     * @param e the e
     */
    void typesList_valueChanged(ListSelectionEvent e) {
      boolean en = typesList.getSelectedValue() != null;
      this.editB.setEnabled(en);
      this.deleteB.setEnabled(en);
    }



}