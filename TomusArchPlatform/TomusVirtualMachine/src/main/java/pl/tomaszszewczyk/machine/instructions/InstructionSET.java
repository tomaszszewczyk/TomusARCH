package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * Instruction SET - sets register to given value
 * Write imm value to register
 *
 * @author Tomasz Szewczyk
 */
public class InstructionSET extends Instruction {
    private Register dst;
    private int imm;

    /**
     * Instruction constructor
     *
     * @param dst Destination register
     * @param imm Given value
     */
    public InstructionSET(Register dst, int imm) {
        this.dst = dst;
        this.imm = imm;
    }

    public static byte getOpcode() {
        return 0x01;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        cpu.setRegister(dst, imm);
    }
}
