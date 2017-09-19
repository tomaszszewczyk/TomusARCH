package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionCRLTest extends InstructionTest {
    private InstructionCRL instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionCRL();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), (byte) 0xF0);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xBB);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(0x100);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setSpecialRegister(0x100, 0xBB);

    }
}