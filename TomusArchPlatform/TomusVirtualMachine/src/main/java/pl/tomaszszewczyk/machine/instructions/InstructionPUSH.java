package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

/**
 * InstructionPOP - pop from stack
 * Saves value from register src to RAM from address in SP register and decrease SP by 4
 *
 * @author Tomasz Szewczyk
 */
public class InstructionPUSH extends Instruction {
    private Register src;

    /**
     * Instruction constructor
     *
     * @param aSrc Destination register
     */
    public InstructionPUSH(Register aSrc) {
        src = aSrc;
    }

    public static byte getOpcode() {
        return 0x30;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int sp = cpu.getRegister(Register.SP);
        sp -= 4;
        cpu.setRegister(Register.SP, sp);

        int value = cpu.getRegister(src);
        ram.writeWord(sp, value);
    }
}
