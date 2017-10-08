package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

/**
 * InstructionCALLR - call method from address saved in register
 * Save next instruction address on stack and perform relative jump to given address
 *
 * @author Tomasz Szewczyk
 */
public class InstructionCALLR extends Instruction {
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param aDst Destination address
     */
    public InstructionCALLR(Register aDst) {
        dst = aDst;
    }

    public static byte getOpcode() {
        return 0x43;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int pc = cpu.getRegister(Register.PC) + 3;
        push(cpu, ram, pc);

        int address = cpu.getRegister(dst);
        cpu.setRegister(Register.PC, address);
    }
}
