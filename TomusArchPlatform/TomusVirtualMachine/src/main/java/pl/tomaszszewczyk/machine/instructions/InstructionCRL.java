package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionCALLR - control register load
 * Copy dst register value to special register of address imm16
 *
 * @author Tomasz Szewczyk
 */
public class InstructionCRL extends Instruction {
    private Register src;
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Control register address
     * @param src   source register
     */
    public InstructionCRL(int imm16, Register src) {
        this.imm16 = imm16;
        this.src = src;
    }

    public static byte getOpcode() {
        return (byte) 0xF0;
    }

    public void execute(Machine parent) throws InstructionExecutionException {
        CPU cpu = parent.getCPU();
        int value = cpu.getRegister(src);
        try {
            cpu.setSpecialRegister(imm16, value);
        } catch (CPU.WrongRegisterAddressException e) {
            throw new InstructionExecutionException();
        }
    }
}
