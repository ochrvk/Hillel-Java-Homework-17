package chrvk.javaelementaty.hw17.task1;

import java.util.List;

public class Currency {
    public String date;
    public String bank;
    public int baseCurrency;
    public String baseCurrencyLit;
    public List<Bank> exchangeRate;

    public List<Bank> getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public String toString() {
        return "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
