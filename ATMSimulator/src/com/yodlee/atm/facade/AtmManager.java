
package com.yodlee.atm.facade;

import java.util.Scanner;

/**
 * Interface AtmManager to process all the options selected by customer.
 * 
 * @author Rameshwar Singh
 *
 */
public interface AtmManager {
	void processOptions(int option, Scanner scanner);
}
