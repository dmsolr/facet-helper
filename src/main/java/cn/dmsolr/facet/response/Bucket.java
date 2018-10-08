package cn.dmsolr.facet.response;

import java.math.BigDecimal;

import org.apache.solr.common.util.NamedList;

public class Bucket {
	private final NamedList<Object> namedList;
	private final int count;
	private final String val;
	
	public Bucket(NamedList<Object> namedList) {
		this.namedList = namedList;
		this.val = ((String) namedList.get("val"));
		this.count = getInt("count");
	}

	public long getCount() {
		return count;
	}
	
	public String getVal() {
		return val;
	}
	
	public String getString(String name) {
		Object object = namedList.get(name);
		if (object == null)
			return null;
		if (object instanceof String) 
			return (String) object;
		return String.valueOf(object);
	}
	
	public Long getLong(String name) {
		Object value = namedList.get(name);
		if(value == null){
            return null;
        }

        if(value instanceof BigDecimal){
        	return ((BigDecimal) value).longValue();
        }

        if(value instanceof Number){
            return ((Number) value).longValue();
        }
        
		return Long.parseLong((String) value);
	}
	
	public Integer getInt(String name) {
		Object value = namedList.get(name);
		if(value == null){
            return null;
        }

        if(value instanceof BigDecimal){
            return ((BigDecimal) value).intValue();
        }

        if(value instanceof Number){
            return ((Number) value).intValue();
        }
        
		return Integer.parseInt((String) value);
	}
	
	public Double getDouble(String name) {
		Object value = namedList.get(name);
		if(value == null){
            return null;
        }

        if(value instanceof BigDecimal){
        	return ((BigDecimal) value).doubleValue();
        }

        if(value instanceof Number){
            return ((Number) value).doubleValue();
        }
        
		return Double.parseDouble((String) value);
	}
	
	@SuppressWarnings("unchecked")
	public Buckets getBuckets(String name) {
		Object object = namedList.get(name);
		if (object == null) { /* do what? */ }
		return new Buckets((NamedList<Object>) object);
	}
}
