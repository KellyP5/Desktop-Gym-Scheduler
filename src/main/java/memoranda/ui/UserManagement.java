package main.java.memoranda.ui;


import main.java.memoranda.database.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserManagement extends JPanel {

    ArrayList<UserEntity> userEntities;

    JButton addUserButton;

    JButton editUser;

    JButton deleteUser;

    JScrollPane scrollPane;

    JTable userList;

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
     * Jb init.
     *
     * @throws Exception the exception
     */
    private void init() throws Exception {


        this.userEntities = App.conn.getDrq().getAllUsers();

        this.setLayout(new BorderLayout());

        initButtons();//initializes all the buttons
        initTable();
        setActions();//sets up our event listeners

    }

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

    private void initTable(){

        String[] columnNames = {"Email", "User Rank", "Role"};

        ArrayList<ArrayList<String>> d = new ArrayList<ArrayList<String>>();
        ArrayList<String> e1 = new ArrayList<>();


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

        //allows you to select but prevents being able to edit
        userList.setDefaultEditor(Object.class,null);

        userList.setBounds(0,0,200,300);
        scrollPane = new JScrollPane(this.userList);
        this.add(this.scrollPane, BorderLayout.CENTER);

    }

    private void setActions(){

        this.addUserButton.addActionListener(actionEvent -> {
            System.out.println("//TODO Add user button");
            //TODO
        });

        this.editUser.addActionListener(actionEvent -> {
            System.out.println("//TODO Edit user button");
            //TODO
        });

        this.deleteUser.addActionListener(actionEvent -> {
            System.out.println("//TODO Delete user button");
            //TODO
        });

    }





}