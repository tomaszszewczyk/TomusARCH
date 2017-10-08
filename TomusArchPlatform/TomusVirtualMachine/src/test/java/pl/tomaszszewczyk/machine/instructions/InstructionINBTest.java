package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionINBTest extends InstructionTest {
    private InstructionINB instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), (byte) 0xF3);
    }

    public void testExecute() throws Exception {
        Mockito.when(cpu.readPort(0x100)).thenReturn(0xBB);
        instruction = new InstructionINB(0x100, CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 0xBB);

    }
}