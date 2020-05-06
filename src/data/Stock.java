package data;

import org.jsoup.select.Elements;

public class Stock {

    private String stock;
    private String company;
    private String price;
    private String pChange;
    private String change;
    private String rec;
    private String vol;
    private String cap;
    private String p2e;
    private String eps;
    private String employees;
    private String sec;
    /*
     * 0 - all 1-3 - full name 4 - stock name 5 - company name 6 - price 7 - percent
     * change 8 - change 9-10 - recommendation 11 - volume 12 - market cap 13 -
     * Price-to-Earnings Ratio 14 - Earning Per Share 15 - # of employees 16-18 -
     * Sector
     */

    public Stock(Elements data) {
        this.stock = data.get(4).text();
        this.company = data.get(5).text();
        this.price = data.get(6).text();
        this.pChange = data.get(7).text();
        this.change = data.get(8).text();
        this.rec = data.get(9).text();
        this.vol = data.get(11).text();
        this.cap = data.get(12).text();
        this.p2e = data.get(13).text();
        this.eps = data.get(14).text();
        this.employees = data.get(15).text();
        this.sec = data.get(16).text();
    }

    public String getStockName() {
        return this.stock;
    }

    public String getCompanyName() {
        return this.company;
    }

    public String getPrice() {
        return this.price;
    }

    public String getPercentChange() {
        return this.pChange;
    }

    public String getChange() {
        return this.change;
    }

    public String getRecommendation() {
        return this.rec;
    }

    public String getVolume() {
        return this.vol;
    }

    public String getMarketCap() {
        return this.cap;
    }

    public String getPriceToEarnings() {
        return this.p2e;
    }

    public String getEarningsPerShare() {
        return this.eps;
    }

    public String getEmployeeCount() {
        return this.employees;
    }

    public String getSector() {
        return this.sec;
    }

    public String toString() {
        return "SECTOR:\t\t" + this.sec + "\n" + "STOCK:\t\t" + this.stock + "\n" + "COMPANY:\t" + this.company + "\n"
                + "PRICE:\t\t" + this.price + "\n" + "CHANGE:\t\t" + this.change + "\n" + "% CHANGE:\t" + this.pChange
                + "\n" + "VOLUME:\t\t" + this.vol + "\n" + "Price/Earnings:\t" + this.p2e + "\n" + "Earnings/Share:\t"
                + this.eps + "\n" + "MARKET CAP:\t" + this.cap + "\n" + "# of EMPLOYEES:\t" + this.employees + "\n"
                + "RECOMMENDATION:\t" + this.rec + "\n";
    }

}
