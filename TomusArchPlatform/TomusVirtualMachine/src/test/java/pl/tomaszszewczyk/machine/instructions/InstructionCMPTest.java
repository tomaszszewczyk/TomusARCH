package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionCMPTest extends InstructionTest {
    private InstructionCMP instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionCMP();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x20);
    }

    public void testEqual() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(10);  //source
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(10);  //destination
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1)).setFlagZF();
        Mockito.verify(cpu, Mockito.times(0)).setFlagCF();
    }

    public void testGreater() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(10);  //source
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(20);  //destination
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(0)).setFlagZF();
        Mockito.verify(cpu, Mockito.times(0)).setFlagCF();
    }

    public void testSmaller() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(20);  //source
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(10);  //destination
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(0)).setFlagZF();
        Mockito.verify(cpu, Mockito.times(1)).setFlagCF();
    }
}