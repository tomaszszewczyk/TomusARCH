package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.CPU;
import pl.tomaszszewczyk.CPU.Register;
import pl.tomaszszewczyk.Machine;

public class InstructionCRS extends Instruction {
    private Register destinationRegister;
    private int source;

    public byte getOpcode() {
        return (byte) 0xF1;
    }

    public void setDestination(Register aDestinationRegister) {
        destinationRegister = aDestinationRegister;
    }

    public void setSource(int aSource) {
        source = aSource;
    }

    public void execute(Machine parent) {
        CPU cpu = parent.getCPU();
        int value = cpu.getSpecialRegister(source);
        cpu.setRegister(destinationRegister, value);
    }
}
