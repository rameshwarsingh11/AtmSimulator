package com.yodlee.atm.facade.currency;

import java.util.Map;
import java.util.Stack;

/**
 * Interface CurrencyManager to provide functionalities for dispensing currency
 * notes and loading the machine with default currency notes.
 * 
 * @author Rameshwar Singh
 *
 */
public interface CurrencyManager {
	Map<Integer, Integer> calculateDispenseCurrencyNotes(int amount, Map<Integer, Integer> currencyMap);

	void loadAtmMachine(Stack<Integer> availablecurrecy);
}
