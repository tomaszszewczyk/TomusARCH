package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * InstructionJNZ - jump if not zero
 * Check if flag ZF is clear - if yes, perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJNZ extends Instruction {
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJNZ(int imm16) {
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return 0x22;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagZF = cpu.getFlagZF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if (!flagZF) {
            pc += imm16;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
