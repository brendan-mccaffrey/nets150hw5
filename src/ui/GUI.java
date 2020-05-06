package ui;

import data.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener {

    private JFrame frame = new JFrame();
    private JButton port, lstock, single;
    private JTextField name, stock;

    private String userName;

    public GUI() {

        JPanel panel = new JPanel();

        frame.setBounds(300, 250, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Stock Prophecy");

        JLabel welcome = new JLabel("~~~~~ Welcome ~~~~~");
        welcome.setBounds(200, 20, 100, 30);
        panel.add(welcome);

        JLabel intro = new JLabel("This application will make you the greatest investor of all time.");
        intro.setBounds(120, 70, 260, 30);
        panel.add(intro);

        JLabel br = new JLabel(" ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––– ");
        br.setBounds(20, 110, 460, 10);
        panel.add(br);

        JLabel instr = new JLabel(" ~~~ Enter your name and a stock, then select what you want ~~~ ");
        instr.setBounds(20, 130, 460, 30);
        panel.add(instr);

        JLabel namePrompt = new JLabel("First Name : ");
        namePrompt.setBounds(40, 180, 80, 35);
        panel.add(namePrompt);

        name = new JTextField(30);
        name.setBounds(150, 180, 300, 35);
        panel.add(name);

        JLabel stockPrompt = new JLabel("Stock (ticker) : ");
        stockPrompt.setBounds(40, 180, 80, 35);
        panel.add(stockPrompt);

        stock = new JTextField(30);
        stock.setBounds(150, 230, 300, 35);
        panel.add(stock);
        
        single = new JButton("Stock of the Day");
        single.setBounds(60, 280, 150, 30);
        single.addActionListener(this);
        panel.add(single);


        port = new JButton("Full Portfolio");
        port.setBounds(290, 280, 150, 30);
        port.addActionListener(this);
        panel.add(port);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (name.getText().isEmpty() || stock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must enter your name and a stock ticker");
        } else {
            userName = name.getText();
            Object src = e.getSource();
            
            Data.setStock(stock.getText());
            
            if (src == single) {
                JOptionPane.showMessageDialog(null, "You have selected to get a single stock");
                frame.removeAll();
                frame.dispose();
                SingleFrame jawn = new SingleFrame(userName);
            } else if (src == port) {
                JOptionPane.showMessageDialog(null, "You have selected to get a portfolio");
                frame.removeAll();
                frame.dispose();
                PortfolioFrame jawn = new PortfolioFrame(userName);
            } else {
                throw new IllegalArgumentException("What was clicked?");
            }
        }

    }


}
