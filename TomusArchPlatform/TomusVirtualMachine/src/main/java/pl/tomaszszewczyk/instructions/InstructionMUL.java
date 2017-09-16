package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

public class InstructionMUL extends Instruction {
    private Register src;
    private Register dst;

    public byte getOpcode() {
        return 0x12;
    }

    public void setSource(Register asrc) {
        src = asrc;
    }

    public void setDestination(Register adst) {
        dst = adst;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();

        int dst_value = cpu.getRegister(dst);
        int src_value = cpu.getRegister(src);

        int result = dst_value * src_value;

        cpu.setRegister(dst, result);
    }
}
