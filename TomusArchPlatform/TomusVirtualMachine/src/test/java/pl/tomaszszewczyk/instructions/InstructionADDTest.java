package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionADDTest extends InstructionTest {
    private InstructionADD instruction;
    public void setUp() {
        super.setUp();
        instruction = new InstructionADD();
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
                .setRegister(CPU.Register.R1, 0x3333);
    }
}