import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.ContentHandler;
import java.time.LocalDate;
import java.util.UUID;


import static java.awt.Font.*;

public class Main {

    CSVReader reader = new CSVReader();
    ContentProvider provider = new ContentProvider();

    // declare layout assets
    GridBagConstraints c;
    CardLayout cl;

    //declare welcome panel assets
    JPanel welcome;
    JButton teacher;
    JButton pupil;

    //declare test panel assets
    JPanel test;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    JButton button;
    //declare table assets
    JTable table;
    JScrollPane scrollPane;
    //table columns
    String[] columnNames = {
            "English",
            "German"
    };
    //temp table content
    Object [][] data = new Object[][]{
            {"umpire", ""},
            {"saviour", ""},
            {"ban", ""},
            {"quarter", ""},
            {"exhibition", ""}
    };

    // fonts
    Font f;

    //declare sign in(pupil) assets
    JPanel signInPanelPupil;
    JButton signInButtonPupil;
    JTextField nameTextFieldPupil;
    JTextField forenameTextFieldPupil;
    JTextField gradeTextFieldPupil;
    JPasswordField passwordFieldPupil;
    JButton cancelButtonPupil;

    //declare pupil data assets
    String passwordPupil;
    String gradePupil;
    String forenamePupil;
    String namePupil;

    //declare sign in(teacher) assets
    JPanel signInPanelTeacher;
    JButton signInButtonTeacher;
    JTextField nameTextFieldTeacher;
    JTextField forenameTextFieldTeacher;
    JPasswordField passwordFieldTeacher;
    JButton cancelButtonTeacher;

    String teacherID;

    //declare teacher data assets
    String passwordTeacher;
    String forenameTeacher;
    String nameTeacher;

    //declare teacherOverview assets
    JPanel teacherOverview;
    JSpinner spinner;
    JTable viewTable;
    JScrollPane viewScrollPane;
    JTextField importTextField;
    JTextField gradeTextFieldTeacher;
    JButton importButton;
    JButton submitButton;
    String[][] voidData = new String[][]{{"", ""}};
    String[][] newData;
    String intendedGrade;

    JPanel correctedTest;


    public void addComponentsToPane(Container pane){
        pane.setLayout(new CardLayout());
        pane.add(welcomeScreen(pane), "WELCOME");
        pane.add(signInPanelPupil(pane), "SIGNINPUPIL");
        pane.add(signInPanelTeacher(pane), "SIGNINTEACHER");
        pane.add(testPanel(pane), "TEST");
        pane.add(teacherOverview(pane), "TEACHEROVERVIEW");
    }

