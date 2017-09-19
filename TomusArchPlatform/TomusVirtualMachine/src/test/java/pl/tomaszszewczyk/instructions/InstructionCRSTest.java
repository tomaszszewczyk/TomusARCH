package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionCRSTest extends InstructionTest {
    private InstructionCRS instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionCRS();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), (byte) 0xF1);
    }

    public void testExecute() {
        Mockito.when(cpu.getSpecialRegister(0x100)).thenReturn(0xBB);
        instruction.setSource(0x100);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0xBB);

    }
}