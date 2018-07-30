package cn.dmsolr.facet;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class JsonFacet implements Facet {
	private final Map<String, Object> params = Maps.newHashMap();
	private List<Facet> facets = Lists.newArrayList();
	private final String name;

	protected JsonFacet(String name, String type, String field) {
		this.name = name;
		params.put("type", type);
		params.put("field", field);
	}

	public static FieldFacet newFieldFacet(String name, String field) {
		return FieldFacet.newFieldFacet(name, field);
	}
	
	public static QueryFacet newQueryFacet(String name) {
		return QueryFacet.newQueryFacet(name);
	}
	
	public static RangeFacet newRangeFacet(String name, String field) {
		return RangeFacet.newRangeFacet(name, field);
	}
	
	public static FieldFacet pivot(String... field) {
		return null;
	}
	
	public String toQueryString() {
		if (!facets.isEmpty()) {
			Map<String, Object> row = Maps.newHashMap();
			for (Facet facet : facets) {
				if (facet instanceof JsonFacet) {
					JsonFacet jf = (JsonFacet) facet;
					row.put(jf.name(), jf.params());
				}
				else if (facet instanceof Stats) {
					Stats sf = (Stats) facet;
					row.put(sf.name(), sf.params());
				}
			}
			params.put("facet", row);
		}
		return new StringBuilder("{")
				.append(this.name).append(":").append(JSONObject.toJSONString(params)).append("}").toString();
	}
	
	protected void put(String key, Object val) {
		params.put(key, val);
	}
	
	protected void remove(String key) {
		params.remove(key);
	}
	
	public String name() {
		return name;
	}
	
	public Object params() {
		return params;
	}
	
	public String facet() {
		 return null;
	}
	
	public JsonFacet facet(Facet facet) {
		facets.add(facet);
		return this;
	}
	
	protected String getString(String key) {
		Object object = params.get(key);
		if (object == null) return null;
		return String.valueOf(object);
	}
	
	protected boolean getBool(String key) {
		Object object = params.get(key);
		if (object == null) return false;
		
		if (object instanceof Boolean) 
			return (Boolean) object;
		
		return Boolean.valueOf(String.valueOf(object));
	}
}


