package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionJMP - jump
 * Perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJMP extends Instruction {
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJMP(int imm16) {
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return 0x40;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int pc = cpu.getRegister(Register.PC) + 3 + imm16;
        cpu.setRegister(Register.PC, pc);
    }
}
