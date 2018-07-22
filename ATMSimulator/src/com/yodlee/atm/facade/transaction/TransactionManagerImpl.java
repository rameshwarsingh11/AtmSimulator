package com.yodlee.atm.facade.transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.yodlee.atm.Transaction;
import com.yodlee.atm.facade.currency.CurrencyManagerImpl;

/**
 * Class TransactionManagerImpl to provide functionalities for processing
 * deposit and withdraw requests made by customer.
 * 
 * @author Rameshwar Singh
 */
public class TransactionManagerImpl implements TransactionManager {
	private static TransactionManagerImpl transactionManagerImplInstance = null;
	private static float totalCredit = 0.0f;
	private static float totalDebit = 0.0f;
	private static final Logger LOGGER = Logger.getLogger(TransactionManagerImpl.class.getName());

	private TransactionManagerImpl() {
	}

	/**
	 * method to process a deposit request made by customer.
	 * 
	 * @param scanner
	 *            Scanner
	 */
	@Override
	public float processDepositRequest(Scanner scanner, float balance) {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Execution started for method ::::: " + TransactionManagerImpl.class + "processDepositRequest");
		System.out.println("Enter ccy to deposit terminated by. e.g. 10 20 50 .");
		System.out.println("");
		String ccyNotes = scanner.nextLine();
		while (!(ccyNotes.equals("."))) {
			ccyNotes = scanner.nextLine();
			if (ccyNotes.equals("."))
				break;
			int ccyNotesValue = Integer.parseInt(ccyNotes);
			if (ccyNotesValue == 10 || ccyNotesValue == 20 || ccyNotesValue == 50) {
				System.out.println("Accpeted");
				balance += ccyNotesValue;
				totalCredit += ccyNotesValue;
				continue;
			} else {
				System.out.println("Invalid denomination");
				continue;
			}
		}
		Transaction.getTransactionSet().add(new Transaction("Credit", new Date(), totalCredit, balance));
		totalCredit = 0.0f;
		LOGGER.info("Execution ended for method ::::: " + TransactionManagerImpl.class + "processDepositRequest");
		return balance;
	}

	/**
	 * method to process withdraw request made by customer.
	 * 
	 * @param scanner
	 *            Scanner
	 */
	@Override
	public float processWithdrawRequest(Scanner scanner, float balance) {
		LOGGER.info("Execution started for method ::::: " + TransactionManagerImpl.class + "processWithdrawRequest");
		System.out.println("Enter amount to withdraw :");
		int amount = scanner.nextInt();
		Map<Integer, Integer> currencyMap = new HashMap<Integer, Integer>();
		if (amount != 0 && amount <= balance) {
			currencyMap = CurrencyManagerImpl.getInstance().calculateDispenseCurrencyNotes(amount, currencyMap);
			for (int currency : currencyMap.keySet()) {
				for (int i = 0; i < currencyMap.get(currency); i++) {
					System.out.println("Dispensing : " + currency + "$");
				}
			}
			balance -= amount;
			totalDebit += amount;
		} else {
			System.out.println("Please enter currect amount");
		}
		Transaction.getTransactionSet().add(new Transaction("Debit", new Date(), totalDebit, balance));
		totalDebit = 0.0f;
		LOGGER.info("Execution ended for method ::::: " + TransactionManagerImpl.class + "processWithdrawRequest");
		return balance;
	}

	public static TransactionManagerImpl getInstance() {
		if (null == transactionManagerImplInstance) {
			transactionManagerImplInstance = new TransactionManagerImpl();
		}
		return transactionManagerImplInstance;
	}
}
