package org.eternity.customer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMoneyBigDecimal() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMoneyLong() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testMethodAlaising(){
		Money money = new Money(2000);
		doSomethingWithMoney(money);
		assertEquals(new Money(2000), money);
	}
	
	private void doSomethingWithMoney(final Money money){
		money.add(new Money(2000));
	}


}
