package pl.tomaszszewczyk.registers;

public class Register
{
	private int value;

	public Register()
	{
		value = 0;
	}

	public void setValue(int new_value)
	{
		this.value = new_value;
	}

	public int getValue()
	{
		return value;
	}
}