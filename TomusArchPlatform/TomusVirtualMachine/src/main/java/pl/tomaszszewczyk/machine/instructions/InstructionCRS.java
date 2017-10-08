package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionCALLR - control register save
 * Copy value of special register imm16 and saves it to dst
 *
 * @author Tomasz Szewczyk
 */
public class InstructionCRS extends Instruction {
    private Register dst;
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Control register address
     * @param dst   destination register
     */
    public InstructionCRS(int imm16, Register dst) {
        this.dst = dst;
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return (byte) 0xF1;
    }

    public void execute(Machine parent) throws InstructionExecutionException {
        CPU cpu = parent.getCPU();
        int value = 0;
        try {
            value = cpu.getSpecialRegister(imm16);
        } catch (CPU.WrongRegisterAddressException e) {
            throw new InstructionExecutionException();
        }
        cpu.setRegister(dst, value);
    }
}
