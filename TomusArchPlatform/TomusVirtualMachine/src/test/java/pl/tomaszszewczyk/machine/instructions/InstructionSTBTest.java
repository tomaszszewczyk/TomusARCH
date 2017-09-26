package pl.tomaszszewczyk.machine.instructions;

import junit.framework.TestCase;
import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

import static org.mockito.Mockito.mock;

public class InstructionSTBTest extends TestCase {
    private InstructionSTB instruction;
    private Machine machine;
    private CPU cpu;
    private RAM ram;

    public void setUp() {
        machine = mock(Machine.class);
        cpu = mock(CPU.class);
        ram = mock(RAM.class);
        Mockito.when(machine.getCPU()).thenReturn(cpu);
        Mockito.when(machine.getRAM()).thenReturn(ram);
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x05);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xAABB);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(0xCCDD);
        instruction = new InstructionSTB(CPU.Register.R1, CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(ram, Mockito.times(1))
                .writeByte(0xCCDD, (byte) 0xBB);
    }
}