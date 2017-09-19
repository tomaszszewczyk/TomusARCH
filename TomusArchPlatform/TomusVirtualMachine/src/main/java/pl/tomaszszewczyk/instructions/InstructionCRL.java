package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

public class InstructionCRL extends Instruction {
    private Register sourceRegister;
    private int destination;

    public byte getOpcode() {
        return (byte) 0xF0;
    }

    public void setSource(Register asourceRegister) {
        sourceRegister = asourceRegister;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int value = cpu.getRegister(sourceRegister);
        cpu.setSpecialRegister(destination, value);
    }
}
