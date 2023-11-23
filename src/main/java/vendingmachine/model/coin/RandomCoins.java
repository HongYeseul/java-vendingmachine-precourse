package vendingmachine.model.coin;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashMap;

public class RandomCoins {
    public static HashMap<Coin, Integer> makeRandomCoins(int totalMoney){
        HashMap<Coin, Integer> moneyBox = new HashMap<>();
        while(totalMoney > 0){
            int coin = Randoms.pickNumberInList(Coin.coinTypes());
            Coin coinType = Coin.valueOf("COIN_"+coin);
            if (totalMoney >= coin) {
                if (moneyBox.containsKey(coinType)) {
                    moneyBox.put(coinType, moneyBox.get(coinType)+1);
                }
                if (!moneyBox.containsKey(coinType)) {
                    moneyBox.put(coinType, 1);
                }
                totalMoney -= coin;
            }
        }
        return moneyBox;
    }
}
