package pl.tomaszszewczyk.instructions;

import junit.framework.TestCase;
import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

import static org.mockito.Mockito.mock;

public class InstructionPOPTest extends TestCase {
    private InstructionPOP instruction;
    private Machine machine;
    private CPU cpu;
    private RAM ram;

    public void setUp() {
        machine = mock(Machine.class);
        cpu = mock(CPU.class);
        ram = mock(RAM.class);
        Mockito.when(machine.getCPU()).thenReturn(cpu);
        Mockito.when(machine.getRAM()).thenReturn(ram);
        instruction = new InstructionPOP();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x31);
    }

    public void testExecuteBothSet() {
        Mockito.when(cpu.getRegister(CPU.Register.SP)).thenReturn(0x0F);
        Mockito.when(ram.getWord(0x0F)).thenReturn(0xAA);
        instruction.setDestination(CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.SP, 0x13);
        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R0, 0xAA);
    }
}