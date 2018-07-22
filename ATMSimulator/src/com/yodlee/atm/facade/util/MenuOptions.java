package com.yodlee.atm.facade.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class MenuOptions to provided default menu to be displayed for customer.
 * 
 * @author Rameshwar Singh
 *
 */
public class MenuOptions {
	private static final Logger LOGGER = Logger.getLogger(MenuOptions.class.getName());

	public static void printMenuOptions() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Execution started for method ::::: " + MenuOptions.class + "printMenuOptions");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Display Balance");
		System.out.println("4. Mini Statement");
		System.out.println("5. Exit");
		System.out.println("Select an Option:");
		LOGGER.info("Execution ended for method ::::: " + MenuOptions.class + "printMenuOptions");
	}
}
