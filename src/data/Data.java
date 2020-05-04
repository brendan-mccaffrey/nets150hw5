package data;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Data {
    HashMap<String, String> url;

    public Data() {
        this.url = new HashMap<String, String>();
        this.url.put("Large Cap",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-large-cap/");
        this.url.put("Gainers",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-gainers/");
        this.url.put("Losers",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-losers/");
        this.url.put("Active",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-active/");
        this.url.put("Volatile",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-most-volatile/");
        this.url.put("Overbought",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-overbought/");
        this.url.put("Oversold",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-oversold/");
    }

    public ArrayList getStockData(String type) {
        ArrayList<Stock> stocks = new ArrayList<Stock>();

        try {
            Document doc = Jsoup.connect(this.url.get(type)).get();
//            "tr.tv-data-table__row tv-data-table__stroke tv-screener-table__result-row"
            Elements links = doc.select(
                    ".tv-data-table__row.tv-data-table__stroke.tv-screener-table__result-row");
//            Elements links = doc
//                    .select("div.tv-screener-table__symbol-right-part");

            for (Element link : links) {
                // two exceptions listed differently
                Elements data = link.getAllElements();
                stocks.add(new Stock(data));
                /*
                 * 0 - all 1-3 - full name 4 - stock name 5 - company name 6 -
                 * price 7 - percent change 8 - change 9-10 - recommendation 11
                 * - volume 12 - market cap 13 - Price-to-Earnings Ratio 14 -
                 * Earning Per Share 15 - # of employees 16-18 - Sector
                 */

//                int i = 0;
//                for (Element child : children) {
//                    System.out.print(i + ": " + child.text() + "\t");
//                    i++;
//                }
//                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//                System.out.println("SECTOR: " + data.get(16).text());
//                System.out.println("NAME: " + data.get(1).text());
//                System.out.println("PRICE: " + data.get(6).text() + ", CHANGE: "
//                        + data.get(8).text() + ", PERCENT CHANGE: "
//                        + data.get(7).text());
//                System.out.println("RECOMMENDATION: " + data.get(9).text());

            }
        } catch (Exception e) {
            System.out.println("Error connecting to market website.");
            e.printStackTrace();
        }
        // System.out.println("Length: " + names.size() + " Length: " +
        // countries.size());
        return stocks;
    }
}
