package main.java.memoranda.ui.classes;

import main.java.memoranda.database.BeltEntity;
import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.util.DbReadQueries;
import main.java.memoranda.ui.App;
import main.java.memoranda.util.Local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;

public class ClassesSchedNewClass extends JFrame {

    ClassesPanel topLevelReference;

    JPanel classCreate;
    JButton createButton;
    JComboBox times;
    JLabel lblTimes;
    JComboBox beltsCB;
    JComboBox levelsCB;
    JComboBox trainersCB;
    JLabel lblTrainers;
    JComboBox classSizeCB;
    JLabel lblClassSize;



    JTextField firstName;
    JTextField lastName;
    JTextField email;
    JPasswordField pass;
    JPasswordField pass2;
    JLabel accountSelection;
    ButtonGroup buttons;
    JRadioButton trainerButton;
    JRadioButton studentButton;
    JRadioButton adminButton;
    JLabel fillOutForm;
    JLabel lblBelt;
    JLabel lblLevels;

    /** Main constructor
     *
     * @param ref This parent reference is passed in so we can call refresh on it once the  UI is updated.
     * @param rel Used to set the position of the add user popup.
     */
    public ClassesSchedNewClass(ClassesPanel ref, Component rel) {
        this.topLevelReference = ref;//we store this to use in our action listener.

        initGuiComponents();

        initActionEvents();

        getContentPane().add(classCreate);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    /**
     * Initializes GUI components in one call.
     */
    public void initGuiComponents(){
        classCreate = new JPanel();
        createButton = new JButton("Create");
        //Trainers CB and Label
        trainersCB = new JComboBox(Local.getTrainerNames());
        lblTrainers = new JLabel("Select Trainer:");
        trainersCB.setBounds(100, 50, 200, 20);
        lblTrainers.setBounds(5, 50, 100, 25);
        //Times CB and Label
        times = new JComboBox(Local.getTimes());
        lblTimes = new JLabel("Select Start Time:");
        times.setBounds(150, 75, 70, 20);
        lblTimes.setBounds(25, 75, 125, 25);
        //Belts CB and Label
        beltsCB = new JComboBox(Local.getBeltNames());
        lblBelt = new JLabel("Select Belt Level:");
        beltsCB.setBounds(150, 100, 115, 20);
        lblBelt.setBounds(15, 100, 125, 20);
        //Set Max Class Size
        classSizeCB = new JComboBox(Local.getMaxClassSize());
        lblClassSize = new JLabel("Select Max Class Size:");
        classSizeCB.setBounds(155, 125, 50, 20);
        lblClassSize.setBounds(15, 125, 135, 20);

        trainerButton = new JRadioButton();
        studentButton = new JRadioButton();
        adminButton = new JRadioButton();
        fillOutForm = new JLabel("Please fill out the form with class information");
        accountSelection = new JLabel("Which type of account would you like to create?");
        buttons = new ButtonGroup(); // Ensures that only 1 option can be chosen
        buttons.add(trainerButton);
        buttons.add(studentButton);
        buttons.add(adminButton);

        levelsCB = new JComboBox(Local.getBeltNames());
        lblLevels = new JLabel();

        setTitle("Schedule New Class");
        setSize(300, 500);


        fillOutForm.setBounds(15, 5, 275, 50);
        fillOutForm.setFont(new Font("Bell MT", Font.PLAIN, 12));

        classCreate.setLayout(null);

        levelsCB.setPreferredSize(new Dimension(100, 25));
        levelsCB.setBounds(150, 400, 100, 20);
        lblLevels.setBounds(100, 400, 100, 20);
        lblLevels.setText(Local.getString("Level:"));
        lblLevels.setMinimumSize(new Dimension(60, 24));


        accountSelection.setBounds(0, 330, 300, 50);

        trainerButton.setText("Trainer");
        trainerButton.setBounds(10, 370, 100, 20);
        trainerButton.setBackground(new Color(230,230,230));
        studentButton.setText("Student");
        studentButton.setBounds(110, 370, 100, 20);
        studentButton.setBackground(new Color(230,230,230));
        adminButton.setText("Admin");
        adminButton.setBounds(210, 370, 100, 20);
        adminButton.setBackground(new Color(230,230,230));

        createButton.setBounds(100, 445, 80, 20);

        classCreate.add(createButton);
        classCreate.add(lblTrainers);
        classCreate.add(lblBelt);
        classCreate.add(classSizeCB);
        classCreate.add(lblClassSize);

        classCreate.add(accountSelection);
        classCreate.add(trainerButton);
        classCreate.add(studentButton);
        classCreate.add(adminButton);
        classCreate.add(fillOutForm);
        classCreate.add(beltsCB);
        classCreate.add(trainersCB);
        classCreate.add(times);
        classCreate.add(lblTimes);
        classCreate.setBackground(new Color(230, 230, 230));

    }

    /**
     * Initializes our action events in one call.
     */
    public void initActionEvents(){
        /**
         * Manages create button entry box
         */


        createButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    createAccount();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Allow an enter key press on the Create button to launch program
        createButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        createAccount();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Throws JOptionPane window on error.
     * @param error Message to display to the user
     */
    public void throwInputError (String error) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, error);
    }

