package com.yodlee.atm.facade.reporting;

import java.util.TreeSet;

import com.yodlee.atm.Transaction;

/**
 * Interface ReportingManager to provide methods to process mini statements and
 * displaying balance requests.
 * 
 * @author Rameshwar Singh
 *
 */
public interface ReportingManager {
	void processMiniStatementRequest(TreeSet<Transaction> transactionSet);

	void processDisplayBalanceRequest(float balance);
}
