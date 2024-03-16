package com.vinted.service;

import com.vinted.entity.TransactionWithDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class prints transaction with price and discount to stdout
 */
public final class TransactionPrinter {

    private static final int SCALE = 2;

    public void printTransactionWithDiscount(TransactionWithDiscount transactionWithDiscount) {
        BigDecimal price = transactionWithDiscount.price().setScale(SCALE, RoundingMode.HALF_UP);
        BigDecimal discount = transactionWithDiscount.discount().setScale(SCALE, RoundingMode.HALF_UP);
        if (BigDecimal.ZERO.compareTo(discount) == 0) {
            System.out.println(
                    transactionWithDiscount.transaction().date() + " " +
                    transactionWithDiscount.transaction().size() + " " +
                    transactionWithDiscount.transaction().provider() + " " +
                    price + " -"
            );
        } else {
            System.out.println(
                    transactionWithDiscount.transaction().date() + " " +
                    transactionWithDiscount.transaction().size() + " " +
                    transactionWithDiscount.transaction().provider() + " " +
                    price + " " +
                    discount
            );
        }
    }
}
