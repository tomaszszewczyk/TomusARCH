package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU.Register;

public class InstructionSETTest extends InstructionTest {
    private InstructionSET instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x01);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(Register.R0)).thenReturn(123);
        instruction = new InstructionSET(Register.R1, 123);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1)).setRegister(Register.R1, 123);
    }
}