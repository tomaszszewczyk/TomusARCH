package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.Machine;

public class InstructionOFF extends Instruction {

    public byte getOpcode() {
        return (byte) 0xF5;
    }

    public void execute(Machine parent) {
        parent.powerOFF();
    }

}
