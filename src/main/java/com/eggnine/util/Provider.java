/**
 * 
 */
package com.eggnine.util;

import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;


/**
 * @since 1
 *
 */
public class Provider<I> implements Iterable<I> {
	Collection<I> collection;

	public Provider(Collection<I> collection) {
		this.collection = collection;
	}

	public Collection<I> asNewCollection() {
		List<I> newCollection = new ArrayList<I>();
		newCollection.addAll(this.collection);
		return newCollection;
	}

	@Override
	public Iterator<I> iterator() {
		return collection.iterator();
	}

}
