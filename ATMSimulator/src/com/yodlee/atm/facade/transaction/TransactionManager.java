package com.yodlee.atm.facade.transaction;

import java.util.Scanner;

/**
 * Interface TransactionManager to provide functionalities for processing
 * deposit and withdraw requests made by customer.
 * 
 * @author Rameshwar Singh
 *
 */
public interface TransactionManager {
	float processDepositRequest(Scanner scanner, float balane);

	float processWithdrawRequest(Scanner scanner, float balance);
}
