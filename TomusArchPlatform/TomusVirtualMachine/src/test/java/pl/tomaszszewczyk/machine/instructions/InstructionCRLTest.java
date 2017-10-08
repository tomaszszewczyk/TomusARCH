package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionCRLTest extends InstructionTest {
    private InstructionCRL instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionCRL.getOpcode(), (byte) 0xF0);
    }

    public void testExecute() throws Exception {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xBB);
        instruction = new InstructionCRL(0x100, CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setSpecialRegister(0x100, 0xBB);

    }
}