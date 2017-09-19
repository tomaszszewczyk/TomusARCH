package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.CPU;
import pl.tomaszszewczyk.machine.CPU.Register;
import pl.tomaszszewczyk.machine.Machine;

public class InstructionINB extends Instruction {
    private Register destinationRegister;
    private int source;

    public byte getOpcode() {
        return (byte) 0xF3;
    }

    public void setDestination(Register aDestinationRegister) {
        destinationRegister = aDestinationRegister;
    }

    public void setSource(int aSource) {
        source = aSource;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int value = cpu.readPort(source);
        cpu.setRegister(destinationRegister, value);
    }
}
