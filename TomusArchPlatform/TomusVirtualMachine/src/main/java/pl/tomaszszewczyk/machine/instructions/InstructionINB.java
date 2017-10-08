package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionINB - load byte from input
 * Copy byte from given port and saves value in dst register
 *
 * @author Tomasz Szewczyk
 */
public class InstructionINB extends Instruction {
    private Register dst;
    private int imm8;

    /**
     * Instruction constructor
     *
     * @param imm8 Input port address
     * @param dst  destination register address
     */
    public InstructionINB(int imm8, Register dst) {
        this.dst = dst;
        this.imm8 = imm8;
    }

    public static byte getOpcode() {
        return (byte) 0xF3;
    }

    public void execute(Machine parent) throws InstructionExecutionException {
        CPU cpu = parent.getCPU();
        int value;
        try {
            value = cpu.readPort(imm8);
        } catch (CPU.WrongPortAddressException e) {
            throw new InstructionExecutionException();
        }
        cpu.setRegister(dst, value);
    }
}
