package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

public class InstructionMOV extends Instruction {
    private Register src;
    private Register dst;

    public byte getOpcode() {
        return 0x00;
    }

    public void setSource(Register asrc) {
        src = asrc;
    }

    public void setDestination(Register adst) {
        dst = adst;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int src_val = cpu.getRegister(src);
        cpu.setRegister(dst, src_val);
    }
}
