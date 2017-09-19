package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionMULTest extends InstructionTest {
    private InstructionMUL instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionMUL();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x12);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(10);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(20);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 200);
    }
}