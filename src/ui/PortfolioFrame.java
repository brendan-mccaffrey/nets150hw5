package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        
        JLabel instr = new JLabel(" ~~~ Please select your targeted portfolio Beta (0 to 3, where 1 ~ market) ~~~ ");
        instr.setBounds(20, 130, 500, 30);
        pan.add(instr);
        
        JLabel betaPrompt = new JLabel("Beta (risk premium) : ");
        betaPrompt.setBounds(40, 180, 80, 35);
        pan.add(betaPrompt);
        
        beta = new JTextField(20);
        beta.setBounds(150, 180, 200, 35);
        pan.add(beta);
        
        button = new JButton("Show Me the Stocks");
        button.setBounds(220, 280, 400, 30);
        button.addActionListener(this);
        pan.add(button);
        
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
