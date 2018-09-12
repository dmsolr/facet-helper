package cn.dmsolr.facet;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
	
	public static FieldFacet pivot(String... fields) {
		if (fields.length < 1) return null;
		
		if (fields.length == 1) 
			return newFieldFacet(fields[0], fields[0]);
		
		FieldFacet facet = newFieldFacet(fields[0], fields[0]);
		FieldFacet sub = facet;
		for (int idx=1; idx<fields.length; idx++) {
			FieldFacet _sub = newFieldFacet(fields[idx], fields[idx]);
			sub.facet(_sub);
			sub = _sub;
		}
		return facet;
	}
	
	public String toQueryString() {
		return new StringBuilder("{")
				.append(this.name).append(":").append(JSONObject.toJSONString(traversal(), SerializerFeature.UseSingleQuotes)).append("}").toString();
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
	
	Map<String, Object> traversal() {
		Map<String, Object> map = Maps.newHashMap();
		for (Facet f : this.facets) {
			if (f instanceof JsonFacet) {
				map.put(f.name(), ((JsonFacet) f).traversal());
			}
			else {
				map.put(f.name(), f.params());
			}
		}
		if (this.facets.isEmpty())
			return params;
		params.put("facet", map);
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


