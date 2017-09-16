package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU.Register;

public class InstructionSETTest extends InstructionTest {
    private InstructionSET instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionSET();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x01);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(Register.R0)).thenReturn(123);
        instruction.setDestination(Register.R1);
        instruction.setValue(123);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1)).setRegister(Register.R1, 123);
    }
}