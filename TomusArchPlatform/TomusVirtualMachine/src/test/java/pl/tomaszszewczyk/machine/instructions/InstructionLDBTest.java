package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionLDBTest extends InstructionTest {
    private InstructionLDB instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionLDB();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x04);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(0xAA);
        Mockito.when(ram.getByte(0xAA)).thenReturn((byte) 0xBB);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, (byte) 0xBB);
    }
}