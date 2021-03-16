package chrvk.javaelementaty.hw17.task1;

import com.google.gson.Gson;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Enter date: ");
        Scanner sc = new Scanner(System.in);
        String day = sc.nextLine();

        long time = System.currentTimeMillis();
        Response response = HttpUtil.sendRequest(dayUrl(day));

        time = System.currentTimeMillis() - time;
        System.out.println("Request time: " + time / 1000.0 + "s");

        if (response.getException() == null) {
            System.out.println("ResponseCode: " + response.responseCode);
            Currency currency = parseJason(response.body);
            returnMoney(currency);

        } else {
            System.out.println("Request failed: " + response.getException());
        }
    }

    public static Currency parseJason(String json) {
        Gson gson = new Gson();
        Currency currency = gson.fromJson(json, Currency.class);
        return currency;
    }


    public static void returnMoney(Currency currency) {
        if (currency.exchangeRate.size() == 0) {
            System.out.println("Data empty");
        } else {
            for (int i = 0; i < currency.exchangeRate.size(); i++) {
                if (currency.exchangeRate.get(i).currency != null) {
                    if (currency.exchangeRate.get(i).currency.equals("USD")) {
                        System.out.println("Sale USD: " + currency.exchangeRate.get(i).saleRate + " , purchase USD: "
                                + currency.exchangeRate.get(i).purchaseRate);
                    }
                }
            }
        }
    }

    public static String dayUrl(String date) {
        String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
        return URL + date;
    }

}
