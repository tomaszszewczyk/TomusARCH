package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.CPU.Register;

public class InstructionMOV extends Instruction {
    private byte opcode = 0x00;
    private Register src;
    private Register dst;

    public InstructionMOV() {
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
