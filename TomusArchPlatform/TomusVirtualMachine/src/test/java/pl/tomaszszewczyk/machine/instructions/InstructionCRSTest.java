package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionCRSTest extends InstructionTest {
    private InstructionCRS instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionCRS.getOpcode(), (byte) 0xF1);
    }

    public void testExecute() throws Exception {
        Mockito.when(cpu.getSpecialRegister(0x100)).thenReturn(0xBB);
        instruction = new InstructionCRS(0x100, CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0xBB);
    }
}