package com.vinted.service;

import com.vinted.entity.*;

import java.math.BigDecimal;
import java.time.Month;

/**
 * This class counts discounts for transaction
 */
public final class DiscountCalculator {
    /**
     * Max discount that can be reached in a month
     */
    private static final double DISCOUNT_LIMIT = 10.;

    /**
     * Shipment price-book
     */
    private final PriceBook priceBook = new PriceBook();

    /**
     * Store how many LP L transaction there were in a month
     */
    private int counter = 0;
    private Month previousMonth = null;
    /**
     * Store how much currency you have left for discount in a month
     */
    private BigDecimal discountRemainder = BigDecimal.valueOf(DISCOUNT_LIMIT);

    /**
     * Counts discount according to the rules from the task
     *
     * @param transaction without discount
     * @return transaction with counted discount
     */
    public TransactionWithDiscount countDiscount(Transaction transaction) {
        clearDataForNextMonth(transaction);
        return switch (transaction.size()) {
            case S -> countDiscountForSizeS(transaction);
            case M -> countDiscountForSizeM(transaction);
            case L -> countDiscountForSizeL(transaction);
        };
    }

    private void clearDataForNextMonth(Transaction transaction) {
        Month currentMonth = transaction.date().getMonth();
        if (previousMonth != currentMonth) {
            previousMonth = currentMonth;
            discountRemainder = BigDecimal.valueOf(DISCOUNT_LIMIT);
            counter = 0;
        }
    }

    private TransactionWithDiscount countDiscountForSizeS(Transaction transaction) {
        BigDecimal currentPrice = priceBook.getPriceFor(transaction.provider(), transaction.size());
        BigDecimal difference = currentPrice.subtract(priceBook.getLowestPriceFor(Size.S));
        BigDecimal discount = difference.min(discountRemainder);
        currentPrice = currentPrice.subtract(discount);
        discountRemainder = discountRemainder.subtract(discount);
        return new TransactionWithDiscount(transaction, currentPrice, discount);
    }

    private TransactionWithDiscount countDiscountForSizeM(Transaction transaction) {
        BigDecimal currentPrice = priceBook.getPriceFor(transaction.provider(), transaction.size());
        BigDecimal discount = BigDecimal.ZERO;
        discountRemainder = discountRemainder.subtract(discount);
        return new TransactionWithDiscount(transaction, currentPrice, discount);
    }

    private TransactionWithDiscount countDiscountForSizeL(Transaction transaction) {
        BigDecimal currentPrice = priceBook.getPriceFor(transaction.provider(), transaction.size());
        BigDecimal discount = BigDecimal.ZERO;
        if (transaction.provider() == Provider.LP) {
            ++counter;
            if (counter == 3) {
                discount = currentPrice.min(discountRemainder);
                currentPrice = BigDecimal.ZERO;
            }
        }
        discountRemainder = discountRemainder.subtract(discount);
        return new TransactionWithDiscount(transaction, currentPrice, discount);
    }

}
