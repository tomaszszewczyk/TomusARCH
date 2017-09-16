package pl.tomaszszewczyk.instructions;

import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;

public class InstructionJATest extends InstructionTest {
    private InstructionJA instruction;

    public void setUp() {
        super.setUp();
        instruction = new InstructionJA();
    }

    public void testSetup() {
        assertEquals(instruction.getOpcode(), 0x26);
    }

    public void testExecuteCFSetZFClear() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagCF()).thenReturn(true);
        Mockito.when(cpu.getFlagZF()).thenReturn(false);
        instruction.setRelativeAddress(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1))
                .setRegister(CPU.Register.PC, 0xFF);
    }

    public void testExecuteCFSetZFSet() {
        Mockito.when(cpu.getRegister(CPU.Register.PC)).thenReturn(0x0F);
        Mockito.when(cpu.getFlagCF()).thenReturn(true);
        Mockito.when(cpu.getFlagZF()).thenReturn(true);
        instruction.setRelativeAddress(0xED);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(0))
                .setRegister(CPU.Register.PC, 0x0F);
    }
}