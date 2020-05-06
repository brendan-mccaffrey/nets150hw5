
import java.util.ArrayList;

import data.Data;
import data.Stock;
import ui.GUI;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Data d = new Data();
        String[] types = { "Large Cap", "Gainers", "Losers", "Active", "Volatile", "Overbought", "Oversold" };
        ArrayList<Stock> stocks = d.getStockData(types[1]);
        for (Stock s : stocks) {
            System.out.println(s.toString());
        }
        GUI ui = new GUI();
        System.out.println("good rep");
    }

}
