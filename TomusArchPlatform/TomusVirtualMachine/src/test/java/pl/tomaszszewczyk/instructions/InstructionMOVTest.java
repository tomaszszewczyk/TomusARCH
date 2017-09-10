package pl.tomaszszewczyk.instructions;

import junit.framework.TestCase;
import org.mockito.Mockito;
import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

import static org.mockito.Mockito.mock;

public class InstructionMOVTest extends TestCase {
    private InstructionMOV instruction;
    private Machine machine;
    private CPU cpu;

    public void setUp() {
        machine = mock(Machine.class);
        cpu = mock(CPU.class);
        Mockito.when(machine.getCPU()).thenReturn(cpu);
        instruction = new InstructionMOV();
    }

    public void testExecute() {
        Mockito.when(cpu.getRegister(Register.R0)).thenReturn(123);
        instruction.setSource(Register.R0);
        instruction.setDestination(Register.R1);

        instruction.execute(machine);

        Mockito.verify(cpu, Mockito.times(1)).setRegister(Register.R1, 123);
    }
}