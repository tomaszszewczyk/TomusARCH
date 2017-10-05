package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionNOTTest extends InstructionTest {
    private InstructionNOT instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionNOT.getOpcode(), 0x18);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(0xFF000000);
        instruction = new InstructionNOT(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0x00FFFFFF);
    }
}