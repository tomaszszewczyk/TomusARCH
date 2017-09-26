package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * Instruction MOV - moving values in registers
 * Copy value from source register to destination register
 *
 * @author Tomasz Szewczyk
 */
public class InstructionMOV extends Instruction {
    private Register src;
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param dst Destination register
     * @param src Source register
     */
    public InstructionMOV(Register dst, Register src) {
        this.dst = dst;
        this.src = src;
    }

    public static byte getOpcode() {
        return 0x00;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int src_val = cpu.getRegister(src);
        cpu.setRegister(dst, src_val);
    }
}
