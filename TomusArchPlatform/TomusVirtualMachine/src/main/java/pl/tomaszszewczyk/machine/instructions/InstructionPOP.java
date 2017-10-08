package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

/**
 * InstructionPOP - pop from stack
 * Load value from RAM from address in SP register to register dst and increment SP by 4
 *
 * @author Tomasz Szewczyk
 */
public class InstructionPOP extends Instruction {
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param aDst Destination register
     */
    public InstructionPOP(Register aDst) {
        dst = aDst;
    }

    public static byte getOpcode() {
        return 0x31;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int sp = cpu.getRegister(Register.SP);
        int value = ram.getWord(sp);
        sp += 4;
        cpu.setRegister(dst, value);
        cpu.setRegister(Register.SP, sp);
    }
}
