package com.birichani.code;

import java.util.List;

/**
 * Declare public API for Vending Machine
 * @author Birichani
 */


public interface FriesVendingMachine {
   long selectItemAndGetPrice(Item item);
     void insertCoin(Coin coin);
     List<Coin> refund();
     Bucket<Item, List<Coin>> collectItemAndChange();
     void reset();


}
