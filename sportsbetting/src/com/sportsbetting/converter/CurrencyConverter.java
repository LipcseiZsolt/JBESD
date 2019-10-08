/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsbetting.converter;

import com.sportsbetting.domain.Currency;

/**
 *
 * @author Lipcsei Zsolt
 */
public class CurrencyConverter {
    public static Currency StringToCurrency(String str){
        str = str.toUpperCase();
        switch(str){
            case("HUF"):
                return Currency.HUF;
            case("EUR"):
                return Currency.EUR;
            case("USD"):
                return Currency.USD;
            default:
                throw new IllegalArgumentException("Not supported currency..");
        }
    }
}
