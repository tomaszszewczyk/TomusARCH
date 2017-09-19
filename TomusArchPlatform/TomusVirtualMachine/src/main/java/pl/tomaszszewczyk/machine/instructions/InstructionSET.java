package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

public class InstructionSET extends Instruction {
    private Register dst;
    private int value;

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
