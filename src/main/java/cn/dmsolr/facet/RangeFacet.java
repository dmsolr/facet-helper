package cn.dmsolr.facet;

public class RangeFacet extends JsonFacet {
	
	private RangeFacet(String name, String field) {
		super(name, "range", field);
	}
	
	public static RangeFacet newRangeFacet(String name, String field) {
		return new RangeFacet(name, field);
	}
	
	public RangeFacet start(String start) {
		put("start", start);
		return this;
	}
	
	public RangeFacet end(String end) {
		put("end", end);
		return this;
	}
	
	public RangeFacet gap(String gap) {
		put("gap", gap);
		return this;
	}
	
	public JsonFacet hardend(boolean hardend) {
		put("hardend", hardend);
		return this;
	}

	public JsonFacet other(String other) {
		put("other", other);
		return this;
	}

	public JsonFacet include(String include) {
		put("include", include);
		return this;
	}
	
	public String start() {
		return getString("start");
	}

	public String end() {
		return getString("end");
	}

	public String gap() {
		return getString("gap");
	}

	public boolean hardend() {
		return getBool("hardend");
	}

	public String other() {
		return getString("other");
	}

	public String include() {
		return getString("include");
	}

}
