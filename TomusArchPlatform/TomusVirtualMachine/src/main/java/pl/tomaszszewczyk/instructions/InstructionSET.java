package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.Machine;
import pl.tomaszszewczyk.CPU.Register;

public class InstructionSET extends Instruction {
    private byte opcode = 0x01;
    private Register dst;
    private int value;

    public InstructionSET() {
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
