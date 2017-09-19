package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionSHLTest extends InstructionTest {
    private InstructionSHL instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionSHL();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x19);
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(CPU.Register.R0)).thenReturn(1);
        Mockito.when(cpu.getRegister(CPU.Register.R1)).thenReturn(2);
        instruction.setSource(CPU.Register.R0);
        instruction.setDestination(CPU.Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.R1, 4);
    }
}