package university.management.system.vu;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Marks extends JFrame implements ActionListener {

    String rollno;
    JButton cancel;
    JLabel lblCGPA;

    Marks(String rollno) {
        this.rollno = rollno;

        setSize(500, 600);
        setLocation(500, 100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Varendra University");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel subheading = new JLabel("Result of Examination 2024");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);

        JLabel lblrollno = new JLabel("Student ID: " + rollno);
        lblrollno.setBounds(60, 100, 500, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);

        JLabel lblsemester = new JLabel();
        lblsemester.setBounds(60, 130, 500, 20);
        lblsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblsemester);

        JLabel sub1 = new JLabel();
        sub1.setBounds(100, 200, 500, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub1);

        JLabel sub2 = new JLabel();
        sub2.setBounds(100, 230, 500, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub2);

        JLabel sub3 = new JLabel();
        sub3.setBounds(100, 260, 500, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub3);

        JLabel sub4 = new JLabel();
        sub4.setBounds(100, 290, 500, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub4);

        JLabel sub5 = new JLabel();
        sub5.setBounds(100, 320, 500, 20);
        sub5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub5);

        lblCGPA = new JLabel();
        lblCGPA.setBounds(100, 350, 500, 20);
        lblCGPA.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(lblCGPA);

        try {
            Conn c = new Conn();

            ResultSet rs1 = c.s.executeQuery("select * from subject where rollno = '" + rollno + "'");
            while (rs1.next()) {
                sub1.setText(rs1.getString("subject1"));
                sub2.setText(rs1.getString("subject2"));
                sub3.setText(rs1.getString("subject3"));
                sub4.setText(rs1.getString("subject4"));
                sub5.setText(rs1.getString("subject5"));
            }

            while (rs1.next()) {
                System.out.println("Subject 1: " + rs1.getString("subject1") + ", Marks 1: " + rs1.getInt("marks1"));
                System.out.println("Subject 2: " + rs1.getString("subject2") + ", Marks 2: " + rs1.getInt("marks2"));
                System.out.println("Subject 3: " + rs1.getString("subject3") + ", Marks 3: " + rs1.getInt("marks3"));
                System.out.println("Subject 4: " + rs1.getString("subject4") + ", Marks 4: " + rs1.getInt("marks4"));
                System.out.println("Subject 5: " + rs1.getString("subject5") + ", Marks 5: " + rs1.getInt("marks5"));
            }

            ResultSet rs2 = c.s.executeQuery("select * from marks where rollno = '" + rollno + "'");
            double totalGradePoints = 0;
            int totalSubjects = 0;
            while (rs2.next()) {
                totalSubjects++;
                totalGradePoints += calculateGradePoint(rs2.getInt("marks1"));
                totalGradePoints += calculateGradePoint(rs2.getInt("marks2"));
                totalGradePoints += calculateGradePoint(rs2.getInt("marks3"));
                totalGradePoints += calculateGradePoint(rs2.getInt("marks4"));
                totalGradePoints += calculateGradePoint(rs2.getInt("marks5"));
                lblsemester.setText("Semester " + rs2.getString("semester"));
            }

            double cgpa = totalGradePoints / (totalSubjects * 5); // Assuming 5 subjects in each semester
            lblCGPA.setText("CGPA: " + String.format("%.2f", cgpa));

        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Back");
        cancel.setBounds(250, 500, 120, 25);
        cancel.setBackground(Color.white);
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    // Method to calculate grade points based on marks
    private double calculateGradePoint(int marks) {
        if (marks >= 90) {
            return 4.0;
        } else if (marks >= 80) {
            return 3.5;
        } else if (marks >= 70) {
            return 3.0;
        } else if (marks >= 60) {
            return 2.5;
        } else if (marks >= 50) {
            return 2.0;
        } else if (marks >= 40) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new Marks("");
    }
}
