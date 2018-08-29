package cn.dmsolr.facet;

/**
 * 
 * <code>
 * json.facet={ dm : 'avg(field)' } // stats </br>
 * json.facet={ dm : { } } // field facet </br>
 * json.facet={ dm : {type:, field:, ..., facet:{} } } // nested facet </br>
 * </code> 
 * 
 * @author daming
 *
 */
public interface Facet {
	String toQueryString();
	
	Object params();
	
	String name();
}
