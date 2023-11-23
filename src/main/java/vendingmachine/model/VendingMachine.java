package vendingmachine.model;

import vendingmachine.model.coin.Coin;
import vendingmachine.model.coin.Coins;
import vendingmachine.model.drink.Drinks;
import vendingmachine.model.user.UserMoney;

import java.util.List;

public class VendingMachine {
    private Coins moneyBox;
    private Drinks drinks;
    int[] toGiveBalance = {0, 0, 0, 0};

    public VendingMachine(Coins moneyBox, Drinks drinks) {
        this.moneyBox = moneyBox;
        this.drinks = drinks;
    }

    public List<Integer> showCoinBox() {
        return moneyBox.coinsCount();
    }

    public boolean hasMoneyMoreThenMinimumPrice(UserMoney userMoney) {
        return userMoney.getBalance() >= drinks.cheapestDrink();
    }

    public int getPrice(String purchaseDrinkType) {
        return drinks.getPriceFindByName(purchaseDrinkType);
    }

    public int[] getBalance(int balance) {
        balance = makeCoin(balance, Coin.COIN_500, 0);
        balance = makeCoin(balance, Coin.COIN_100, 1);
        balance = makeCoin(balance, Coin.COIN_50, 2);
        makeCoin(balance, Coin.COIN_10, 3);
        return moneyBox.getToGiveBalance();
    }

    private int makeCoin(int balance, Coin coin, int index) {
        if (moneyBox.isGiveBalance(balance, coin)) {
            balance = moneyBox.makeCoinBalance(balance, coin, index);
        }
        return balance;
    }
}
