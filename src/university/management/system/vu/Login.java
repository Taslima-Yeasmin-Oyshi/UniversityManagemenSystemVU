package university.management.system.vu;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JButton loginButton, cancelButton;
    private JTextField tfUsername;
    private JPasswordField tfPassword;

    Login() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(new Color(192, 57, 43)); // Terracotta color
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(192, 57, 43)); // Terracotta color

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 12)); // Increased height of JTextField
        loginPanel.setBorder(BorderFactory.createEmptyBorder(100, 80, 80, 50));
        loginPanel.setBackground(new Color(192, 57, 43)); // Terracotta color

        JLabel lblUsername = new JLabel("Username");
        tfUsername = new JTextField();
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Bold text
        tfUsername.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Bold text
        tfUsername.setBorder(new LineBorder(Color.WHITE, 2)); // White border

        JLabel lblPassword = new JLabel("Password");
        tfPassword = new JPasswordField();
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Bold text
        tfPassword.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Bold text
        tfPassword.setBorder(new LineBorder(Color.WHITE, 2)); // White border

        loginPanel.add(lblUsername);
        loginPanel.add(tfUsername);
        loginPanel.add(lblPassword);
        loginPanel.add(tfPassword);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(192, 57, 43));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginButton.setBorderPainted(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(192, 57, 43)); // Terracotta color
        cancelButton.setForeground(Color.BLACK); // Black text color
        cancelButton.addActionListener(this);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setOpaque(true);
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setBackground(new Color(192, 57, 43)); // Terracotta color

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        ImageIcon varendraLogo = new ImageIcon(getClass().getResource("/icon/vu pic.png"));
        Image scaledVarendraLogo = varendraLogo.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledVarendraLogoIcon = new ImageIcon(scaledVarendraLogo);

        JLabel logoLabel = new JLabel(scaledVarendraLogoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setPreferredSize(new Dimension(150, 160));

        // Create a JPanel with white background for the logo
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.add(logoLabel);
        logoPanel.setBackground(Color.WHITE); // Set the background color to white

        // Add padding around the logo panel
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        add(mainPanel, BorderLayout.CENTER);

        setSize(500, 550); // Increased overall height
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Varendra University Login");
        setResizable(false);
        setVisible(true);
    }

    @Override
public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == loginButton) {
        String username = tfUsername.getText();
        String password = String.valueOf(tfPassword.getPassword());

        if (password.matches("^[a-zA-Z0-9]+$")) {
            String query = "SELECT * FROM login WHERE username=? AND password=?";

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystemvu", "root", "Oyshi812024@");
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, username);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    SwingUtilities.invokeLater(() -> new Project()); // Open the Project window on successful login
                } else {
                    showInvalidPasswordError();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            showInvalidPasswordError();
        }
    } else if (ae.getSource() == cancelButton) {
        setVisible(false);
    }
}


    private void showInvalidPasswordError() {
        ImageIcon errorIcon = new ImageIcon(getClass().getResource("/icon/error3.gif"));

        JLabel errorMessageLabel = new JLabel("Invalid password. Please use only letters and numbers.");
        errorMessageLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        errorMessageLabel.setForeground(new Color(255, 87, 34)); // Light Red text color
        errorMessageLabel.setBackground(Color.WHITE);
        errorMessageLabel.setOpaque(true);

        JOptionPane.showMessageDialog(null, errorMessageLabel, "Invalid Password", JOptionPane.ERROR_MESSAGE, errorIcon);
    }

    public static void main(String[] args) {
        new Login();
    }
}
