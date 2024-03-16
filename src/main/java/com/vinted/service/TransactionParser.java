package com.vinted.service;

import com.vinted.entity.Provider;
import com.vinted.entity.Size;
import com.vinted.entity.Transaction;

import java.time.LocalDate;

/**
 * This class trying to parse transaction from string line
 */
public final class TransactionParser {

    public Transaction parseTransaction(String line) {
        String[] words = line.split(" ");
        if (words.length != 3) {
            throw new IllegalArgumentException("Not valid data");
        }
        LocalDate date = LocalDate.parse(words[0]);
        Size size = Size.valueOf(words[1]);
        Provider provider = Provider.valueOf(words[2]);
        return new Transaction(date, size, provider);
    }
}
