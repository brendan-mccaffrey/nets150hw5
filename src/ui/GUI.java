package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener {
    
    private JFrame frame = new JFrame();
    private JButton port, lstock, single;
    private JTextField name;
    
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
        
        JLabel instr = new JLabel(" ~~~ Please enter your name, then select single stock or full portfolio ~~~ ");
        instr.setBounds(20, 130, 460, 30);
        panel.add(instr);
        
        JLabel namePrompt = new JLabel("First Name : ");
        namePrompt.setBounds(40, 180, 80, 35);
        panel.add(namePrompt);
        
        name = new JTextField(30);
        name.setBounds(150, 180, 300, 35);
        panel.add(name);
        
        single = new JButton("Stock of the Day");
        single.setBounds(60, 230, 100, 30);
        single.addActionListener(this);
        panel.add(single);
        
        lstock = new JButton("List of Stocks");
        lstock.setBounds(200, 230, 100, 30);
        lstock.addActionListener(this);
        panel.add(lstock);
        
        port = new JButton("Full Portfolio");
        port.setBounds(340, 230, 100, 30);
        port.addActionListener(this);
        panel.add(port);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (name.getText().isEmpty()) {
            System.out.println("Must enter a valid name.");
        } else {
            userName = name.getText();
            Object src = e.getSource();
            if (src == single) {
                JOptionPane.showMessageDialog(null, "You have selected to get a single stock");
                frame.removeAll();
                frame.dispose();
                SingleFrame jawn = new SingleFrame(userName);
            } else if (src == lstock) {
                JOptionPane.showMessageDialog(null, "You have selected to get several stocks");
                frame.removeAll();
                frame.dispose();
                SeveralFrame jawn = new SeveralFrame(userName);
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
    
    public void single() {
        
        
        
    }
    
    public void several() {
        JPanel pan = new JPanel();
    }
    
    public void port() {
        JPanel pan = new JPanel();
    }

}
