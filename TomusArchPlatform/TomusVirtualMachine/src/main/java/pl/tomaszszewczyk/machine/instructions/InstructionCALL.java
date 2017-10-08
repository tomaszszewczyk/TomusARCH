package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

/**
 * InstructionCALL - call method from address
 * Save next instruction address on stack and perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionCALL extends Instruction {
    private int imm16;

    /**
     * Instruction constructor
     *
     * @param imm16 Destination address
     */
    public InstructionCALL(int imm16) {
        this.imm16 = imm16;
    }

    public static byte getOpcode() {
        return 0x42;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int pc = cpu.getRegister(Register.PC) + 3;
        push(cpu, ram, pc);

        cpu.setRegister(Register.PC, imm16);
    }
}
