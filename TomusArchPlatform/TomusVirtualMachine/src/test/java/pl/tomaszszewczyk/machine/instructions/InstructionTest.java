package pl.tomaszszewczyk.machine.instructions;

import junit.framework.TestCase;
import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

import static org.mockito.Mockito.mock;

public abstract class InstructionTest extends TestCase {
    protected Machine machine;
    protected CPU cpu;
    protected RAM ram;

    public void setUp() {
        machine = mock(Machine.class);
        cpu = mock(CPU.class);
        ram = mock(RAM.class);
        Mockito.when(machine.getCPU()).thenReturn(cpu);
        Mockito.when(machine.getRAM()).thenReturn(ram);
    }
}
