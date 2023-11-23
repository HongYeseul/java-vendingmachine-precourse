package vendingmachine.model.coin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Coins {
    private HashMap<Coin, Integer> coins;
    int[] toGiveBalance = {0, 0, 0, 0};

    public Coins(HashMap<Coin, Integer> coins) {
        this.coins = coins;
    }

    public List<Integer> coinsCount() {
        List<Integer> count = new ArrayList<>();
        count.add(getCoinCount("COIN_500"));
        count.add(getCoinCount("COIN_100"));
        count.add(getCoinCount("COIN_50"));
        count.add(getCoinCount("COIN_10"));
        return count;
    }

    private Integer getCoinCount(String coinType) {
        Integer coinCount = coins.get(Coin.valueOf(coinType));
        if (coinCount == null) {
            return 0;
        }
        return coinCount;
    }

    public boolean isGiveBalance(int balance, Coin coin) {
        if(coins.get(coin) == null)
            return false;
        return coins.get(coin) > 0 && ((balance - coin.getCoin()) > 0);
    }

    public int makeCoinBalance(int balance, Coin coin, int index) {
        int machineCount = coins.get(coin);
        int needCount = makeCountBalance(machineCount, balance, coin);
        coins.remove(coin);
        coins.put(coin, machineCount - needCount);
        toGiveBalance[index] = needCount;
        balance -= coin.getCoin();
        return balance;
    }

    public int makeCountBalance(int machineCount, int balance, Coin coin) {
        int needCount = balance / coin.getCoin();

        if (needCount > machineCount) {
            needCount = machineCount;
        }
        return needCount;
    }

    public int[] getToGiveBalance() {
        return toGiveBalance;
    }
}
