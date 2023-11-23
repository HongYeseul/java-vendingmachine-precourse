package vendingmachine.view;

import vendingmachine.model.coin.Coin;

import java.util.List;

import static vendingmachine.model.constants.Index.COIN_100_INDEX;
import static vendingmachine.model.constants.Index.COIN_10_INDEX;
import static vendingmachine.model.constants.Index.COIN_500_INDEX;
import static vendingmachine.model.constants.Index.COIN_50_INDEX;
import static vendingmachine.view.constants.ErrorMessage.ERROR_TAG;
import static vendingmachine.view.constants.OutputMessage.ASK_DRINKS;
import static vendingmachine.view.constants.OutputMessage.ASK_PURCHASE_DRINK_TYPE;
import static vendingmachine.view.constants.OutputMessage.ASK_TOTAL_MONEY_OF_VENDING_MACHINE;
import static vendingmachine.view.constants.OutputMessage.ASK_USER_INPUT_MONEY;
import static vendingmachine.view.constants.OutputMessage.SHOW_BALANCE;
import static vendingmachine.view.constants.OutputMessage.SHOW_TOTAL_USER_BALANCE_START_FLAG;
import static vendingmachine.view.constants.OutputMessage.SHOW_USER_BALANCE;
import static vendingmachine.view.constants.OutputMessage.SHOW_VENDING_MACHINE_COINS;
import static vendingmachine.view.constants.OutputMessage.SHOW_VENDING_MACHINE_HOLD_COIN_START_TAG;

public class OutputView {
    public static void askMachineTotalMoney(){
        print(ASK_TOTAL_MONEY_OF_VENDING_MACHINE);
    }

    public static void printVendingMachineCoins(List<Integer> coinCounts){
        print(SHOW_VENDING_MACHINE_HOLD_COIN_START_TAG);
        print(String.format(SHOW_VENDING_MACHINE_COINS,
                coinCounts.get(0),
                coinCounts.get(1),
                coinCounts.get(2),
                coinCounts.get(3)
        ));
    }

    public static void askDrinkFromUsers() {
        print(ASK_DRINKS);
    }

    public static void askUserInputMoney() {
        print(ASK_USER_INPUT_MONEY);
    }

    public static void showBalance(int balance) {
        print(String.format(SHOW_BALANCE, balance));
    }

    public static void showUserTotalBalance(int[] balance) {
        print(SHOW_TOTAL_USER_BALANCE_START_FLAG);

        showDetailOfBalance(Coin.COIN_500, balance, COIN_500_INDEX);
        showDetailOfBalance(Coin.COIN_100, balance, COIN_100_INDEX);
        showDetailOfBalance(Coin.COIN_50, balance, COIN_50_INDEX);
        showDetailOfBalance(Coin.COIN_10, balance, COIN_10_INDEX);
    }

    private static void showDetailOfBalance(Coin coin, int[] balance, int index) {
        if (balance[index] > 0) {
            print(String.format(SHOW_USER_BALANCE, coin.getCoin(), balance[index]));
        }
    }

    public static void askPurchaseDrinkType() {
        print(ASK_PURCHASE_DRINK_TYPE);
    }

    private static void print(String message){
        System.out.println(message);
    }

    public static void println() {
        System.out.println(System.lineSeparator());
    }

    public static void errorMessage(String errorMessage) {
        System.out.println(ERROR_TAG + errorMessage);
    }
}
