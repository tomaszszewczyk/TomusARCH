package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.Machine;

public abstract class Instruction {

    public Instruction() {
    }

    public byte getOpcode() {
        return 0x00;
    }

    public abstract void execute(Machine parent);
}
