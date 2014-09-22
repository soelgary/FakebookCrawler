package com.gsoeller.fakebook.FakebookCrawler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.net.InternetDomainName;
import com.gsoeller.fakebook.Data.FakebookQueue;

public class FakebookQueueTest {

	private final InternetDomainName domain1 = InternetDomainName.from("gsoeller.com");
	private final InternetDomainName domain2 = InternetDomainName.from("fakebook.com");
	
	private FakebookQueue queue;
	
	@Before
	public void setup() {
		queue = new FakebookQueue();
	}
	
	@Test
	public void testAddDomain() {
		queue.addUrl(domain1);
		assertEquals(domain1, queue.getNextUrl());
		assertTrue(queue.hasVisted(domain1));
		
		
	}
	
	@Test
	public void testAddTwoDomains() {
		queue.addUrl(domain1);
		queue.addUrl(domain2);
		assertEquals(domain1, queue.getNextUrl());
		assertTrue(queue.hasVisted(domain1));
		assertFalse(queue.hasVisted(domain2));
		assertEquals(domain2, queue.getNextUrl());
		assertTrue(queue.hasVisted(domain2));
	}
}
