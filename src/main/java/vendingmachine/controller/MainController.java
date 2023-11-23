package vendingmachine.controller;

import vendingmachine.model.VendingMachine;
import vendingmachine.model.coin.Coins;
import vendingmachine.model.drink.Drinks;
import vendingmachine.model.user.UserMoney;
import vendingmachine.view.OutputView;

import static vendingmachine.model.coin.RandomCoins.makeRandomCoins;
import static vendingmachine.view.InputView.readDrinks;
import static vendingmachine.view.InputView.readMoney;
import static vendingmachine.view.InputView.readPurchase;
import static vendingmachine.view.OutputView.askDrinkFromUsers;
import static vendingmachine.view.OutputView.askMachineTotalMoney;
import static vendingmachine.view.OutputView.askPurchaseDrinkType;
import static vendingmachine.view.OutputView.askUserInputMoney;
import static vendingmachine.view.OutputView.printVendingMachineCoins;
import static vendingmachine.view.OutputView.showBalance;
import static vendingmachine.view.OutputView.showUserTotalBalance;

public class MainController {
//    VendingMachineController vendingMachineController = new VendingMachineController();

    public void run(){
        Coins coins = askTotalMoney();
        showCoins(coins);
        Drinks drinks = askDrinks();
        VendingMachine vendingMachine = new VendingMachine(coins, drinks);
        UserMoney userMoney = askInputAmount();

        makePurchase(vendingMachine, userMoney);

        showTotalBalance(vendingMachine, userMoney);
    }

    /**
     * 자판기가 보유하고 있는 금액을 입력 받는 메서드
     */
    private Coins askTotalMoney(){
        while (true){
            try {
                askMachineTotalMoney();
                return new Coins(makeRandomCoins(readMoney()));
            } catch (IllegalArgumentException exception) {
                OutputView.errorMessage(exception.getMessage());
            }
        }
    }

    /**
     * 자판기가 가지는 동전 리스트를 출력 하는 메서드
     */
    private void showCoins(Coins coins) {
        printVendingMachineCoins(coins.coinsCount());
    }

    /**
     * 자판기가 보유하고 있는 음료를 입력 받는 메서드
     */
    private Drinks askDrinks() {
        while (true) {
            try {
                askDrinkFromUsers();
                return new Drinks(readDrinks());
            } catch (IllegalArgumentException exception) {
                OutputView.errorMessage(exception.getMessage());
            }
        }
    }

    /**
     * 사용자가 자판기에 넣는 금액을 입력 받는 메서드
     */
    private UserMoney askInputAmount() {
        while (true) {
            try {
                askUserInputMoney();
                return new UserMoney(readMoney());
            } catch (IllegalArgumentException exception) {
                OutputView.errorMessage(exception.getMessage());
            }
        }
    }

    /**
     * 사용자가 음료를 구매하고 그에 따른 잔액을 출력하는 메서드
     */
    private void makePurchase(VendingMachine vendingMachine, UserMoney userMoney) {
        while (vendingMachine.hasMoneyMoreThenMinimumPrice(userMoney)) {
            showBalance(userMoney.getBalance());
            String purchaseDrinkType = askPurchase();
            userMoney.purchaseDrink(vendingMachine.getPrice(purchaseDrinkType));
        }
    }

    /**
     * 사용자가 구매하는 음료 종류를 입력 받는 메서드
     */
    private String askPurchase() {
        while (true) {
            try {
                askPurchaseDrinkType();
                return readPurchase();
            } catch (IllegalArgumentException exception) {
                OutputView.errorMessage(exception.getMessage());
            }
        }
    }

    /**
     * 자판기가 사용자에게 주는 거스름돈을 출력하는 메서드
     */
    private void showTotalBalance(VendingMachine vendingMachine, UserMoney userMoney) {
        showBalance(userMoney.getBalance());

        showUserTotalBalance(vendingMachine.getBalance(userMoney.getBalance()));
    }
}
