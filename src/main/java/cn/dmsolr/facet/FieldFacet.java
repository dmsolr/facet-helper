package cn.dmsolr.facet;

public class FieldFacet extends JsonFacet {
	private static final String TYPE = "terms";
	
	public FieldFacet(String name, String field) {
		super(name, TYPE, field);
	}
	
	public static final FieldFacet newFieldFacet(String name, String field) {
		return new FieldFacet(name, field);
	}
	
	public FieldFacet offset(long offset) { put("offset", offset); return this; }
	public FieldFacet limit(long limit) { put("limit", limit); return this; }
	public FieldFacet mincount(long mincount) { put("mincount", mincount); return this; }

	public FieldFacet sort(String sort) { put("sort", sort); return this; }
	public FieldFacet prefix(String prefix) { put("prefix", prefix); return this; }
	public FieldFacet method(String method) { put("method", method); return this; }
	  
	public FieldFacet overrequest(int overrequest) { put("overrequest", overrequest); return this; }
	public FieldFacet refine(boolean refine) { put("refine", refine); return this; }
	public FieldFacet missing(boolean missing) { put("missing", missing); return this; }
	public FieldFacet numBuckets(boolean numBuckets) { put("numBuckets", numBuckets); return this; }
	public FieldFacet allBuckets(boolean allBuckets) { put("allBuckets", allBuckets); return this; }
}
