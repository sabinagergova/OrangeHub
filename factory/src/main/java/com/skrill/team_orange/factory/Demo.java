package com.skrill.team_orange.factory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<FarmProductionInterface> farm = new ArrayList<FarmProductionInterface>();
        Random r = new Random();
        int varPrice;
        while (true) {
            input = sc.next();
            if (input.matches("animal,[a-z]+") || input.matches("plant,[a-z]+") || "view".equals(input)) {
                if ("view".equals(input)) {
                    for (int i = 0; i < farm.size(); i++) {
                        varPrice = r.nextInt(50);
                        System.out.println("The price for " + farm.get(i).toString() + " is " + farm.get(i).getPrice(varPrice));
                    }
                } else {
                    String[] inputs = input.split("[,]");
                        farm.add(ProdFactory.getProduction(inputs[0], inputs[1]));
                }
            } else {
                System.out.println("Wrong input");
            }
        }

    }
}
