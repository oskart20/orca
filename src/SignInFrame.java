import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class SignInFrame extends JInternalFrame {
    //initiate sign in assets
    JButton SignInButton = new JButton("Sign in");
    JTextField NameTextField = new JTextField("Surname");
    JTextField ForenameTextField = new JTextField("Forename");
    JTextField GradeTextField = new JTextField("Grade");
    JPasswordField PasswordField = new JPasswordField("");
    JButton CancelButton = new JButton("Cancel");
    JPanel field;
    JPanel fieldButtons;

    //initiate data assets
    String password;
    String grade;
    String forename;
    String name;


    public SignInFrame(Main main){
        //initiate sign in pop up window
        SignInFrame signIn = this;
        signIn.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(350, 180);
        setLocation(30, 30);
        signIn.rootPane.setLayout(new BoxLayout(rootPane,BoxLayout.PAGE_AXIS));
        //add sign in assets to sign in window
        field = new JPanel(new BorderLayout());
        NameTextField.setSize(signIn.rootPane.getWidth(),14);
        NameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NameTextField.setText("");
            }
        });
        ForenameTextField.setSize(signIn.rootPane.getWidth(),14);
        ForenameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ForenameTextField.setText("");
            }
        });
        field.add(NameTextField, BorderLayout.CENTER);
        GradeTextField.setSize(signIn.rootPane.getWidth(),14);
        GradeTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GradeTextField.setText("");
            }
        });
        field.add(GradeTextField, BorderLayout.NORTH);
        PasswordField.setSize(signIn.rootPane.getWidth(),14);
        field.add(PasswordField, BorderLayout.SOUTH);
        this.rootPane.add(field);
        fieldButtons = new JPanel(new BorderLayout());
        fieldButtons.add(SignInButton, BorderLayout.WEST);
        SignInButton.addActionListener(e -> {
            name = NameTextField.getText();
            password = String.valueOf(PasswordField.getPassword());
            grade = GradeTextField.getText();
            if ("1234".contentEquals(password)) {
                signIn.setVisible(false);
                System.out.println("Password correct");
            } else {
                System.out.println("Password incorrect");
            }

        });
        fieldButtons.add(CancelButton, BorderLayout.EAST);
        CancelButton.addActionListener(e -> {
            GradeTextField.setText("Grade");
            PasswordField.setText("");
            signIn.setVisible(false);
        });
        this.rootPane.add(fieldButtons);
    }
}
