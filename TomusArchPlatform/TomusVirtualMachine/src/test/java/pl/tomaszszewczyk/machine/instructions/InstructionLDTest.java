package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionLDTest extends InstructionTest {
    private InstructionLD instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionLD();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x02);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xAA);
        Mockito.when(ram.getWord(0xAA)).thenReturn(0xBBDD);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0xBBDD);
    }
}