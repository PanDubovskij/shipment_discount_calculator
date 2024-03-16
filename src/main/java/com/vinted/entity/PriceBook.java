package com.vinted.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * PriceBook represents the table of shipping prices that depend on provider and package size
 */
public final class PriceBook {
    public static final List<Delivery> deliveries = List.of(
            new Delivery(Provider.LP, Size.S, BigDecimal.valueOf(1.5)),
            new Delivery(Provider.LP, Size.M, BigDecimal.valueOf(4.9)),
            new Delivery(Provider.LP, Size.L, BigDecimal.valueOf(6.9)),
            new Delivery(Provider.MR, Size.S, BigDecimal.valueOf(2.0)),
            new Delivery(Provider.MR, Size.M, BigDecimal.valueOf(3.0)),
            new Delivery(Provider.MR, Size.L, BigDecimal.valueOf(4.0)));


    /**
     * Litterally via this method you can get price of your transaction without discount
     *
     * @param provider
     * @param size
     * @return Price for this provider and size
     */
    public BigDecimal getPriceFor(Provider provider, Size size) {
        return deliveries.stream()
                .filter(delivery -> delivery.provider() == provider)
                .filter(delivery -> delivery.size() == size)
                .findFirst()
                .get().price();
    }

    /**
     * This method finds the lowest price among providers for given package size
     *
     * @param size Size of the package
     * @return The lowest price among providers
     */
    public BigDecimal getLowestPriceFor(Size size) {
        return deliveries.stream()
                .filter(delivery -> delivery.size() == size)
                .min(Comparator.comparing(Delivery::price))
                .get().price();
    }


}
