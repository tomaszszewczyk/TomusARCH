package pl.tomaszszewczyk.machine.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.machine.CPU;

public class InstructionJNZTest extends InstructionTest {
    private InstructionJNZ instruction;

    public void setUp() {
        super.setUp();
    }

    public void testSetup() {
        assertEquals(InstructionJNZ.getOpcode(), 0x22);
    }

    public void testExecuteZFClear() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagZF()).thenReturn(false);
        instruction = new InstructionJNZ(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xFF);
    }

    public void testExecuteZFSet() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagZF()).thenReturn(true);
        instruction = new InstructionJNZ(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(0))
                .setRegister(CPU.Register.PC, 0x0F);
    }
}