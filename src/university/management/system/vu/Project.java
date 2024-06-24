
package university.management.system.vu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.BasicMenuItemUI;

public class Project extends JFrame implements ActionListener {

    public Project() {
        initializeUI();
    }

    private void initializeUI() {
        setSize(1540, 850);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/uni2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        JMenuBar mb = new JMenuBar();
        mb.setLayout(new GridLayout(0, 1));  // Set layout to a single column
        mb.setBackground(new Color(192, 57, 43)); // Terracotta color

        // New Information
        createMenu(mb, "New Information", new Color(192, 57, 43), "New Faculty Information", "New Student Information");

        // Details
        createMenu(mb, "View Details", new Color(192, 57, 43), "View Faculty Details", "View Student Details");

        // Leave
        createMenu(mb, "Apply Leave", new Color(192, 57, 43), "Faculty Leave", "Student Leave");

        // Leave Details
        createMenu(mb, "Leave Details", new Color(192, 57, 43), "Faculty Leave Details", "Student Leave Details");

        // Exams
        createMenu(mb, "Examination", new Color(192, 57, 43), "Examination Results", "Enter Marks");

        // UpdateInfo
        createMenu(mb, "Update Details", new Color(192, 57, 43), "Update Faculty Details", "Update Student Details");

        // Fee
        createMenu(mb, "Fee Details", new Color(192, 57, 43), "Fee Structure", "Student Fee Form");

        // Utility
        createMenu(mb, "Utility", new Color(192, 57, 43), "Notepad", "Calculator");

        // About
        createMenu(mb, "About", new Color(192, 57, 43), "About");

        // Exit
        createMenu(mb, "Exit", new Color(192, 57, 43), "Exit");

        // Set the JMenuBar to the left side of the frame
        add(mb, BorderLayout.WEST);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createMenu(JMenuBar mb, String menuName, Color color, String... items) {
        JMenu menu = new JMenu(menuName);
        menu.setForeground( Color.BLACK);
        menu.setBackground(new Color(192, 57, 43));
        Font boldFont = new Font("Segoe UI", Font.BOLD, 14); // Bold text
        menu.setFont(boldFont);

        for (String itemName : items) {
            JMenuItem menuItem = new JMenuItem(itemName);

            // Explicitly set background color in JMenuItem UI
            menuItem.setUI(new BasicMenuItemUI() {
                @Override
                protected void installDefaults() {
                    super.installDefaults();
                    menuItem.setBackground(new Color(192, 57, 43));
                }
            });

            menuItem.setForeground(Color.BLACK);
            Font boldFont2 = new Font("Segoe UI", Font.BOLD, 14); // Bold text
            menuItem.setFont(boldFont2);
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
        mb.add(menu);
    }

     public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();

        if (msg.equals("Exit")) {
            setVisible(false);
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("New Faculty Information")) {
            new AddTeacher();
        } else if (msg.equals("New Student Information")) {
            new AddStudent();
        } else if (msg.equals("View Faculty Details")) {
            new TeacherDetails();
        } else if (msg.equals("View Student Details")) {
            new StudentDetails();
        } else if (msg.equals("Faculty Leave")) {
            new TeacherLeave();
        } else if (msg.equals("Student Leave")) {
            new StudentLeave();
        } else if (msg.equals("Faculty Leave Details")) {
            new TeacherLeaveDetails();
        } else if (msg.equals("Student Leave Details")) {
            new StudentLeaveDetails();
        } else if (msg.equals("Update Faculty Details")) {
            new UpdateTeacher();
        } else if (msg.equals("Update Student Details")) {
            new UpdateStudent();
        } else if (msg.equals("Enter Marks")) {
            new EnterMarks();
        } else if (msg.equals("Examination Results")) {
            new ExaminationDetails();
        } else if (msg.equals("Fee Structure")) {
            new FeeStructure();
        } else if (msg.equals("About")) {
            new About();
        } else if (msg.equals("Student Fee Form")) {
            new StudentFeeForm();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Project());
    }
}