    public JPanel welcomeScreen(Container pane){
        welcome = new JPanel();
        welcome.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        teacher = new JButton("Teacher");
        teacher.addActionListener(e -> {
            cl = (CardLayout) pane.getLayout();
            cl.show(pane, "SIGNINTEACHER");
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        welcome.add(teacher, c);
        pupil = new JButton("Pupil");
        pupil.addActionListener(e -> {
            cl = (CardLayout) pane.getLayout();
            cl.show(pane, "SIGNINPUPIL");
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        welcome.add(pupil, c);
        return welcome;
    }

    public JPanel testPanel(Container pane) {
        //set BorderLayout as simple layout choice
        test = new JPanel();
        test.setLayout(new BorderLayout());
        //add menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        //add multiple menu items
        menuItem = new JMenuItem("temp1");
        menuItem.addActionListener(e -> {

        });
        menu.add(menuItem);
        menuItem = new JMenuItem("temp2");
        menuItem.addActionListener(e -> {

        });
        menu.add(menuItem);
        //add menu to windows menu bar
        menuBar.add(menu);
        //configure and add table to the main window
        table = new JTable(new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        });
        table.setFont(new Font(DIALOG , PLAIN, 13));
        // make table scrollable
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        test.add(scrollPane, BorderLayout.CENTER);
        //add submit button
        button = new JButton("Submit");
        button.addActionListener(e -> {
            //todo: switch card to end of test

        });
        test.add(button, BorderLayout.PAGE_END);
        return test;
    }

    public JPanel signInPanelPupil(Container pane){
        signInPanelPupil = new JPanel();
        signInPanelPupil.setLayout(new GridBagLayout());
        //add sign in assets to sign in window
        f = new Font("Courier", Font.PLAIN, 30);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.25;
        c.ipady = 40;
        forenameTextFieldPupil = new JTextField("Forename");
        forenameTextFieldPupil.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(forenameTextFieldPupil.getText().equals("Forename")){
                    forenameTextFieldPupil.setText("");
                }
            }
        });
        forenameTextFieldPupil.setFont(f);
        signInPanelPupil.add(forenameTextFieldPupil, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.25;
        c.ipady = 40;
        nameTextFieldPupil = new JTextField("Surname");
        nameTextFieldPupil.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(nameTextFieldPupil.getText().equals("Surname")){
                    nameTextFieldPupil.setText("");
                }
            }
        });
        nameTextFieldPupil.setFont(f);
        signInPanelPupil.add(nameTextFieldPupil, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.25;
        c.ipady = 40;
        gradeTextFieldPupil = new JTextField("Grade");
        gradeTextFieldPupil.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(gradeTextFieldPupil.getText().equals("Grade")){
                    gradeTextFieldPupil.setText("");
                }
            }
        });
        gradeTextFieldPupil.setFont(f);
        signInPanelPupil.add(gradeTextFieldPupil, c);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        c.weighty = 0.25;
        c.ipady = 40;
        passwordFieldPupil = new JPasswordField("");
        gradeTextFieldPupil.setFont(f);
        signInPanelPupil.add(passwordFieldPupil, c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.25;
        c.weighty = 0.3;
        c.ipady = 20;
        signInButtonPupil = new JButton("Sign in");
        signInButtonPupil.addActionListener(e -> {
            forenamePupil = forenameTextFieldPupil.getText();
            namePupil = nameTextFieldPupil.getText();
            passwordPupil = String.valueOf(passwordFieldPupil.getPassword());
            gradePupil = gradeTextFieldPupil.getText();
            /*if (provider.singInUser(passwordPupil, namePupil, forenamePupil, "Pupil")) {
                cl = (CardLayout) pane.getLayout();
                cl.show(pane, "TEACHEROVERVIEW");
                System.out.println("Password correct, used content provider");
            } else {
                System.out.println("Password incorrect");
            }*/
            if ("1234".contentEquals(passwordPupil)) {
                cl = (CardLayout) pane.getLayout();
                cl.show(pane, "TEST");
                System.out.println("Password correct");
            } else {
                System.out.println("Password incorrect");
            }

        });
        signInPanelPupil.add(signInButtonPupil, c);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.25;
        c.weighty = 0.3;
        c.ipady = 20;
        cancelButtonPupil = new JButton("Cancel");
        cancelButtonPupil.addActionListener(e -> {
            forenameTextFieldPupil.setText("Forename");
            nameTextFieldPupil.setText("Surname");
            gradeTextFieldPupil.setText("Grade");
            passwordFieldPupil.setText("");
            cl = (CardLayout) pane.getLayout();
            cl.show(pane, "WELCOME");
        });
        signInPanelPupil.add(cancelButtonPupil, c);
        return signInPanelPupil;
    }

    public JPanel signInPanelTeacher(Container pane){
        signInPanelTeacher = new JPanel();
        signInPanelTeacher.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        f = new Font("Courier", Font.PLAIN, 30);
        //add sign in assets to sign in window
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.25;
        c.ipady = 40;
        forenameTextFieldTeacher = new JTextField("Forename");
        forenameTextFieldTeacher.setSize(signInPanelTeacher.getWidth(),14);
        forenameTextFieldTeacher.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(forenameTextFieldTeacher.getText().equals("Forename")){
                    forenameTextFieldTeacher.setText("");
                }
            }
        });
        forenameTextFieldTeacher.setFont(f);
        signInPanelTeacher.add(forenameTextFieldTeacher, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.25;
        c.ipady = 40;
        nameTextFieldTeacher = new JTextField("Surname");
        nameTextFieldTeacher.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(nameTextFieldTeacher.getText().equals("Surname")){
                    nameTextFieldTeacher.setText("");
                }
            }
        });
        nameTextFieldTeacher.setFont(f);
        signInPanelTeacher.add(nameTextFieldTeacher, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.25;
        c.ipady = 40;
        passwordFieldTeacher = new JPasswordField("");
        passwordFieldTeacher.setFont(f);
        signInPanelTeacher.add(passwordFieldTeacher, c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.25;
        c.weighty = 0.3;
        c.ipady = 20;
        signInButtonTeacher = new JButton("Sign in");
        signInButtonTeacher.addActionListener(e -> {
            forenameTeacher = forenameTextFieldTeacher.getText();
            nameTeacher = nameTextFieldTeacher.getText();
            passwordTeacher = String.valueOf(passwordFieldTeacher.getPassword());
            /*if (provider.singInUser(passwordTeacher, nameTeacher, forenameTeacher, "Teacher")) {
                cl = (CardLayout) pane.getLayout();
                cl.show(pane, "TEACHEROVERVIEW");
                System.out.println("Password correct, used content provider");
            } else {
                System.out.println("Password incorrect");
            }*/
            if ("1234".contentEquals(passwordTeacher)) {
                cl = (CardLayout) pane.getLayout();
                cl.show(pane, "TEACHEROVERVIEW");
                System.out.println("Password correct");
            } else {
                System.out.println("Password incorrect");
            }

        });
        signInPanelTeacher.add(signInButtonTeacher, c);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.25;
        c.weighty = 0.3;
        c.ipady = 20;
        cancelButtonTeacher = new JButton("Cancel");
        cancelButtonTeacher.addActionListener(e -> {
            forenameTextFieldTeacher.setText("Forename");
            nameTextFieldTeacher.setText("Surname");
            passwordFieldTeacher.setText("");
            cl = (CardLayout) pane.getLayout();
            cl.show(pane, "WELCOME");
        });
        signInPanelTeacher.add(cancelButtonTeacher, c);
        return signInPanelTeacher;
    }

    public JPanel teacherOverview(Container pane){
        teacherOverview = new JPanel();
        teacherOverview.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        f = new Font("Courier", Font.PLAIN, 15);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 1;
        c.ipady = 40;
        importTextField = new JTextField("Import .csv file");
        importTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(importTextField.getText().equals("Import .csv file")){
                    importTextField.setText("");
                }
            }
        });
        importTextField.setFont(f);
        teacherOverview.add(importTextField, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 1;
        c.ipady = 40;
        importButton = new JButton("Import");
        importButton.addActionListener(e -> {
            DefaultTableModel tableModel = (DefaultTableModel) viewTable.getModel();
            newData = reader.readCSV(importTextField.getText());
            tableModel.setDataVector(newData, columnNames);
            spinner.setValue(newData.length);
        });
        teacherOverview.add(importButton, c);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 1;
        c.ipady = 40;
        gradeTextFieldTeacher = new JTextField("Grade");
        gradeTextFieldTeacher.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(gradeTextFieldTeacher.getText().equals("Grade")){
                    gradeTextFieldTeacher.setText("");

                }
            }
        });
        gradeTextFieldTeacher.setFont(f);
        teacherOverview.add(gradeTextFieldTeacher, c);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        c.weighty = 1;
        c.ipady = 40;
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            // insert current table data into database under unique id
            // id is made up out of teacher surname + grade the test is intended for
            LocalDate date = LocalDate.now();
            intendedGrade = gradeTextFieldTeacher.getText();
            String testID = (nameTeacher + "_" + intendedGrade).toLowerCase() + date; //TODO: ADD DATE
            System.out.println(testID);
        });
        teacherOverview.add(submitButton, c);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        c.weighty = 1;
        c.ipady = 40;
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinner.addChangeListener(e -> {
            DefaultTableModel tableModel = (DefaultTableModel) viewTable.getModel();
            tableModel.setRowCount((Integer) spinner.getValue());
        });
        teacherOverview.add(spinner, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        c.weightx = 5;
        c.fill = GridBagConstraints.BOTH;
        viewTable = new JTable(new DefaultTableModel(voidData, columnNames));
        viewTable.setFont(new Font(DIALOG , PLAIN, 13));
        viewScrollPane = new JScrollPane(viewTable);
        viewTable.setFillsViewportHeight(true);
        viewTable.setGridColor(Color.BLUE);
        teacherOverview.add(viewScrollPane, c);
        return teacherOverview;
    }

    public JPanel correctedTest(Container pane){
        correctedTest = new JPanel();
        correctedTest.setLayout(new GridBagLayout());

        return correctedTest;
    }

    private void createAndShowGUI() {
        //create and set up the window.
        JFrame frame;

        frame = new JFrame("orca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set up the content
        addComponentsToPane(frame.getContentPane());

        //set up menu bar
        frame.setJMenuBar(menuBar);

        //size and display the window.
        Insets insets = frame.getInsets();
        frame.setSize(800 + insets.left + insets.right, 550 + insets.top + insets.bottom);
        frame.setVisible(true);
    }
    
    public static void main(String[] args){
        Main orca = new Main();
        orca.createAndShowGUI();
    }
}