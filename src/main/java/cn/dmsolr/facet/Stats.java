package cn.dmsolr.facet;

public class Stats implements Facet {
	private static final String format = "%s(%s)";
	private String func, field;
	private String name;

	public Stats(String name, String func, String field) {
		this.name = name;
		this.func = func;
		this.field = field;
	}
	
	public static Stats avg(String name, String field) {
		return new Stats(name, Func.avg, field);
	}
	
	public static Stats sum(String name, String field) {
		return new Stats(name, Func.sum, field);
	}
	
	public static Stats max(String name, String field) {
		return new Stats(name, Func.max, field);
	}
	
	public static Stats min(String name, String field) {
		return new Stats(name, Func.min, field);
	}
	
	public static Stats unique(String name, String field) {
		return new Stats(name, Func.unique, field);
	}
	
	public static Stats hll(String name, String field) {
		return new Stats(name, Func.hll, field);
	}
	
	public static Stats percentile(String name, String field) {
		return new Stats(name, Func.percentile, field);
	}
	
	public static Stats sumsq(String name, String field) {
		return new Stats(name, Func.sumsq, field);
	}
	
	public static Stats stddev(String name, String field) {
		return new Stats(name, Func.stddev, field);
	}
	
	public static Stats agg(String name, String func, String field) {
		return new Stats(name, func, field);
	}
	
	public String name() {
		return name;
	}
	
	public Object params() {
		return String.format(format, func, field);
	}
	
	static class StatsSpec {
		String func;
		String field;
	}
	
	static interface Func {
		String sum = "sum", avg = "avg", min = "min", max = "max", unique = "unique", hll = "hll", percentile = "percentile", sumsq = "sumsq", stddev = "stddev";
	}
	
	public String toQueryString() {
		return null;
	}
	
}
