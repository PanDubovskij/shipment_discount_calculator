package com.vinted;


import com.vinted.service.DiscountCalculator;
import com.vinted.service.TransactionParser;
import com.vinted.entity.Transaction;
import com.vinted.entity.TransactionWithDiscount;
import com.vinted.service.TransactionPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * App entrypoint
 */
public final class Main {

    public static void main(String[] args) throws FileNotFoundException {

        var scanner = new Scanner(new File(("input.txt")));
        var parser = new TransactionParser();
        var calculator = new DiscountCalculator();
        var printer = new TransactionPrinter();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Transaction transaction;
            try {
                transaction = parser.parseTransaction(line);
            } catch (DateTimeParseException | IllegalArgumentException ignored) {
                System.out.println(line + " Ignored");
                continue;
            }
            TransactionWithDiscount transactionWithDiscount = calculator.countDiscount(transaction);
            printer.printTransactionWithDiscount(transactionWithDiscount);
        }
        scanner.close();
    }
}
