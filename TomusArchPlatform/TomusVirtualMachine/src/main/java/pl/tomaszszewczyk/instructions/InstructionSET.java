package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.CPU.Register;

public class InstructionSET extends Instruction {
    private Register dst;
    private int value;

    public InstructionSET() {
    }

    public byte getOpcode() {
        return 0x01;
    }

    public void setDestination(Register adst) {
        dst = adst;
    }

    public void setValue(int avalue) {
        value = avalue;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        cpu.setRegister(dst, value);
    }
}
