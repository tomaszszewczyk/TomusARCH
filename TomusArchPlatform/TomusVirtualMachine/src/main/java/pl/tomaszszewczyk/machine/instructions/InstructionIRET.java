package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

/**
 * InstructionIRET - Interrupt return
 * Restore registers state saved on stack
 *
 * @author Tomasz Szewczyk
 */
public class InstructionIRET extends Instruction {

    public static byte getOpcode() {
        return (byte) 0xF4;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        for (CPU.Register register : CPU.Register.values()) {
            int value = pop(cpu, ram);
            cpu.setRegister(register, value);
        }
    }

}
