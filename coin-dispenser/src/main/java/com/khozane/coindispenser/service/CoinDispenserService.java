package com.khozane.coindispenser.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CoinDispenserService {
    public  int coinsDispensed(int coins[], int amountValue) {
        //Sort the array before finding the combination
        Arrays.sort(coins);

        //base case
        if (amountValue == 0)
            return 0;

        //initialize results
        int result = Integer.MAX_VALUE;

        //loop through every coin that is smaller than amount value
        for (int x = 0; x < coins.length ; x++) {
            if(amountValue >= coins[x]){
                int change = coinsDispensed(coins, amountValue-coins[x]);

                //check for int result to avoid overflow and see if result can be minimised
                if(change != result && change +1 < result){
                    result = change + 1;
                }
            }
        }
        return result;
    }
}
