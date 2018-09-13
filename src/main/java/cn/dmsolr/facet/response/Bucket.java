package cn.dmsolr.facet.response;

import org.apache.solr.common.util.NamedList;

import com.alibaba.fastjson.util.TypeUtils;

public class Bucket {
	private final NamedList<Object> namedList;
	private final int count;
	private final String val;
	
	public Bucket(NamedList<Object> namedList) {
		this.namedList = namedList;
		this.val = ((String) namedList.get("val"));
		this.count = TypeUtils.castToInt(namedList.get("count"));
	}

	public long getCount() {
		return count;
	}
	
	public String getVal() {
		return val;
	}
	
	public String getString(String name) {
		Object object = namedList.get(name);
		return TypeUtils.castToString(object);
	}
	
	public long getLong(String name) {
		Object object = namedList.get(name);
		return TypeUtils.castToLong(object);
	}
	
	public int getInt(String name) {
		Object object = namedList.get(name);
		return TypeUtils.castToInt(object);
	}
	
	public double getDouble(String name) {
		Object object = namedList.get(name);
		return TypeUtils.castToDouble(object);
	}
	
	@SuppressWarnings("unchecked")
	public Buckets getBuckets(String name) {
		Object object = namedList.get(name);
		if (object == null) { /* do what? */ }
		return new Buckets((NamedList<Object>) object);
	}
}
