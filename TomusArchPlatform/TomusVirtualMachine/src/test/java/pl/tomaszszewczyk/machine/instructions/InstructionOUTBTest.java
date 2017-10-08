package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionOUTBTest extends InstructionTest {
    private InstructionOUTB instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionOUTB.getOpcode(), (byte) 0xF2);
    }

    public void testExecute() throws Exception {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xBB);
        instruction = new InstructionOUTB(0x100, CPU.Register.R0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .writePort(0x100, 0xBB);

    }
}