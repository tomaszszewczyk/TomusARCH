package pl.tomaszszewczyk.machine.instructions;

import junit.framework.TestCase;
import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

import static org.mockito.Mockito.mock;

public class InstructionSUBTest extends TestCase {
    private InstructionSUB instruction;
    private Machine machine;
    private CPU cpu;
    private RAM ram;

    public void setUp() {
        machine = mock(Machine.class);
        cpu = mock(CPU.class);
        ram = mock(RAM.class);
        Mockito.when(machine.getCPU()).thenReturn(cpu);
        Mockito.when(machine.getRAM()).thenReturn(ram);
        instruction = new InstructionSUB();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x10);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0x1111);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(0x2222);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0x1111);
    }
}