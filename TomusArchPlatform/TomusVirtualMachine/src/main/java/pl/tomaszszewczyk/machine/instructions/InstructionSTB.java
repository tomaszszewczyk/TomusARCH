package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;
import pl.tomaszszewczyk.machine.RAM;

/**
 * Instruction STB - writes byte to RAM
 * Store byte from register src in RAM at address in register dst
 *
 * @author Tomasz Szewczyk
 */

public class InstructionSTB extends Instruction {
    private Register src;
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param dst Register with destination address
     * @param src Source register
     */
    public InstructionSTB(Register dst, Register src) {
        this.dst = dst;
        this.src = src;
    }

    public static byte getOpcode() {
        return 0x05;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        RAM ram = parent.getRAM();

        int dst_address = cpu.getRegister(dst);
        int data = cpu.getRegister(src);
        ram.writeByte(dst_address, (byte) data);
    }
}
