package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Data;

public class PortfolioFrame implements ActionListener {

    private JFrame frame = new JFrame();
    private JButton button;
    private JTextField beta;

    public PortfolioFrame(String name) {

        JPanel pan = new JPanel();

        frame.setBounds(300, 250, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Stock Prophecy");

        JLabel welcome = new JLabel("~~~~~ Welcome " + name + " ~~~~~");
        welcome.setBounds(200, 20, 100, 30);
        pan.add(welcome);

        JLabel intro = new JLabel("This application will make you the greatest investor of all time.");
        intro.setBounds(120, 70, 260, 30);
        pan.add(intro);

        JLabel br = new JLabel(" ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––– ");
        br.setBounds(20, 110, 460, 10);
        pan.add(br);

        JLabel instr = new JLabel(" ~~~ You selected portfolio, and referenced " + Data.getStock() + " ~~~ ");
        instr.setBounds(20, 130, 500, 30);
        pan.add(instr);

        // Here we will print the recommended portfolio of stocks on the UI

        frame.add(pan);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (beta.getText() == null) {
            System.out.println("Must make selection");
        } else {

        }

    }

}
