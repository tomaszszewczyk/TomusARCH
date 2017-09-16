package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionJZTest extends InstructionTest {
    private InstructionJZ instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionJZ();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x21);
    }

    public void testExecuteZFSet() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagZF()).thenReturn(true);
        instruction.setRelativeAddress(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xFF);
    }

    public void testExecuteZFClear() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagZF()).thenReturn(false);
        instruction.setRelativeAddress(0xF0);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(0))
                .setRegister(CPU.Register.PC, 0x0F);
    }
}