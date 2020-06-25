package hr.util.models;

public class Columns {
	public Columns(String column, String display,int colWidth)
	{
		this.column = column;
		this.display= display;
		this.colWidth = colWidth;
	}
	public int colWidth;
	public String column;
	public String display;
}
