package com.yodlee.atm.facade.currency;

import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class CurrencyManagerImpl to provide functionalities for dispensing currency
 * notes and loading the machine with default currency notes.
 * 
 * @author Rameshwar Singh
 *
 */
public class CurrencyManagerImpl implements CurrencyManager {
	private static final Stack<Integer> availableCurrency = new Stack<>();
	private static CurrencyManagerImpl currencyManagerImplInstance = null;
	private static final Logger LOGGER = Logger.getLogger(CurrencyManagerImpl.class.getName());

	private CurrencyManagerImpl() {
		loadAtmMachine(availableCurrency);
	}

	/**
	 * method to calculate various dispensing notes for the requested amount.
	 * 
	 * @param amount
	 *            int
	 * @param currencyMap
	 *            Map<Integer, Integer>
	 * @return Map<Integer, Integer> . example { 50=1,20=2 }
	 */
	@Override
	public Map<Integer, Integer> calculateDispenseCurrencyNotes(int amount, Map<Integer, Integer> currencyMap) {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info(
				"Execution started for method ::::: " + CurrencyManagerImpl.class + "calculateDispenseCurrencyNotes");
		boolean isValidCurrency = true;
		while (isValidCurrency) {
			int currencyNote = availableCurrency.pop();
			int currencyNoteCount = (int) (amount / currencyNote);
			currencyMap.put(currencyNote, currencyNoteCount);
			int nextCurrencyAmount = (int) (amount % currencyNote);
			if (nextCurrencyAmount > 0) {
				currencyNote = availableCurrency.pop();
			}
			amount = nextCurrencyAmount;
			if (amount == 0) {
				isValidCurrency = false;
				break;
			}
			if (amount >= currencyNote) {
				currencyNoteCount = (int) (amount / currencyNote);
				currencyMap.put(currencyNote, currencyNoteCount);
				nextCurrencyAmount = (int) (amount % currencyNote);
				amount = nextCurrencyAmount;
				isValidCurrency = true;
				break;
			}
		}
		LOGGER.info("Execution ended for method ::::: " + CurrencyManagerImpl.class + "calculateDispenseCurrencyNotes");
		return currencyMap;
	}

	/**
	 * Method to populate currency notes in ATM machine.
	 * 
	 * @param availablecurrecy
	 *            Stack<Integer>
	 */
	@Override
	public void loadAtmMachine(Stack<Integer> availablecurrecy) {
		LOGGER.info("Execution started for method ::::: " + CurrencyManagerImpl.class + "loadAtmMachine");
		availablecurrecy.push(10);
		availablecurrecy.push(20);
		availablecurrecy.push(50);
		LOGGER.info("Execution ended for method ::::: " + CurrencyManagerImpl.class + "loadAtmMachine");
	}

	/**
	 * @return the available currency
	 */
	public static Stack<Integer> getAvailablecurrency() {
		return availableCurrency;
	}

	/**
	 * Generating Singleton object of CurrencyManagerImpl
	 * 
	 * @return
	 */
	public static CurrencyManagerImpl getInstance() {
		if (null == currencyManagerImplInstance) {
			currencyManagerImplInstance = new CurrencyManagerImpl();
		}
		return currencyManagerImplInstance;
	}
}
