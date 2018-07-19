package com.yodlee.atm;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {
	public Transaction(String transactionType, Date timeStamp, float transactionAmount, float balanceAfterTransaction) {
		this.transactionType = transactionType;
		this.timeStamp = timeStamp;
		this.transactionAmount = transactionAmount;
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

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
}
