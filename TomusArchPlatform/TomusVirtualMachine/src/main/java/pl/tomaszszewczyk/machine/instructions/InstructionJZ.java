package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * Instruction JZ - jump if zero
 * Check of ZF flag is set. If is, then PC is incremented by imm16
 *
 * @author Tomasz Szewczyk
 */
public class InstructionJZ extends Instruction {
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionJZ(int imm16) {
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return 0x21;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        boolean flagZF = cpu.getFlagZF();
        int pc = cpu.getRegister(Register.PC) + 3;

        if (flagZF) {
            pc += imm16;
            cpu.setRegister(Register.PC, pc);
        }
    }
}
