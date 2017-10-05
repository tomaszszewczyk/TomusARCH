package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * Instruction NOT - negate value
 * negate value from dst, result is saved in dst
 *
 * @author Tomasz Szewczyk
 */
public class InstructionNOT extends Instruction {
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param dst Destination register
     */
    public InstructionNOT(Register dst) {
        this.dst = dst;
    }

    public static byte getOpcode() {
        return 0x18;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        int dst_value = cpu.getRegister(dst);

        int result = ~dst_value;

        cpu.setRegister(dst, result);
    }
}
