package vendingmachine.model;

import vendingmachine.model.coin.Coin;
import vendingmachine.model.coin.Coins;
import vendingmachine.model.drink.Drinks;
import vendingmachine.model.user.UserMoney;

import java.util.List;

import static vendingmachine.model.constants.Index.COIN_100_INDEX;
import static vendingmachine.model.constants.Index.COIN_10_INDEX;
import static vendingmachine.model.constants.Index.COIN_500_INDEX;
import static vendingmachine.model.constants.Index.COIN_50_INDEX;

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
        balance = makeCoin(balance, Coin.COIN_500, COIN_500_INDEX);
        balance = makeCoin(balance, Coin.COIN_100, COIN_100_INDEX);
        balance = makeCoin(balance, Coin.COIN_50, COIN_50_INDEX);
        makeCoin(balance, Coin.COIN_10, COIN_10_INDEX);
        return moneyBox.getToGiveBalance();
    }

    private int makeCoin(int balance, Coin coin, int index) {
        if (moneyBox.isGiveBalance(balance, coin)) {
            balance = moneyBox.makeCoinBalance(balance, coin, index);
        }
        return balance;
    }
}
