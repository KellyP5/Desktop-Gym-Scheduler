package main.java.memoranda.ui.usermanagment;

import main.java.memoranda.database.UserEntity;
import main.java.memoranda.ui.App;
import main.java.memoranda.ui.ExceptionDialog;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This controls the UserManagemnent panel on the LHS of our application.
 */
public class UserManagement extends JPanel {

    private String currentlySelectedEmail;      //these variables are updated when a row is selected on the JTable
    private String currentlySelectedUserRank;   //these variables are updated when a row is selected on the JTable
    private String currentlySelectedRole;       //these variables are updated when a row is selected on the JTable

    private ArrayList<UserEntity> userEntities;

    private JButton addUserButton;

    private JButton editUser;

    private JButton deleteUser;

    private JScrollPane scrollPane;

    private JTable userList;

    /**
     * Constructor for our UserManagement class
     */
    public UserManagement() {
        try {
            init();
        } catch(SQLException cep){
            cep.printStackTrace();
        } catch (Exception ex) {
           new ExceptionDialog(ex);
        }
    }

    /**
     * Master init method that runs all our other init methods
     * @throws Exception exception.
     */
    private void init() throws Exception {


        this.userEntities = App.conn.getDrq().getAllUsers();

        this.setLayout(new BorderLayout());

        initButtons();//initializes all the buttons
        initTable();
        setActions();//sets up our event listeners

    }

    /**
     * Initializes our buttons.
     */
    private void initButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        addUserButton = new JButton("Add User");
        editUser = new JButton("Edit User");
        deleteUser  = new JButton("Delete User");

        Dimension dem = new Dimension(100,100);
        addUserButton.setPreferredSize(dem);
        editUser.setPreferredSize(dem);
        deleteUser.setPreferredSize(dem);
        buttonPanel.add(this.addUserButton);
        buttonPanel.add(this.editUser);
        buttonPanel.add(this.deleteUser);

        this.add(buttonPanel,BorderLayout.NORTH);


    }

    /**
     * Initializes the table
     */
    private void initTable(){

        String[] columnNames = {"Email", "User Rank", "Role"};

        ArrayList<ArrayList<String>> d = new ArrayList<ArrayList<String>>();

        for(int i = 0;i< this.userEntities.size();i++){
            ArrayList<String> e = new ArrayList<>();
            e.add(this.userEntities.get(i).getEmail());
            e.add(this.userEntities.get(i).getBelt().toString());
            e.add(this.userEntities.get(i).getRole().toString());
            d.add(e);
        }

        String[][] data = new String[d.size()][];
        for(int i = 0;i<d.size();i++){
            ArrayList<String> current = d.get(i);

            String[] copy = new String[current.size()];
            for(int j = 0;j< current.size();j++){
                copy[j] = current.get(j);
            }
            data[i] = copy;
        }

        userList = new JTable(data,columnNames);

        //Forces selection to be just 1 row at a time
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Set up event listener for selecting a row
        userList.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            this.currentlySelectedEmail = (String) userList.getModel().getValueAt(userList.getSelectedRow(),0);
            this.currentlySelectedUserRank = (String) userList.getModel().getValueAt(userList.getSelectedRow(),1);
            this.currentlySelectedRole = (String) userList.getModel().getValueAt(userList.getSelectedRow(),2);
        });

        //allows you to select but prevents being able to edit
        userList.setDefaultEditor(Object.class,null);

        userList.setBounds(0,0,200,300);
        scrollPane = new JScrollPane(this.userList);
        this.add(this.scrollPane, BorderLayout.CENTER);

    }

    /**
     * Creates the action listensers.
     */
    private void setActions(){

        this.addUserButton.addActionListener(actionEvent -> {
            System.out.println("//TODO Add user button");
            new UserManagementAddUser(addUserButton);
        });

        this.editUser.addActionListener(actionEvent -> {
            System.out.println("//TODO Edit user button");
            new UserManagementEditUser(editUser);
        });

        this.deleteUser.addActionListener(actionEvent -> {
            System.out.println("//TODO Delete user button");
            //TODO
        });

    }

}