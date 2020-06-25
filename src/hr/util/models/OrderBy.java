package hr.util.models;

public class OrderBy
{
	public OrderBy(String column,OrderEnum orderOperation)
	{
		this.column = column;
		this.orderOperation = orderOperation;
	}

    public String getOrderByString()
    {
            return String.format("{0} {1}", column, orderOperation.name());
    }

    public String column;
    
    public OrderEnum orderOperation;
}