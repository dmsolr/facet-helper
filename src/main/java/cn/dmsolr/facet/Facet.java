package cn.dmsolr.facet;

public interface Facet {
	String toQueryString();
	
	Object params();
	
	String name();
}
