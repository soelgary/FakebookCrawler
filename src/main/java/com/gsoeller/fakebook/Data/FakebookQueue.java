package com.gsoeller.fakebook.Data;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.net.InternetDomainName;

public class FakebookQueue {
	
	private List<InternetDomainName> queue = Lists.newArrayList();
	private Set<InternetDomainName> visited = Sets.newHashSet();
	
	public void addUrl(InternetDomainName url){
		queue.add(url);
	}
	
	public boolean hasVisted(InternetDomainName domain) {
		return visited.contains(domain);
	}
	
	public InternetDomainName getNextUrl(){
		if (queue.isEmpty()) {
			throw new RuntimeException("FakebookQueue is empty. Unable to get next url.");
		}
		InternetDomainName nextDomain = queue.remove(0);
		visited.add(nextDomain);
		return nextDomain;
	}
}
