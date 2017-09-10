package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.Machine;

public abstract class Instruction {
    private byte opcode;

    public Instruction() {
    }

    public byte get_opcode() {
        return this.opcode;
    }

    public abstract void execute(Machine parent);
}
