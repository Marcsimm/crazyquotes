package com.rest.quotes;

import java.util.List;

public interface QuotesRepository {
	
	public List<Quote> getAllActors();
	public void insertQuote();

}
