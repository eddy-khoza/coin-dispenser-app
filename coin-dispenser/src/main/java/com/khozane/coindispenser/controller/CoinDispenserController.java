package com.khozane.coindispenser.controller;

import com.khozane.coindispenser.core.InternalServerException;
import com.khozane.coindispenser.core.InvalidInputException;
import com.khozane.coindispenser.service.CoinDispenserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@Controller
public class CoinDispenserController {

    @Autowired
    private CoinDispenserService coinDispenserService;

    @RequestMapping(value = "/home")
    public static String welcome(){
        return "home";
    }

    //create a post method to accept amount to change and denominators of coins
    //pass Model as parameter to add the attribute being returned (combination)
    @RequestMapping(value = "/dispanseCoin")
    public String dispanseCoin(@RequestParam @Min(1) int amount, @RequestParam @Min(1)int[] coins, Model model) throws Exception {
        String answer = "";

        //check if input amount and coins length is greater than 0
        try {

            if (amount > 0 || coins.length > 0) {

                int combinedCoin = coinDispenserService.coinsDispensed(coins, amount);

                //check if a combination is found
                if(combinedCoin < amount && combinedCoin > 0 ){
                    answer = "The minimum combination of coins  :" + combinedCoin;

                }else if( combinedCoin < 0){
                    combinedCoin = 0;//if no combination of coins found return 0
                    answer = "No combination  found : "+combinedCoin;
                }
                model.addAttribute("coin", answer);
            }
            return "home";

        }catch(InvalidInputException e){

            throw new Exception(e.getMessage());

        }catch(InternalServerException e){

            throw new Exception(e.getMessage());
        }
    }
}
