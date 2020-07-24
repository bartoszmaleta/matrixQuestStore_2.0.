package com.company.service;

import com.company.dao.ConnectionFactory;
import com.company.dao.TransactionDao;
import com.company.dao.TransactionDaoDb;
import com.company.dao.UserDaoDb;
import com.company.model.Transaction;
import com.company.model.user.Student;
import com.company.model.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TransactionsServiceTest {
    private static TransactionsService transactionsService;
    private static TransactionDaoDb transactionDaoDb;

    @BeforeAll
    static void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc"
                , "org.postgresql.Driver"
                , "pirqathgcgzhbg"
                , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
        );
        transactionDaoDb = Mockito.mock(TransactionDaoDb.class);
        transactionsService = new TransactionsService(connectionFactory);
    }

    @Test
    public void should_returnTransactions_when_studentIdIsProvided() {
        User user = new Student()
                .setId(1);

        List<Transaction> transactionsExpected = new ArrayList<>();
        Transaction transaction = new Transaction(1, "Cristiano Ronaldo", "I was blind now I see", 20, Timestamp.valueOf("2018-11-12 01:02:03.123456789"));
        Transaction transaction2 = new Transaction(2, "Cristiano Ronaldo", "Martial-Art Workshop", 44, Timestamp.valueOf("2018-11-11 01:02:03.123456789"));
        transactionsExpected.add(transaction);
        transactionsExpected.add(transaction2);

        Mockito.when(transactionDaoDb
                .getMyTransactionsById(user.getId()))
                .thenReturn(transactionsExpected);

        assertEquals(transactionDaoDb
                        .getMyTransactionsById(user.getId())
                , transactionsService.getTransactionsByStudentId(user.getId()));
    }

}