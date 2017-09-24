package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionOUTBTest extends InstructionTest {
    private InstructionOUTB instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionOUTB();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), (byte) 0xF2);
    }

    public void testExecute() throws Exception {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xBB);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(0x100);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .writePort(0x100, 0xBB);

    }
}