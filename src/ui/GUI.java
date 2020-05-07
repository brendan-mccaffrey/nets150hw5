package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Data;
import data.Stock;

public class GUI implements ActionListener {

    private JFrame frame = new JFrame();
    private JButton port, lstock, single;
    private JTextField name, stock;
    private JComboBox type, sector, find;
    private Data d = new Data();

    private String userName;

    public GUI() {

        JPanel panel = new JPanel();

        frame.setBounds(300, 250, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Cosine Stock Prophecy");

        JLabel welcome = new JLabel("~~~~~ Welcome ~~~~~");
        welcome.setBounds(200, 20, 100, 30);
        panel.add(welcome);

        JLabel intro = new JLabel(
                "This application will make you the greatest investor of all time.");
        intro.setBounds(120, 70, 260, 30);
        panel.add(intro);

        JLabel br = new JLabel(
                " ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––– ");
        br.setBounds(20, 110, 460, 10);
        panel.add(br);

        JLabel instr = new JLabel(
                " ~~~ Enter your name and a stock you currently like, then select what you want ~~~");
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

        String[] findBy = { "Default", "By Trend", "By Sector" };
        String[] types = { "Large Cap", "Gainers", "Losers", "Most Active",
                "Most Volatile", "Overbought", "Oversold" };
        String[] sectors = { "Energy Minerals", "Technology", "BioTech",
                "Medicine", "Finance", "Manufacturing", "Retail" };

        find = new JComboBox(findBy);
        find.addActionListener(this);
        type = new JComboBox(types);
        type.addActionListener(this);
        sector = new JComboBox(sectors);
        sector.addActionListener(this);

        type.setVisible(false);
        sector.setVisible(false);
        JPanel select = new JPanel(new GridLayout(1, 2));
        JPanel modifiers = new JPanel(new GridLayout(2, 1));
        select.add(new JLabel("Options:"));
        select.add(find);
        modifiers.add(type);
        modifiers.add(sector);
        panel.add(select);
        panel.add(modifiers);

        JPanel buttons = new JPanel(new GridLayout(1, 2));
        single = new JButton("Stock of the Day");
        single.setBounds(60, 400, 150, 30);
        single.addActionListener(this);
        buttons.add(single);

        port = new JButton("Full Portfolio");
        port.setBounds(290, 400, 150, 30);
        port.addActionListener(this);
        buttons.add(port);
        panel.add(buttons);

        frame.add(panel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (name.getText().isEmpty() || stock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "You must enter your name and a stock ticker");
        } else {
            if (find.getSelectedItem() == null) {

            } else {
                if (find.getSelectedItem().equals("Default")) {
                    type.setVisible(false);
                    sector.setVisible(false);
                } else if (find.getSelectedItem().equals("By Trend")) {
                    type.setVisible(true);
                    sector.setVisible(false);
                } else if (find.getSelectedItem().equals("By Sector")) {
                    sector.setVisible(true);
                    type.setVisible(false);
                }
                if (!find.getSelectedItem().equals("Default")
                        && (type.getSelectedItem() == null
                                || sector.getSelectedItem() == null)) {
                    JOptionPane.showMessageDialog(null,
                            "Please select a trend/sector");
                } else {
                    userName = name.getText();
                    Object src = e.getSource();

                    if (src == single) {
                        JOptionPane.showMessageDialog(null,
                                "You have selected to get a single stock");
                        System.out.println(stock.getText());
                        Stock choice = d
                                .setStock(stock.getText().toUpperCase());

                        if (choice != null) {
                            frame.removeAll();
                            frame.dispose();
                            String spec = "Default";
                            if (find.getSelectedItem().equals("By Trend")) {
                                spec = (String) type.getSelectedItem();

                            } else if (find.getSelectedItem()
                                    .equals("By Sector")) {
                                spec = (String) sector.getSelectedItem();
                            }
                            SingleFrame jawn = new SingleFrame(userName, choice,
                                    spec);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Error fetching data for the stock you've chosen");
                        }

                    } else if (src == port) {
                        JOptionPane.showMessageDialog(null,
                                "You have selected to get a portfolio");
                        Stock choice = d
                                .setStock(stock.getText().toUpperCase());
                        if (choice != null) {
                            frame.removeAll();
                            frame.dispose();
                            String spec = "Default";
                            if (find.getSelectedItem().equals("By Trend")) {
                                spec = (String) type.getSelectedItem();

                            } else if (find.getSelectedItem()
                                    .equals("By Sector")) {
                                spec = (String) sector.getSelectedItem();
                            }
                            PortfolioFrame jawn = new PortfolioFrame(userName,
                                    choice, spec);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Error fetching data for the stock you've chosen");
                        }

                    }
                }
            }
        }

    }

}
