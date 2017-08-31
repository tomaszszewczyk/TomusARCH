package pl.tomaszszewczyk;

import pl.tomaszszewczyk.registers.*;
import java.util.*;

public class ProccesingUnit
{
	public enum RegisterName {
		R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16;
	}

	protected Map<RegisterName, GeneralPurposeRegister> gpr;

	protected ControlRegisters cr;

	protected ProgramCounterRegister pc;
	protected StackPointerRegister   sp;
	protected FlagRegister           fl;

	public ProccesingUnit()
	{
		gpr = new HashMap<RegisterName, GeneralPurposeRegister>();

		for(RegisterName reg : RegisterName.values())
			gpr.put(reg, new GeneralPurposeRegister());

		gpr.put(RegisterName.R0, new GeneralPurposeRegister());
		gpr.put(RegisterName.R1, new GeneralPurposeRegister());

		cr = new ControlRegisters();

		pc = new ProgramCounterRegister();
		sp = new StackPointerRegister();
		fl = new FlagRegister();
	}

	public void executeMOV(RegisterName destination, RegisterName source)
	{
		int source_value = gpr.get(source).getValue();
		gpr.get(destination).setValue(source_value);
	}

	public void executeSET(RegisterName destination, int value)
	{
		gpr.get(destination).setValue(value);
	}
}