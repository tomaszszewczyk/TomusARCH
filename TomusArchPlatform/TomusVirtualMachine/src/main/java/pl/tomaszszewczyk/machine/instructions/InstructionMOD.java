package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * Instruction MOD - calculates modulo from two values
 * Modulo values from dst and source, result is saved in dst
 *
 * @author Tomasz Szewczyk
 */
public class InstructionMOD extends Instruction {
    private Register src;
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param dst Destination register
     * @param src Source register
     */
    public InstructionMOD(Register dst, Register src) {
        this.dst = dst;
        this.src = src;
    }

    public static byte getOpcode() {
        return 0x14;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        int dst_value = cpu.getRegister(dst);
        int src_value = cpu.getRegister(src);

        int result = dst_value % src_value;

        cpu.setRegister(dst, result);
    }
}
