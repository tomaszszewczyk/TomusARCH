package pl.tomaszszewczyk.instructions;

import junit.framework.TestCase;
import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.RAM;

import static org.mockito.Mockito.mock;

public class InstructionPUSHTest extends TestCase {
    private InstructionPUSH instruction;
    private Machine machine;
    private CPU cpu;
    private RAM ram;

    public void setUp() {
        machine = mock(Machine.class);
        cpu = mock(CPU.class);
        ram = mock(RAM.class);
        Mockito.when(machine.getCPU()).thenReturn(cpu);
        Mockito.when(machine.getRAM()).thenReturn(ram);
        instruction = new InstructionPUSH();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x30);
    }

    public void testExecuteBothSet() {
        Mockito.when(cpu.getRegister(CPU.Register.SP)).thenReturn(0x0F);
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xAB);
        instruction.setSource(CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.SP, 0x0B);
        Mockito.verify(ram, Mockito.times(1))
                .writeWord(0x0B, 0xAB);
    }
}