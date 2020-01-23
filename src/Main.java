import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Font.*;

public class Main {
    //initiate window assets
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    JButton button;
    //initiate table assets
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


    SignInFrame signInFrame;

    public void addComponentsToPane(Container pane) {
        //set BorderLayout as simple layout choice
        pane.setLayout(new BorderLayout());
        //add SignInFrame
        signInFrame = new SignInFrame(this);
        pane.add(signInFrame);
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
        menuItem = new JMenuItem("Sign in");
        menuItem.addActionListener(e -> signInFrame.setVisible(true));
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
        pane.add(scrollPane, BorderLayout.CENTER);
        //add submit button
        button = new JButton("Submit");
        button.addActionListener(e -> {

        });
        pane.add(button, BorderLayout.PAGE_END);
    }

    private void createAndShowGUI() {
        //create and set up the window.
        JFrame frame;

        frame = new JFrame("orca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set up the content pane.
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