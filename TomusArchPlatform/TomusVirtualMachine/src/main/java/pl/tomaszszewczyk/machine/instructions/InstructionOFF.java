package pl.tomaszszewczyk.machine.instructions;

import pl.tomaszszewczyk.machine.Machine;

public class InstructionOFF extends Instruction {

    public byte getOpcode() {
        return (byte) 0xF5;
    }

    public void execute(Machine parent) {
        parent.powerOFF();
    }

}
