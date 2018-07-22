package com.yodlee.atm;

import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yodlee.atm.facade.AtmManagerImpl;
import com.yodlee.atm.facade.currency.CurrencyManagerImpl;
import com.yodlee.atm.facade.util.MenuOptions;

/**
 * Class AtmSimulator to provide Deposit, withdraw, mini statements facility of
 * an ATM machine.
 * 
 * @author Rameshwar Singh
 */
public class AtmSimulator {
	private static TreeSet<Transaction> transactionSet = new TreeSet<>();
	private static final Stack<Integer> availableCurrency = new Stack<>();
	private static final Logger LOGGER = Logger.getLogger(AtmSimulator.class.getName());

	/**
	 * bootstrap main method.
	 * 
	 * @param args
	 *            String[]
	 * 
	 */
	public static void main(String[] args) {
		startMenuOptions();
	}

	/**
	 * entry point method displaying options available to customer.
	 */
	private static void startMenuOptions() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("\nExecution started for method ::::::" + AtmSimulator.class + "startMenuOptions.");
		try (Scanner scanner = new Scanner(System.in)) {
			CurrencyManagerImpl.getInstance().loadAtmMachine(availableCurrency);
			MenuOptions.printMenuOptions();
			int option = scanner.nextInt();
			AtmManagerImpl.getInstance().processOptions(option, scanner);
			LOGGER.info("\nExecution ended for method ::::::" + AtmSimulator.class + "startMenuOptions.");
		} catch (Exception e) {
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe("\nGot an exception while executing ::::::" + AtmSimulator.class + "startMenuOptions");
			e.printStackTrace();
		}
	}

	/**
	 * @return the transactionSet
	 */
	public static TreeSet<Transaction> getTransactionSet() {
		return transactionSet;
	}

	/**
	 * @param transactionSet
	 *            the transactionSet to set
	 */
	public static void setTransactionSet(TreeSet<Transaction> transactionSet) {
		AtmSimulator.transactionSet = transactionSet;
	}
}
