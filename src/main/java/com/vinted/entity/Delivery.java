package com.vinted.entity;

import java.math.BigDecimal;

/**
 *  This class represent an entity of PriceBook
 *
 * @param provider Provider of the delivery
 * @param size Size of the package
 * @param price Shipping price
 */
public record Delivery(Provider provider, Size size, BigDecimal price) {
}