    /**
     * Call to create account. Tests fields on Account Creation window for data.
     * @throws SQLException Throws exception is account does not exist.
     */
    public void createAccount () throws SQLException {
        if (firstName.getText().equals("First Name")) {
            throwInputError("You did not enter a first name");
        } else if (lastName.getText().equals("Last Name")) {
            throwInputError("You did not enter a Last name");
        } else if (email.getText().equals("E-mail")) {
            throwInputError("You did not enter an E-mail");
        } else if (!email.getText().contains("@") || !email.getText().contains(".")) {
            throwInputError("Invalid E-mail");
        } else if (pass.getPassword().length == 0) {
            throwInputError("You did not enter a password");
        } else if (pass2.getPassword().length == 0) {
            throwInputError("You did not verify your password");
        } else if (!Arrays.equals(pass.getPassword(), pass2.getPassword())) {
            throwInputError("Your passwords do not match");
        } else if (trainerButton.isSelected() || studentButton.isSelected() || adminButton.isSelected()){
            System.out.println("Attempting to create account with E-mail: "+ email.getText() );
            SqlConnection sql = SqlConnection.getInstance();
            DbReadQueries dbrq = sql.getDrq();
            try {
                RoleEntity role;
                if (adminButton.isSelected()) {
                    role = new RoleEntity(RoleEntity.UserRole.admin);
                } else if (trainerButton.isSelected()){
                    role = new RoleEntity(RoleEntity.UserRole.trainer);
                } else if (studentButton.isSelected()){
                    role = new RoleEntity(RoleEntity.UserRole.customer);
                } else {
                    System.out.println("[DEBUG] ERROR: Type of user not found");
                    return;
                }
                BeltEntity belt = new BeltEntity(BeltEntity.Rank.black1);
                String rank = beltsCB.getSelectedItem().toString();
                BeltEntity.Rank r = belt.getRank(rank);
                belt = new BeltEntity(r);
                // Add new user to database
                App.conn.getDcq().insertUser(email.getText(), firstName.getText(), lastName.getText(), pass.getText(), role, belt, belt);
                dispose();
                //this.topLevelReference.addUserToTable(email.getText(),rank,role.toString());
                showCreatedSuccessfullyPopup();
            } catch (SQLException ex) {
                throwInputError("An account already exists with that email.");
            }
        } else {
            throwInputError("Select the type of account to create");
        }

    }

    /**
     * Popup window that tells the user the account was created successfully
     */
    public void showCreatedSuccessfullyPopup() {
        Object[] option = {"OK"};
        int x = JOptionPane.showOptionDialog(null, "Account was created successfully!",
                "Account Creation", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
    }
}

