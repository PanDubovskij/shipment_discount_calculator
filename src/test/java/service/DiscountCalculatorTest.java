package service;

import com.vinted.entity.Provider;
import com.vinted.entity.Size;
import com.vinted.entity.Transaction;
import com.vinted.entity.TransactionWithDiscount;
import com.vinted.service.DiscountCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCalculatorTest {

    @Test
    void countDiscountCaseS() {
        DiscountCalculator calculator = new DiscountCalculator();

        Transaction transactionMR = new Transaction(LocalDate.parse("2024-02-01"), Size.S, Provider.MR);
        TransactionWithDiscount transactionWithDiscountMR = calculator.countDiscount(transactionMR);
        assertEquals(BigDecimal.valueOf(0.5), transactionWithDiscountMR.discount());

        Transaction transactionLP = new Transaction(LocalDate.parse("2024-02-02"), Size.S, Provider.LP);
        TransactionWithDiscount transactionWithDiscountLP = calculator.countDiscount(transactionLP);
        assertEquals(BigDecimal.valueOf(0.0), transactionWithDiscountLP.discount());
    }

    @Test
    void countDiscountCaseM() {
        Transaction transaction1 = new Transaction(LocalDate.parse("2024-02-01"), Size.M, Provider.LP);
        DiscountCalculator calculator = new DiscountCalculator();
        TransactionWithDiscount transactionWithDiscount1 = calculator.countDiscount(transaction1);
        assertEquals(BigDecimal.ZERO, transactionWithDiscount1.discount());
    }

    @Test
    void countDiscountCaseL() {
        Transaction transaction1 = new Transaction(LocalDate.parse("2024-02-01"), Size.L, Provider.LP);
        Transaction transaction2 = new Transaction(LocalDate.parse("2024-02-02"), Size.L, Provider.LP);
        Transaction transaction3 = new Transaction(LocalDate.parse("2024-02-03"), Size.L, Provider.LP);
        Transaction transaction4 = new Transaction(LocalDate.parse("2024-02-04"), Size.L, Provider.LP);

        DiscountCalculator calculator = new DiscountCalculator();
        TransactionWithDiscount transactionWithDiscount1 = calculator.countDiscount(transaction1);
        assertEquals(BigDecimal.ZERO, transactionWithDiscount1.discount());

        TransactionWithDiscount transactionWithDiscount2 = calculator.countDiscount(transaction2);
        assertEquals(BigDecimal.ZERO, transactionWithDiscount2.discount());

        TransactionWithDiscount transactionWithDiscount3 = calculator.countDiscount(transaction3);
        assertEquals(BigDecimal.valueOf(6.9), transactionWithDiscount3.discount());

        TransactionWithDiscount transactionWithDiscount4 = calculator.countDiscount(transaction4);
        assertEquals(BigDecimal.ZERO, transactionWithDiscount4.discount());
    }

    @Test
    void countDiscountWithTenLimit() {
        Transaction transaction1 = new Transaction(LocalDate.parse("2024-02-01"), Size.L, Provider.LP);
        Transaction transaction2 = new Transaction(LocalDate.parse("2024-02-02"), Size.L, Provider.LP);
        Transaction transaction3 = new Transaction(LocalDate.parse("2024-02-03"), Size.L, Provider.LP);
        Transaction transaction4 = new Transaction(LocalDate.parse("2024-02-04"), Size.S, Provider.MR);
        Transaction transaction5 = new Transaction(LocalDate.parse("2024-02-05"), Size.S, Provider.MR);
        Transaction transaction6 = new Transaction(LocalDate.parse("2024-02-06"), Size.S, Provider.MR);
        Transaction transaction7 = new Transaction(LocalDate.parse("2024-02-07"), Size.S, Provider.MR);
        Transaction transaction8 = new Transaction(LocalDate.parse("2024-02-08"), Size.S, Provider.MR);
        Transaction transaction9 = new Transaction(LocalDate.parse("2024-02-09"), Size.S, Provider.MR);
        Transaction transaction10 = new Transaction(LocalDate.parse("2024-02-10"), Size.S, Provider.MR);
        Transaction transaction11 = new Transaction(LocalDate.parse("2024-02-11"), Size.S, Provider.MR);

        DiscountCalculator calculator = new DiscountCalculator();

        TransactionWithDiscount transactionWithDiscount1 = calculator.countDiscount(transaction1);
        assertEquals(BigDecimal.ZERO, transactionWithDiscount1.discount());

        TransactionWithDiscount transactionWithDiscount2 = calculator.countDiscount(transaction2);
        assertEquals(BigDecimal.ZERO, transactionWithDiscount2.discount());

        TransactionWithDiscount transactionWithDiscount3 = calculator.countDiscount(transaction3);
        assertEquals(BigDecimal.valueOf(6.9), transactionWithDiscount3.discount());

        TransactionWithDiscount transactionWithDiscount4 = calculator.countDiscount(transaction4);
        assertEquals(BigDecimal.valueOf(0.5), transactionWithDiscount4.discount());

        TransactionWithDiscount transactionWithDiscount5 = calculator.countDiscount(transaction5);
        assertEquals(BigDecimal.valueOf(0.5), transactionWithDiscount5.discount());

        TransactionWithDiscount transactionWithDiscount6 = calculator.countDiscount(transaction6);
        assertEquals(BigDecimal.valueOf(0.5), transactionWithDiscount6.discount());

        TransactionWithDiscount transactionWithDiscount7 = calculator.countDiscount(transaction7);
        assertEquals(BigDecimal.valueOf(0.5), transactionWithDiscount7.discount());

        TransactionWithDiscount transactionWithDiscount8 = calculator.countDiscount(transaction8);
        assertEquals(BigDecimal.valueOf(0.5), transactionWithDiscount8.discount());

        TransactionWithDiscount transactionWithDiscount9 = calculator.countDiscount(transaction9);
        assertEquals(BigDecimal.valueOf(0.5), transactionWithDiscount9.discount());

        TransactionWithDiscount transactionWithDiscount10 = calculator.countDiscount(transaction10);
        assertEquals(BigDecimal.valueOf(0.1), transactionWithDiscount10.discount());

        TransactionWithDiscount transactionWithDiscount11 = calculator.countDiscount(transaction11);
        assertEquals(BigDecimal.valueOf(0.0), transactionWithDiscount11.discount());
    }
}