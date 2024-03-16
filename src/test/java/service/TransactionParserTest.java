package service;

import com.vinted.entity.Transaction;
import com.vinted.service.TransactionParser;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionParserTest {

    @Test
    void parseTransactionCorrect() {
        String correctLine = "2015-02-03 L LP";
        TransactionParser parser = new TransactionParser();
        assertDoesNotThrow(() -> {
            Transaction transaction = parser.parseTransaction(correctLine);
        });
    }

    @Test
    void parseTransactionWrongDate() {
        String line = "2015-02-29 L LP";
        TransactionParser parser = new TransactionParser();
        assertThrows(DateTimeParseException.class, () -> {
            Transaction transaction = parser.parseTransaction(line);
        });
    }

    @Test
    void parseTransactionWrongSize() {
        String line = "2015-02-20 O LP";
        TransactionParser parser = new TransactionParser();
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction transaction = parser.parseTransaction(line);
        });
    }
    @Test
    void parseTransactionWrongProvider() {
        String line = "2015-02-20 L L";
        TransactionParser parser = new TransactionParser();
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction transaction = parser.parseTransaction(line);
        });
    }

    @Test
    void parseTransactionLessThenThreeWords() {
        String line = "2015-02-20 L ";
        TransactionParser parser = new TransactionParser();
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction transaction = parser.parseTransaction(line);
        });
    }

    @Test
    void parseTransactionMoreThenThreeWords() {
        String line = "2015-02-20 L L L";
        TransactionParser parser = new TransactionParser();
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction transaction = parser.parseTransaction(line);
        });
    }
}