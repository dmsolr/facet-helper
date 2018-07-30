package cn.dmsolr.facet;

public class SortSpec {
	private String field;
	
	
	public static SortSpec field(String field) {
		return new SortSpec(field);
	}
	
	private SortSpec(String field) {
		this.field = field;
	}
	
	public String desc() {
		return String.format("{%s:desc}", field);
	}
	
	public String asc() {
		return String.format("{%s:asc}", field);
	}
}
