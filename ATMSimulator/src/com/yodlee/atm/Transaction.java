package com.yodlee.atm;

import java.util.Date;
import java.util.TreeSet;

/**
 * Class to time stamp the Transaction made by customer.
 * 
 * @author Rameshwar Singh
 *
 */
public class Transaction implements Comparable<Transaction> {
	public Transaction(String transactionType, Date timeStamp, float transactionAmount, float balanceAfterTransaction) {
		this.transactionType = transactionType;
		this.timeStamp = timeStamp;
		this.transactionAmount = transactionAmount;
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

	private static TreeSet<Transaction> transactionSet = new TreeSet<>();
	private String transactionType;
	private Date timeStamp;
	private float transactionAmount;
	private float balanceAfterTransaction;

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public float getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(float balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

	public String toString() {
		return timeStamp + " " + transactionType + " " + transactionAmount + " " + balanceAfterTransaction;
	}

	@Override
	public int compareTo(Transaction transaction) {
		if (this.timeStamp == transaction.getTimeStamp()) {
			return 0;
		}
		return 1;
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
		Transaction.transactionSet = transactionSet;
	}
}
