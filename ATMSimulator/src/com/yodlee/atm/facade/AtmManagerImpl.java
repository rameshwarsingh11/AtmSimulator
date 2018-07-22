package com.yodlee.atm.facade;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yodlee.atm.Transaction;
import com.yodlee.atm.facade.reporting.ReportingManagerImpl;
import com.yodlee.atm.facade.transaction.TransactionManagerImpl;
import com.yodlee.atm.facade.util.MenuOptions;

/**
 * Class AtmManagerImpl to process all the options selected by customer.
 * 
 * @author Rameshwar Singh
 *
 */
public class AtmManagerImpl implements AtmManager {
	private static float balance = 0.0f;
	private static AtmManagerImpl atmManagerImplInstance = null;
	private static final Logger LOGGER = Logger.getLogger(AtmManagerImpl.class.getName());

	public AtmManagerImpl() {
	}

	/**
	 * method to process various options as per requested by user.
	 * 
	 * @param option
	 *            int
	 * @param scanner
	 *            Scanner
	 */
	@Override
	public void processOptions(int option, Scanner scanner) {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Execution started for method ::::: " + AtmManagerImpl.class + "processOptions");
		switch (option) {
		case 1:
			balance = TransactionManagerImpl.getInstance().processDepositRequest(scanner, balance);
			break;
		case 2:
			balance = TransactionManagerImpl.getInstance().processWithdrawRequest(scanner, balance);
			break;
		case 3:
			ReportingManagerImpl.getInstance().processDisplayBalanceRequest(balance);
			break;
		case 4:
			ReportingManagerImpl.getInstance().processMiniStatementRequest(Transaction.getTransactionSet());
			break;
		case 5:
			System.out.println("Thanks for banking with us today. Have a nice day !");
			System.exit(0);
		default:
			processOptions(option, scanner);
		}
		System.out.println("\n\n");
		MenuOptions.printMenuOptions();
		option = scanner.nextInt();
		processOptions(option, scanner);
		LOGGER.info("Execution ended for method ::::: " + AtmManagerImpl.class + "processOptions");
	}

	public static AtmManagerImpl getInstance() {
		if (null == atmManagerImplInstance) {
			atmManagerImplInstance = new AtmManagerImpl();
		}
		return atmManagerImplInstance;
	}
}
