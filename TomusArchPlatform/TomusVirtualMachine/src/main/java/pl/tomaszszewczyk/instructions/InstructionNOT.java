package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.CPU.Register;

public class InstructionNOT extends Instruction {
    private Register src;
    private Register dst;

    public InstructionNOT() {
    }

    public byte getOpcode() {
        return 0x18;
    }

    public void setDestination(Register adst) {
        dst = adst;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        int dst_value = cpu.getRegister(dst);

        int result = ~dst_value;

        cpu.setRegister(dst, result);
    }
}
