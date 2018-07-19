package com.yodlee.atm;

import java.util.Date;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 
 * @author Rameshwar Singh
 *
 */
// TODO : add comments and logger.
public class AtmSimulator {
	private static float balance = 0.0f;
	private static boolean creditDone = false;
	private static boolean debitDone = false;
	private static float totalCredit = 0.0f;
	private static float totalDebit = 0.0f;
	private static TreeSet<Transaction> transactionSet = new TreeSet<>();
	private static final Stack<Float> availableCurrecy = new Stack<>();

	public AtmSimulator() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		startMenuOptions();
	}

	private static void startMenuOptions() {
		loadAtmMachine(availableCurrecy);
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

	private static void loadAtmMachine(Stack<Float> availablecurrecy) {
		availablecurrecy.push(10.0f);
		availablecurrecy.push(20.0f);
		availablecurrecy.push(50.0f);
	}

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

	private static String processDisplayBalanceRequest(float balance) {
		return "Available balance :" + balance;
	}

	private static void processWithdrawRequest(Scanner scanner) {
		System.out.println("Enter amount to withdraw :");
		float amount = scanner.nextFloat();
		if (amount != 0 && amount <= balance) {
			float dispenseCurrencyNotes[] = calculateDispenseCurrencyNotes(amount);
			System.out.println("Dispensing : " + amount + "$");
			balance -= amount;
			totalDebit += amount;
			debitDone = true;
		} else {
			System.out.println("Please enter currect amount");
			debitDone = false;
		}
		transactionSet.add(new Transaction("Debit", new Date(), totalDebit, balance));
	}

	private static float[] calculateDispenseCurrencyNotes(float amount) {
		float currencyNote=availableCurrecy.pop();
		int currencyNoteCount=(int) (amount/availableCurrecy.pop());
		//TODO
		
		return null;
	}
}
