package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionOUTB - save byte to output
 * Saves least significant byte from register src to output port imm8
 *
 * @author Tomasz Szewczyk
 */
public class InstructionOUTB extends Instruction {
    private Register src;
    private int imm8;

    /**
     * Instruction constructor
     *
     * @param imm8 Output port
     * @param src  Source register
     */
    public InstructionOUTB(int imm8, Register src) {
        this.src = src;
        this.imm8 = imm8;
    }

    public static byte getOpcode() {
        return (byte) 0xF2;
    }

    public void execute(Machine parent) throws InstructionExecutionException {
        CPU cpu = parent.getCPU();
        int value = cpu.getRegister(src);
        try {
            cpu.writePort(imm8, value);
        } catch (CPU.WrongPortAddressException e) {
            throw new InstructionExecutionException();
        }
    }
}
