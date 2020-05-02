package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SingleFrame implements ActionListener {
    
    private JFrame frame = new JFrame();
    private JButton button;
    private JComboBox<String> risk, sel;
    
    public SingleFrame(String name) {

        JPanel pan = new JPanel();
        
        frame.setBounds(300, 250, 450, 300);
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
        
        JLabel instr = new JLabel(" ~~~ Please select long or short, then select targeted risk ~~~ ");
        instr.setBounds(20, 130, 500, 30);
        pan.add(instr);
        
        JLabel prompt = new JLabel("Long or Short : ");
        prompt.setBounds(40, 180, 80, 35);
        pan.add(prompt);
        
        sel = new JComboBox<String>();
        sel.addItem("Long");
        sel.addItem("Short");
        sel.setSelectedItem(null);
        sel.setBounds(150, 180, 300, 35);
        pan.add(sel);
        
        JLabel rPrompt = new JLabel("Level of Risk : ");
        rPrompt.setBounds(40, 220, 80, 35);
        pan.add(rPrompt);
        
        risk = new JComboBox<String>();
        risk.addItem("High Risk ");
        risk.addItem("Slight Risk ");
        risk.addItem("Least Risk ");
        risk.setSelectedItem(null);
        risk.setBounds(150, 220, 300, 35);
        pan.add(risk);
        
        button = new JButton("Show Me the Stock");
        button.setBounds(220, 280, 60, 30);
        button.addActionListener(this);
        pan.add(button);
        
        frame.add(pan);
        frame.setVisible(true);    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (risk.getSelectedItem() == null || sel.getSelectedItem() == null) {
            System.out.println("Must make selection");
        } else {
            
        }
        
    }

}
