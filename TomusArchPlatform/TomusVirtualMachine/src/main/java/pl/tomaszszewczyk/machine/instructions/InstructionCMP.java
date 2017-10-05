package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

/**
 * Instruction CMP - compare two registers
 * Compares dst register to src register and saves result in FR register
 *
 * @author Tomasz Szewczyk
 */
public class InstructionCMP extends Instruction {
    private Register src;
    private Register dst;

    /**
     * Instruction constructor
     *
     * @param dst Destination register
     * @param src Source register
     */
    public InstructionCMP(Register dst, Register src) {
        this.dst = dst;
        this.src = src;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        int dst_value = cpu.getRegister(dst);
        int src_value = cpu.getRegister(src);

        int result = dst_value - src_value;

        cpu.clearFlagZF();
        cpu.clearFlagCF();

        if (result == 0)
            cpu.setFlagZF();
        else if (result < 0)
            cpu.setFlagCF();
    }
}
