package chrvk.javaelementaty.hw17.task2;

import chrvk.javaelementaty.hw17.task1.Currency;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static String dayUrl(String date) {
        String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
        return URL + date;
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

    public static void sendRequest(String json, String day) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(dayUrl(day)).build();
        try {
            Response response = client.newCall(request).execute();
            returnMoney(parseJason(Objects.requireNonNull(response.body()).string()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String json = "";

        System.out.print("Enter date: ");
        Scanner sc = new Scanner(System.in);
        String day = sc.nextLine();

        long time = System.currentTimeMillis();

        sendRequest(json, day);

        time = System.currentTimeMillis() - time;
        System.out.println("Request time: " + time / 1000.0 + "s");


    }
}
