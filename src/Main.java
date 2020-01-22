import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Font.*;

public class Main {

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    JButton button;

    JTable table;
    JScrollPane scrollPane;
    String[] columnNames = {
            "English",
            "German"
    };
    Object [][] data = new Object[][]{
            {"umpire", ""},
            {"saviour", ""},
            {"ban", ""},
            {"quarter", ""},
            {"exhibition", ""}
    };


    SignInFrame signInFrame;

    public void addComponentsToPane(Container pane) {
        pane.setLayout(new BorderLayout());
        signInFrame = new SignInFrame(this);
        pane.add(signInFrame);

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuItem = new JMenuItem("temp1");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("temp2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Sign in");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signInFrame.setVisible(true);
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        table = new JTable(new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        });
        table.setFont(new Font(DIALOG , PLAIN, 13));

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        pane.add(scrollPane, BorderLayout.CENTER);
        button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        pane.add(button, BorderLayout.PAGE_END);
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame;

        frame = new JFrame("orca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        frame.setJMenuBar(menuBar);

        //Size and display the window.
        Insets insets = frame.getInsets();
        frame.setSize(800 + insets.left + insets.right, 550 + insets.top + insets.bottom);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        Main orca = new Main();
        orca.createAndShowGUI();
    }
}