package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

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
