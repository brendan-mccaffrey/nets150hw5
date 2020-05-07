package data;

import java.util.ArrayList;

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
     * 0 - all 1-3 - full name 4 - stock name 5 - company name 6 - price 7 -
     * percent change 8 - change 9-10 - recommendation 11 - volume 12 - market
     * cap 13 - Price-to-Earnings Ratio 14 - Earning Per Share 15 - # of
     * employees 16-18 - Sector
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

    public Stock(String s, String p, String pChange, String change, String vol,
            String cap, String p2e, String eps) {
        this.stock = s;
        this.price = p;
        this.pChange = pChange;
        this.change = change;
        this.vol = vol;
        this.cap = cap;
        this.p2e = p2e;
        this.eps = eps;
        this.company = "N/A";
        this.employees = "N/A";
        this.sec = "N/A";
        this.rec = "N/A";
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

    public double toNumericalValue(String input) {
        double output;
        double factor = 1.0;
        if (input.contains("K")) {
            factor = 1000.0;
        }
        if (input.contains("M")) {
            factor = 1000000.0;
        }
        if (input.contains("B")) {
            factor = 1000000000.0;
        }
        if (input.contains("T")) {
            factor = 1000000000000.0;
        }
        String clean = input.replaceAll("[^\\d.-]", "");
        try {
            output = Double.parseDouble(clean);
            output = output * factor;
        } catch (Exception e) {
//            System.out.println(
//                    this.stock + ": Error parsing " + input + "\n" + e);
            output = 0.0;
        }
//        System.out.println(this.stock + ": Input\t" + input);
//        System.out.println(this.stock + ": Cleaned\t" + clean);
//        System.out.println(this.stock + ": Final\t" + output);
        return output;
    }

    public ArrayList<Double> toVector() {
        ArrayList<Double> s = new ArrayList<Double>();
//        private String price;
        s.add(this.toNumericalValue(this.price));
//        private String pChange;
        s.add(this.toNumericalValue(this.pChange));
//        private String change;
        s.add(this.toNumericalValue(this.change));
//        private String rec;
//        private String vol;
        s.add(this.toNumericalValue(this.vol));
//        private String cap;
        s.add(this.toNumericalValue(this.cap));
//        private String p2e;
        s.add(this.toNumericalValue(this.p2e));
//        private String eps;
        s.add(this.toNumericalValue(this.eps));
//        private String employees;
//        private String sec;
        return s;
    }

    public static double getDotProduct(ArrayList<Double> v1,
            ArrayList<Double> v2) {
        double product = 0;
        for (int i = 0; i < v1.size(); i++) {
            product += v1.get(i) * v2.get(i);
        }
        return product;
    }

    public static double getMagnitude(ArrayList<Double> v1) {
        double mag = 0;
        for (Double d : v1) {
            mag += d * d;
        }
        return Math.sqrt(mag);
    }

    public static double getCosineSimilarity(ArrayList<Double> v1, Stock s2) {
        ArrayList<Double> v2 = s2.toVector();
        double m1 = getMagnitude(v1);
        double m2 = getMagnitude(v2);
        return getDotProduct(v1, v2) / (m1 * m2);
    }

    public String toString() {
        return "SECTOR:\t\t" + this.sec + "\n" + "STOCK:\t\t" + this.stock
                + "\n" + "COMPANY:\t\t" + this.company + "\n" + "PRICE:\t\t"
                + this.price + "\n" + "CHANGE:\t\t" + this.change + "\n"
                + "% CHANGE:\t\t" + this.pChange + "\n" + "VOLUME:\t\t"
                + this.vol + "\n" + "Price/Earnings:\t\t" + this.p2e + "\n"
                + "Earnings/Share:\t" + this.eps + "\n" + "MARKET CAP:\t\t"
                + this.cap + "\n" + "# of EMPLOYEES:\t" + this.employees + "\n"
                + "RECOMMENDATION:\t" + this.rec + "\n";
    }

}