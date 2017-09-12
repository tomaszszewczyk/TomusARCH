package pl.tomaszszewczyk.instructions;

import junit.framework.TestCase;
import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

import static org.mockito.Mockito.mock;

public class InstructionCMPTest extends TestCase {
    private InstructionCMP instruction;
    private Machine machine;
    private CPU cpu;
    private RAM ram;

    public void setUp() {
        machine = mock(Machine.class);
        cpu = mock(CPU.class);
        ram = mock(RAM.class);
        Mockito.when(machine.getCPU()).thenReturn(cpu);
        Mockito.when(machine.getRAM()).thenReturn(ram);
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