package com.birichani.code;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FriesVendingMachineTest {
    private static FriesVendingMachine friesVendingMachine;

    @BeforeClass
    public static void setUp() {
       friesVendingMachine = VendingMachineFactory.createVendingMachine();
    }

    @AfterClass
    public static void tearDown() {
        friesVendingMachine = null;

    }
    @Test
    public void testBuyItemWithExactPrice() {
        //select item, price in cents
         long price = friesVendingMachine.selectItemAndGetPrice(Item.COKE);
        // price should be Coke's price
        assertEquals(Item.COKE.getPrice(), price);
        // 25 cents paid
        friesVendingMachine.insertCoin(Coin.QUARTER);
         Bucket<Item, List<Coin>> bucket = friesVendingMachine.collectItemAndChange();
        Item item = bucket.getFirst(); List<Coin> change = bucket.getSecond();
        // should be Coke
        assertEquals(Item.COKE, item);
        // there should not be any change
        assertTrue(change.isEmpty());
        }
        @Test
    public void testBuyItemWithMorePrice(){
        long price = friesVendingMachine.selectItemAndGetPrice(Item.SODA);
        assertEquals(Item.SODA.getPrice(), price);
        friesVendingMachine.insertCoin(Coin.QUARTER);
        friesVendingMachine.insertCoin(Coin.QUARTER);
        Bucket<Item, List<Coin>> bucket = friesVendingMachine.collectItemAndChange();
        Item item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();
        //should be Coke
         assertEquals(Item.SODA, item);
         //there should not be any change
        assertTrue(!change.isEmpty());
        //comparing change
        assertEquals(50 - Item.SODA.getPrice(), getTotal(change));
    }



    @Test(expected=SoldOutException.class)
    public void testReset(){
    FriesVendingMachine vmachine = VendingMachineFactory.createVendingMachine();
    vmachine.reset(); vmachine.selectItemAndGetPrice(Item.COKE);
}
    @Ignore
    public void testVendingMachineImpl(){
    FriesVendingMachineImpl vm = new FriesVendingMachineImpl();
}
private long getTotal(List<Coin> change){
    long total = 0; for(Coin c : change){
        total = total + c.getDenomination();
    }
    return total; }
    @Test(expected=NotSufficientChangeException.class)
    public void testNotSufficientChangeException(){
        for (int i = 0; i < 5; i++) {
          friesVendingMachine.selectItemAndGetPrice(Item.SODA);
          friesVendingMachine.insertCoin(Coin.QUARTER);
        friesVendingMachine.insertCoin(Coin.QUARTER);
            friesVendingMachine.collectItemAndChange();
            friesVendingMachine.selectItemAndGetPrice(Item.PEPSI);
            friesVendingMachine.insertCoin(Coin.QUARTER);
            friesVendingMachine.insertCoin(Coin.QUARTER);
            friesVendingMachine.collectItemAndChange();
        }
    }
    @Test public void testRefund(){
        long price = friesVendingMachine.selectItemAndGetPrice(Item.PEPSI);
        assertEquals(Item.PEPSI.getPrice(), price);
        friesVendingMachine.insertCoin(Coin.DIME);
        friesVendingMachine.insertCoin(Coin.NICKLE);
        friesVendingMachine.insertCoin(Coin.PENNY);
        friesVendingMachine.insertCoin(Coin.QUARTER);
        assertEquals(41, getTotal(friesVendingMachine.refund()));
    }
}
