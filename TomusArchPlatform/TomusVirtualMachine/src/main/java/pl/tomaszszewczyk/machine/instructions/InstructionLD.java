package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

/**
 * Instruction LD - load word from RAM
 * Load value from address given in src in RAM to dst
 *
 * @author Tomasz Szewczyk
 */
public class InstructionLD extends Instruction {
    private Register src;
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param dst Destination register
     * @param src Register with source address
     */
    public InstructionLD(Register dst, Register src) {
        this.dst = dst;
        this.src = src;
    }

    public static byte getOpcode() {
        return 0x02;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int source_address = cpu.getRegister(src);
        int data = ram.getWord(source_address);
        cpu.setRegister(dst, data);
    }
}
