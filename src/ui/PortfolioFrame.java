package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.Data;
import data.Stock;

public class PortfolioFrame implements ActionListener {

    private JFrame frame = new JFrame();
    private JFrame recommendation = new JFrame();
    private JButton button;
    private JTextField beta;

    public PortfolioFrame(String name, Stock choice, String spec) {

        JPanel pan = new JPanel();
        JPanel container = new JPanel();

        frame.setBounds(300, 250, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Stock Prophecy");
        recommendation.setBounds(300, 400, 450, 300);
        recommendation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        recommendation.setTitle("Your Chosen Stock");

        JLabel welcome = new JLabel("~~~~~ Welcome " + name + " ~~~~~");
        welcome.setBounds(200, 20, 100, 30);
        pan.add(welcome);

        JLabel intro = new JLabel(
                "This application will make you the greatest investor of all time.");
        intro.setBounds(120, 70, 260, 30);
        pan.add(intro);

        JLabel br = new JLabel(
                " ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––– ");
        br.setBounds(20, 110, 460, 10);
        pan.add(br);

        JLabel instr = new JLabel(
                " ~~~ You selected single stock, and referenced "
                        + choice.getStockName() + " ~~~ ");
        instr.setBounds(20, 130, 500, 30);
        pan.add(instr);
        JLabel rez = new JLabel(" ~~~ Here is our recommendation ~~~ ");
        rez.setBounds(20, 130, 500, 30);
        pan.add(rez);

        // Here we will present our recommended stock
        ArrayList<Double> v1 = choice.toVector();
        Data d = new Data();
        ArrayList<Stock> stocks = d.getStockData(spec);
        HashMap<Double, Stock> coses = new HashMap<Double, Stock>();
        ArrayList<Double> vals = new ArrayList<Double>();
        String rec = "";
        for (Stock s : stocks) {
            if (s.getStockName().equals(choice.getStockName())) {
                continue;
            }
            double curr = choice.getCosineSimilarity(v1, s);
////            System.out.println("CosSim " + choice.getStockName() + " "
//                    + s.getStockName() + " " + curr);
            coses.put(curr, s);
            vals.add(curr);
        }
        Collections.sort(vals, Collections.reverseOrder());
        for (int i = 1; i < 11; i++) {
            rec += i + ". Cosine Similarity = " + vals.get(i) + "\n"
                    + coses.get(vals.get(i)).toString() + "\n\n";
        }
        JTextArea ta = new JTextArea(rec.toString());
        ta.setLineWrap(true);
        ta.setAutoscrolls(true);
        ta.setEditable(false);
        ta.setBounds(20, 130, 425, 100);
        container.add(ta);
        JScrollPane scroll = new JScrollPane(ta,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(375, 250));
        container.add(scroll);
        recommendation.add(container);
        frame.add(pan);
        frame.setVisible(true);
        recommendation.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (beta.getText() == null) {
            System.out.println("Must make selection");
        } else {

        }

    }

}