package org.eternity.customer;

import java.math.BigDecimal;

public class Money {
	private final BigDecimal amount;
	
	public Money() {
		this.amount = new BigDecimal(0);
	}
	public Money(BigDecimal amount) {
		this.amount = amount;
	}

	public Money(long amount) {
		this(new BigDecimal(amount));
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Money)) {
			return false;
		}

		return amount.equals(((Money) object).amount);
	}

	public int hashCode() {
		return amount.hashCode();
	}

	/**
	 * �Һ���ü�� ����� ���� add�� �ٲ���� �ʵ尪�� �ٲ������ �ʵ尪�� �����ڷ� ��ü�� ����
	 * �Ѱ���.
	 **** */
	// public Money add(Money added) {
	// this.amount = this.amount.add(added.amount);
	// return this;
	// }

	public Money add(Money added) {
		return new Money(this.amount.add(added.amount));
	}

	public String toString() {
		return amount.toString();
	}

	public boolean isGreaterThan(Money limitPrice) {
		if (this.amount.compareTo(limitPrice.amount) > 0)
			return true;
		else
			return false;
	}

	public Money multiply(int quantity) {
		return new Money(this.amount.multiply(new BigDecimal(quantity)));
	}
}
