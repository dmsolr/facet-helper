package cn.dmsolr.facet.response;

import java.util.Iterator;
import java.util.List;

import org.apache.solr.common.util.NamedList;

@SuppressWarnings("unchecked")
public class Buckets implements Iterator<Bucket> {
	final int size;
	final List<Object> _buckets;
	
	int idx = 0;
	
	public Buckets(NamedList<Object> buckets) {
		List<Object> list = (List<Object>)buckets.get("buckets");
		if (list == null)  { /* do what? */ }
		this.size = list.size();
		this._buckets = list; 
	}

	@Override
	public boolean hasNext() {
		return (idx < size);
	}

	@Override
	public Bucket next() {
		return new Bucket((NamedList<Object>)_buckets.get(idx++));
	}
	
	@Override
	public String toString() {
		return _buckets.toString();
	}
	
}