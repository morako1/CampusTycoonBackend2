package CampusTycoon.GameLogic;

import CampusTycoon.UI.Component;

public class Money {




    public static Component MoneyText;
    private static int money = 2000;



    public static int getMoney() {
        return money;
    }

    public static void increaseMoney(int value) {
        money = money + value;
       Money.updateDisplay();


    }

    public static void decreaseMoney(int value) {
        money = money - value;


        Money.updateDisplay();
    }

    public static void updateDisplay() {
        if (MoneyText == null) {
            System.out.println("null");
            return;
        }
        MoneyText.text = String.valueOf(money);
    }

    public static void resetMoney() {
        money = 0;
    }
}
