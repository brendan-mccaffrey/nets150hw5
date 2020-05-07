package data;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Data {
    HashMap<String, String> url;

    private static String userStock = null;
    private Stock uStock;

    public Data() {
        this.url = new HashMap<String, String>();
        this.url.put("Default",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-large-cap/");
        this.url.put("Large Cap",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-large-cap/");
        this.url.put("Gainers",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-gainers/");
        this.url.put("Losers",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-losers/");
        this.url.put("Most Active",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-active/");
        this.url.put("Most Volatile",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-most-volatile/");
        this.url.put("Overbought",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-overbought/");
        this.url.put("Oversold",
                "https://www.tradingview.com/markets/stocks-usa/market-movers-oversold/");
        this.url.put("Energy Minerals",
                "https://www.tradingview.com/markets/stocks-usa/sectorandindustry-sector/energy-minerals/");
        this.url.put("Technology",
                "https://www.tradingview.com/markets/stocks-usa/sectorandindustry-sector/electronic-technology/");
        this.url.put("Technology2",
                "https://www.tradingview.com/markets/stocks-usa/sectorandindustry-sector/technology-services/");
        this.url.put("BioTech",
                "https://www.tradingview.com/markets/stocks-usa/sectorandindustry-sector/health-technology/");
        this.url.put("Medicine",
                "https://www.tradingview.com/markets/stocks-usa/sectorandindustry-sector/health-services/");
        this.url.put("Finance",
                "https://www.tradingview.com/markets/stocks-usa/sectorandindustry-sector/finance/");
        this.url.put("Manufacturing",
                "https://www.tradingview.com/markets/stocks-usa/sectorandindustry-sector/producer-manufacturing/");
        this.url.put("Retail",
                "https://www.tradingview.com/markets/stocks-usa/sectorandindustry-sector/retail-trade/");
    }

    public Stock setStock(String ticker) {
        String userUrl = "https://finance.yahoo.com/quote/" + ticker;
        String price = "N/A", ch = "N/A", pch = "N/A", vol = "N/A", cap = "N/A",
                p2e = "N/A", eps = "N/A";
        try {
            Document doc = Jsoup.connect(userUrl).get();
            Elements links = doc.getElementsByClass("Bdc($seperatorColor)");
            links.addAll(doc.getElementsByClass("Mend(20px)"));
//            Elements links = doc.getElementsByClass("Trsdu(0.3s)");
//            System.out.println("Found links: " + links.size());
//            String[] changes = links.get(10).text().split(" ");
//            String change = changes[0];
//            String pChange = changes[1];
//            uStock = new Stock(ticker, links.get(9).text(), pChange, change,
//                    links.get(15).text(), links.get(17).text(),
//                    links.get(19).text(), links.get(20).text());
//            System.out.println(uStock.toString());
//            int i = 0;
            for (Element link : links) {
                String curr = link.text();
                if (curr.contains("Market Cap")) {
//                    System.out.println(i + ": cap " + link.text());
                    cap = curr.substring(curr.indexOf("p") + 2);
//                    System.out.println(cap);
                } else if (curr.contains("PE Ratio")) {
//                    System.out.println(i + ": p2e " + link.text());
                    p2e = curr.substring(curr.indexOf(")") + 2);
//                    System.out.println(p2e);
                } else if (curr.contains("EPS")) {
//                    System.out.println(i + ": eps " + link.text());
                    eps = curr.substring(curr.indexOf(")") + 2);
//                    System.out.println(eps);
                } else if (curr.contains("Volume") && !curr.contains("Avg")) {
//                    System.out.println(i + ": vol " + link.text());
                    vol = curr.substring(curr.indexOf("e") + 2);
//                    System.out.println(vol);
                } else if (curr.contains("EDT") && curr.contains("At")) {
//                    System.out.println(i + ": price, ch, pch" + link.text());
                    price = curr.substring(0,
                            Math.max(curr.indexOf("+"), curr.indexOf("-")));
                    ch = curr.substring(
                            Math.max(curr.indexOf("+"), curr.indexOf("-")),
                            curr.indexOf("(") - 1);
                    pch = curr.substring(curr.indexOf("(") + 1,
                            curr.indexOf(")"));
//                    System.out.println(price + ", " + ch + ", " + pch);
                }
//                System.out.println(link.text());
//                i++;
//                Elements children = link.getAllElements();
//                for (Element child : children) {
//                    System.out.println(i + ": " + child);
//                    i++;
//                }
            }
        } catch (Exception e) {
            System.out.println("Error finding reference stock " + e);
            e.printStackTrace(System.out);
        }
        if (cap.equals("N/A") || price.equals("N/A")) {
//            System.out.println("Error null Error null " + cap + price);
            return null;
        }
        Stock uStock = new Stock(ticker, price, pch, ch, vol, cap, p2e, eps);
//        System.out.println(uStock.toString());
        return uStock;
    }

    public Stock getStock() {
        return uStock;
    }

    public ArrayList<Stock> getStockData(String type) {
        ArrayList<Stock> stocks = new ArrayList<Stock>();

        try {
            Document doc = Jsoup.connect(this.url.get(type)).get();
//            "tr.tv-data-table__row tv-data-table__stroke tv-screener-table__result-row"
            Elements links = doc.select(
                    ".tv-data-table__row.tv-data-table__stroke.tv-screener-table__result-row");
            if (type.equals("Technology")) {
                doc = Jsoup.connect(this.url.get("Technology2")).get();
                links.addAll(doc.select(
                        ".tv-data-table__row.tv-data-table__stroke.tv-screener-table__result-row"));
            }
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