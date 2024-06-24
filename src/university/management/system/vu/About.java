package university.management.system.vu;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {

    About() {
        setSize(1000, 1000);
        setLocation(400, 150);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/vu pic.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(750, 20, 300, 200);
        add(image);

        JLabel heading = new JLabel("<html> Varendra University<br/>Management System</html>");
        heading.setBounds(70, 20, 700, 130);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        JLabel name = new JLabel("<html>Developed By:<br/>Taslima Yeasmin Oyshi<br/>Abdullah Al Masum<html>");
        name.setBounds(70, 280, 550, 540);
        name.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(name);
        
        JLabel detail = new JLabel("<html><p>Varendra University, established in 2012, received approval from the Ministry of Education and the University Grants Commission. Located in Rajshahi, its permanent campus spans across Chandrima on Bypass Road and Kazla in Motihar. The future permanent campus is planned for Kharkhari, Paba Upazila. Offering diverse faculties like Business and Law, Arts and Social Science, Engineering, Science and Technology, the university houses departments ranging from Business Administration to Sociology. With approximately 6,500 students, it provides scholarships to over 6,700 students. Boasting 152 full-time and 40 adjunct faculty members, along with 84 officials and 39 supporting staff, the university features 58 AC classrooms equipped with multimedia facilities, broadband, and Wi-Fi. It hosts 21 well-equipped laboratories and libraries across its campuses. Moreover, it fosters various clubs and extracurricular activities, including cultural programs, study tours, seminars, debates, and participation in national and international competitions, ensuring holistic development for its students.<p/>");
        detail.setBounds(70, 280, 780, 200);
        detail.setFont(new Font("Tahoma", Font.BOLD, 13));
        add(detail);

        JLabel rollno = new JLabel("Student ID:221311209,222311081");
        rollno.setBounds(70, 310, 550, 560);
        rollno.setFont(new Font("Tahoma", Font.PLAIN, 13));
        add(rollno);

        JLabel contact = new JLabel("Contact:ty.oyshi@gmail.com");
        contact.setBounds(70, 320, 550, 570);
        contact.setFont(new Font("Tahoma", Font.PLAIN, 13));
        add(contact);

        setLayout(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        new About();
    }
}
