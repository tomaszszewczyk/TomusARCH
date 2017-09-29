package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionDIVTest extends InstructionTest {
    private InstructionDIV instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionDIV.getOpcode(), 0x13);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(5);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(100);
        instruction = new InstructionDIV(CPU.Register.R1, CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 20);
    }
}