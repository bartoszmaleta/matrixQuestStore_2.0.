package com.company.service;

import com.company.dao.TransactionDao;
import com.company.dao.TransactionDaoDb;
import com.company.models.statistics.TransactionCountAndTotalSumByUser;
import com.company.view.StatisticsView;

import java.util.List;

public class TransactionsService {
    private final TransactionDao transactionDao;

    public TransactionsService() {
        this.transactionDao = new TransactionDaoDb();
    }

    public void displayTransactionsCountsAndTotalSumByUser() {
        List<TransactionCountAndTotalSumByUser> stats = transactionDao.getTransactionsCountAndTotalSumByUser();
        StatisticsView.countAndTotalByUser(stats);
    }

}
