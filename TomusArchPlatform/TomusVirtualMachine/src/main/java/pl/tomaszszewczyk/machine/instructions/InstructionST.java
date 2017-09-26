package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

/**
 * Instruction ST - write word to RAM
 * Write value from src register to RAM at address given in src
 *
 * @author Tomasz Szewczyk
 */
public class InstructionST extends Instruction {
    private Register src;
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param dst Destination address register
     * @param src Source register
     */
    public InstructionST(Register dst, Register src) {
        this.dst = dst;
        this.src = src;
    }

    public static byte getOpcode() {
        return 0x04;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int dst_address = cpu.getRegister(dst);
        int data = cpu.getRegister(src);
        ram.writeWord(dst_address, data);
    }
}
