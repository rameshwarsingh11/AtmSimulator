package com.yodlee.atm.facade.reporting;

import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yodlee.atm.Transaction;

/**
 * Class ReportingManagerImpl to provide methods to process mini statements and
 * displaying balance requests.
 * 
 * @author Rameshwar Singh
 *
 */
public class ReportingManagerImpl implements ReportingManager {
	private static ReportingManagerImpl reportingManagerImplInstance = null;
	private static final Logger LOGGER = Logger.getLogger(ReportingManagerImpl.class.getName());

	/**
	 * method to process mini statement display for the customer. It will show Debit
	 * and Credit done on the account.
	 * 
	 * @param transactionSet
	 *            TreeSet<Transaction>
	 */
	@Override
	public void processMiniStatementRequest(TreeSet<Transaction> transactionSet) {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Execution started for method ::::: " + ReportingManagerImpl.class + "processMiniStatementRequest");
		System.out.println(
				"--------–----------------------------------------------------------------------------------------");
		System.out.println("Date Time                      Transaction        Amount                Closing Balance");
		System.out.println(
				"--------–----------------------------------------------------------------------------------------");
		for (Transaction transaction : transactionSet) {
			System.out.println(transaction.getTimeStamp() + "          " + transaction.getTransactionType() + "        "
					+ transaction.getTransactionAmount() + "             " + transaction.getBalanceAfterTransaction());
		}
		System.out.println(
				"-------------------–-----------------------------------------------------------------------------");
		LOGGER.info("Execution ended for method ::::: " + ReportingManagerImpl.class + "processMiniStatementRequest");
	}

	/**
	 * method to display available balance in the account.
	 * 
	 * @param balance
	 *            float
	 * @return String balance amount
	 */
	@Override
	public void processDisplayBalanceRequest(float balance) {
		LOGGER.info(
				"Execution started for method ::::: " + ReportingManagerImpl.class + "processDisplayBalanceRequest");
		System.out.println("Available balance : " + balance + "$");
		LOGGER.info("Execution ended for method ::::: " + ReportingManagerImpl.class + "processDisplayBalanceRequest");
	}

	public static ReportingManagerImpl getInstance() {
		if (null == reportingManagerImplInstance) {
			reportingManagerImplInstance = new ReportingManagerImpl();
		}
		return reportingManagerImplInstance;
	}
}
