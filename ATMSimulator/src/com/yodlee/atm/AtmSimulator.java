package com.yodlee.atm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 
 * @author Rameshwar Singh Class to provide Deposit, withdraw, ministatements
 *         facility of an ATM machine.
 */
public class AtmSimulator {
	private static float balance = 0.0f;
	private static boolean creditDone = false;
	private static boolean debitDone = false;
	private static float totalCredit = 0.0f;
	private static float totalDebit = 0.0f;
	private static TreeSet<Transaction> transactionSet = new TreeSet<>();
	private static final Stack<Integer> availableCurrency = new Stack<>();

	/**
	 * bootstrap main method.
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		startMenuOptions();
	}

	/**
	 * entry point method displaying options available to customer.
	 */
	private static void startMenuOptions() {
		loadAtmMachine(availableCurrency);
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Display Balance");
		System.out.println("4. Mini Statement");
		System.out.println("5. Exit");
		System.out.println("Select an Option:");
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		processOptions(option, scanner);
		scanner.close();
	}

	/**
	 * Method to populate currency notes in ATM machine.
	 * 
	 * @param availablecurrecy
	 *            Stack<Integer>
	 */
	private static void loadAtmMachine(Stack<Integer> availablecurrecy) {
		availablecurrecy.push(10);
		availablecurrecy.push(20);
		availablecurrecy.push(50);
	}

	/**
	 * method to process varous options as per requested by user.
	 * 
	 * @param option
	 *            int
	 * @param scanner
	 *            Scanner
	 */
	private static void processOptions(int option, Scanner scanner) {
		switch (option) {
		case 1:
			processDepositRequest(scanner);
			break;
		case 2:
			processWithdrawRequest(scanner);
			break;
		case 3:
			System.out.println(processDisplayBalanceRequest(balance));
			break;
		case 4:
			processMiniStatementRequest(transactionSet);
			break;
		case 5:
			System.out.println("Thanks for banking with us today. Have a nice day !");
			System.exit(0);
		default:
			startMenuOptions();
		}
		startMenuOptions();
	}

	/**
	 * method to process a deposit request made by customer.
	 * 
	 * @param scanner
	 *            Scanner
	 */
	private static void processDepositRequest(Scanner scanner) {
		System.out.println("Enter ccy to deposit terminated by. e.g. 10 20 50 .");
		System.out.println("");
		String ccyNotes = scanner.nextLine();
		int ccyNotesCount = 0;
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
				creditDone = false;
				continue;
			}
		}
		transactionSet.add(new Transaction("Credit", new Date(), totalCredit, balance));
		creditDone = true;
		for (int i = 0; i < ccyNotesCount; i++) {
		}
	}

	/**
	 * method to process mini statement display for the customer. It will show Debit
	 * and Credit done on the account.
	 * 
	 * @param transactionSet
	 *            TreeSet<Transaction>
	 */
	private static void processMiniStatementRequest(TreeSet<Transaction> transactionSet) {
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
	}

	/**
	 * method to display available balance in the account.
	 * 
	 * @param balance
	 *            float
	 * @return String balance amount
	 */
	private static String processDisplayBalanceRequest(float balance) {
		return "Available balance :" + balance;
	}

	/**
	 * method to process withdraw request made by customer.
	 * 
	 * @param scanner
	 *            Scanner
	 */
	private static void processWithdrawRequest(Scanner scanner) {
		System.out.println("Enter amount to withdraw :");
		int amount = scanner.nextInt();
		Map<Integer, Integer> currencyMap = new HashMap<Integer, Integer>();
		if (amount != 0 && amount <= balance) {
			currencyMap = calculateDispenseCurrencyNotes(amount, currencyMap);
			for (int currency : currencyMap.keySet()) {
				for (int i = 0; i < currencyMap.get(currency); i++) {
					System.out.println("Dispensing : " + currency + "$");
				}
			}
			balance -= amount;
			totalDebit += amount;
			debitDone = true;
		} else {
			System.out.println("Please enter currect amount");
			debitDone = false;
		}
		transactionSet.add(new Transaction("Debit", new Date(), totalDebit, balance));
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
	private static Map<Integer, Integer> calculateDispenseCurrencyNotes(int amount, Map<Integer, Integer> currencyMap) {
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
			isValidCurrency = false;
		}
		return currencyMap;
	}
}
