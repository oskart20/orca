import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Font.*;

public class Main {

    //declare welcome panel assets
    JPanel welcome;
    JButton teachers;
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

    //declare sign in(pupils) assets
    JPanel signInPanelPupil;
    JButton signInButtonPupil;
    JTextField nameTextFieldPupil;
    JTextField forenameTextFieldPupil;
    JTextField gradeTextFieldPupil;
    JPasswordField passwordFieldPupil;
    JButton cancelButtonPupil;
    JPanel fieldPupil;
    JPanel fieldButtonsPupil;

    //declare teachers data assets
    String passwordPupil;
    String gradePupil;
    String forenamePupil;
    String namePupil;

    //declare sign in(teachers) assets
    JPanel signInPanelTeachers;
    JButton signInButtonTeachers;
    JTextField nameTextFieldTeachers;
    JTextField forenameTextFieldTeachers;
    JPasswordField passwordFieldTeachers;
    JButton cancelButtonTeachers;
    JPanel fieldTeachers;
    JPanel fieldButtonsTeachers;

    //declare teacher data assets
    String passwordTeachers;
    String forenameTeachers;
    String nameTeachers;

    public void addComponentsToPane(Container pane){
        pane.setLayout(new CardLayout());
        pane.add(welcomeScreen(pane), "WELCOME");
        pane.add(signInPanelPupil(pane), "SIGNINPUPIL");
        pane.add(signInPanelTeachers(pane), "SIGNINTEACHERS");
        pane.add(testPanel(pane), "TEST");
    }

    public JPanel welcomeScreen(Container pane){
        welcome = new JPanel();
        welcome.setLayout(new BorderLayout());
        teachers = new JButton("Teachers");
        teachers.addActionListener(e -> {
            CardLayout cl = (CardLayout) pane.getLayout();
            cl.show(pane, "SIGNINTEACHERS");
        });
        teachers.setPreferredSize(new Dimension(200, 100));
        welcome.add(teachers, BorderLayout.LINE_START);
        pupil = new JButton("Pupil");
        pupil.addActionListener(e -> {
            CardLayout cl = (CardLayout) pane.getLayout();
            cl.show(pane, "SIGNINPUPIL");
        });
        pupil.setPreferredSize(new Dimension(200, 100));
        welcome.add(pupil, BorderLayout.LINE_END);
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
        signInPanelPupil.setLayout(new BoxLayout(signInPanelPupil, BoxLayout.PAGE_AXIS));
        //add sign in assets to sign in window
        fieldPupil = new JPanel(new BorderLayout());
        nameTextFieldPupil = new JTextField("Surname");
        nameTextFieldPupil.setSize(signInPanelPupil.getWidth(),14);
        nameTextFieldPupil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameTextFieldPupil.setText("");
            }
        });
        forenameTextFieldPupil = new JTextField("Forename");
        forenameTextFieldPupil.setSize(signInPanelPupil.getWidth(),14);
        forenameTextFieldPupil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                forenameTextFieldPupil.setText("");
            }
        });
        fieldPupil.add(nameTextFieldPupil, BorderLayout.CENTER);
        gradeTextFieldPupil = new JTextField("Grade");
        gradeTextFieldPupil.setSize(signInPanelPupil.getWidth(),14);
        gradeTextFieldPupil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gradeTextFieldPupil.setText("");
            }
        });
        fieldPupil.add(gradeTextFieldPupil, BorderLayout.NORTH);
        passwordFieldPupil = new JPasswordField("");
        passwordFieldPupil.setSize(signInPanelPupil.getWidth(),14);
        fieldPupil.add(passwordFieldPupil, BorderLayout.SOUTH);
        signInPanelPupil.add(fieldPupil);
        fieldButtonsPupil = new JPanel(new BorderLayout());
        signInButtonPupil = new JButton("Sign in");
        fieldButtonsPupil.add(signInButtonPupil, BorderLayout.WEST);
        signInButtonPupil.addActionListener(e -> {
            forenamePupil = forenameTextFieldPupil.getText();
            namePupil = nameTextFieldPupil.getText();
            passwordPupil = String.valueOf(passwordFieldPupil.getPassword());
            gradePupil = gradeTextFieldPupil.getText();
            if ("1234".contentEquals(passwordPupil)) {
                // todo: sign in as pupil with the details given
                CardLayout cl = (CardLayout) pane.getLayout();
                cl.show(pane, "TEST");
                System.out.println("Password correct");
            } else {
                System.out.println("Password incorrect");
            }

        });
        cancelButtonPupil = new JButton("Cancel");
        cancelButtonPupil.addActionListener(e -> {
            gradeTextFieldPupil.setText("Grade");
            passwordFieldPupil.setText("");
            CardLayout cl = (CardLayout) pane.getLayout();
            cl.show(pane, "WELCOME");
        });
        fieldButtonsPupil.add(cancelButtonPupil, BorderLayout.EAST);
        signInPanelPupil.add(fieldButtonsPupil);
        return signInPanelPupil;
    }

    public JPanel signInPanelTeachers(Container pane){
        signInPanelTeachers = new JPanel();
        signInPanelTeachers.setLayout(new BoxLayout(signInPanelTeachers, BoxLayout.PAGE_AXIS));
        //add sign in assets to sign in window
        fieldTeachers = new JPanel(new BorderLayout());
        nameTextFieldTeachers = new JTextField("Surname");
        nameTextFieldTeachers.setSize(signInPanelTeachers.getWidth(),14);
        nameTextFieldTeachers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameTextFieldTeachers.setText("");
            }
        });
        forenameTextFieldTeachers = new JTextField("Forename");
        forenameTextFieldTeachers.setSize(signInPanelTeachers.getWidth(),14);
        forenameTextFieldTeachers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                forenameTextFieldTeachers.setText("");
            }
        });
        fieldTeachers.add(nameTextFieldTeachers, BorderLayout.CENTER);
        passwordFieldTeachers = new JPasswordField("");
        passwordFieldTeachers.setSize(signInPanelTeachers.getWidth(),14);
        fieldTeachers.add(passwordFieldTeachers, BorderLayout.SOUTH);
        signInPanelTeachers.add(fieldTeachers);
        fieldButtonsTeachers = new JPanel(new BorderLayout());
        signInButtonTeachers = new JButton("Sign in");
        fieldButtonsTeachers.add(signInButtonTeachers, BorderLayout.WEST);
        signInButtonTeachers.addActionListener(e -> {
            forenameTeachers = forenameTextFieldTeachers.getText();
            nameTeachers = nameTextFieldTeachers.getText();
            passwordTeachers = String.valueOf(passwordFieldTeachers.getPassword());
            if ("1234".contentEquals(passwordTeachers)) {
                // todo: sign in as teacher with the details given
                CardLayout cl = (CardLayout) pane.getLayout();
                cl.show(pane, "TEST");
                System.out.println("Password correct");
            } else {
                System.out.println("Password incorrect");
            }

        });
        cancelButtonTeachers = new JButton("Cancel");
        cancelButtonTeachers.addActionListener(e -> {
            passwordFieldTeachers.setText("");
            CardLayout cl = (CardLayout) pane.getLayout();
            cl.show(pane, "WELCOME");
        });
        fieldButtonsTeachers.add(cancelButtonTeachers, BorderLayout.EAST);
        signInPanelTeachers.add(fieldButtonsTeachers);
        return signInPanelTeachers;
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