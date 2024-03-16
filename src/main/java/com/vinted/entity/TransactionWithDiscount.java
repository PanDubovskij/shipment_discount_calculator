package com.vinted.entity;

import java.math.BigDecimal;

/**
 * This class like a compliment for Transaction after counting the discount and result price
 *
 * @param transaction Good old Transaction a.k.a. transaction before counting the discount
 * @param price Price for shipment considering the discount
 * @param discount discount for the shipment
 */
public record TransactionWithDiscount(Transaction transaction, BigDecimal price, BigDecimal discount) {
}
