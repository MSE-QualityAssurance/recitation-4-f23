package edu.cmu.cs.analysis;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ExchangeRateTestSolution {

	@Mock private ExchangeRate exchangeRate;

	@Test
	public void getRate() {
		MockitoAnnotations.initMocks(this);

		Mockito.when(exchangeRate.getRate("USD", "EUR")).thenReturn(1.5);

		Currency testObject = new Currency(2.50, "USD");
		Currency expected = new Currency(3.75, "EUR");

		Currency actual = testObject.toEuros(exchangeRate);
		Assert.assertEquals(expected, actual);
	}





	@Test
	public void exercise1() {

		MockitoAnnotations.initMocks(this);

		Mockito.when(exchangeRate.getRate("EUR", "USD")).thenReturn(0.5);

		Currency testObject = new Currency(2.0, "EUR");
		Currency expected = new Currency(1.0, "USD");

		Currency actual = testObject.toDollars(exchangeRate);
		Assert.assertEquals(expected, actual);
	}





	@Test
	public void exercise21() {

		MockitoAnnotations.initMocks(this);

		Mockito.when(exchangeRate.getRate("USD", "EUR")).thenReturn(0.9);

		Currency testObject = new Currency(2.50, "USD");  
		Currency expected = new Currency(2.25, "EUR");  

		Currency actual = testObject.toEuros(exchangeRate);
		Assert.assertEquals(expected, actual);
	}





	@Test
	public void exercise22() {

		MockitoAnnotations.initMocks(this);

		Mockito.when(exchangeRate.getRate("USD", "EUR")).thenReturn(1.1);

		Currency testObject = new Currency(2.50, "USD"); 
		Currency expected = new Currency(2.75, "EUR"); 

		Currency actual = testObject.toEuros(exchangeRate);
		Assert.assertEquals(expected, actual);
	}





	@Test
	public void testGetRateInvocationCount() {
		
		MockitoAnnotations.initMocks(this);

		Mockito.when(exchangeRate.getRate("USD", "EUR")).thenReturn(1.5);

		Currency testObject = new Currency(2.50, "USD");
		Currency expected = new Currency(3.75, "EUR");

		Currency actual = testObject.toEuros(exchangeRate);
		Assert.assertEquals(expected, actual);

		// Assert
		Mockito.verify(exchangeRate, Mockito.times(1)).getRate("USD", "EUR");
	}
}