public class Bank {
    public String baseCurrency;
    public String currency;
    public double saleRateNB;
    public double purchaseRateNB;
    public  double saleRate;
    public double purchaseRate;

    @Override
    public String toString() {
        return "Bank{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                ", saleRateNB=" + saleRateNB +
                ", purchaseRateNB=" + purchaseRateNB +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate +
                '}';
    }
}
