package org.eternity.order;

import org.eternity.exception.BaseException;

public class OrderLimitExceededException extends BaseException {

	public OrderLimitExceededException(Throwable e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public OrderLimitExceededException(int status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public OrderLimitExceededException(int status, Throwable e) {
		super(status, e);
		// TODO Auto-generated constructor stub
	}

}
