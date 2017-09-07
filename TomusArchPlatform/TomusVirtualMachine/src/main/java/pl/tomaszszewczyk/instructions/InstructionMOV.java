package pl.tomaszszewczyk.instructions;

import pl.tomaszszewczyk.Instruction;
import pl.tomaszszewczyk.Machine;

import java.util.List;

public class InstructionMOV implements Instruction
{
    private final byte opcode = 0x00;
    private List<Byte> args;
    private Machine machine;

    public InstructionMOV(Machine aMachine)
    {
        machine = aMachine;
    }

    public void add_arg(byte arg)
    {
        args.add(arg);
    }

    public void execute() {
        
    }

    public byte get_opcode() {
        return opcode;
    }

    public List<Byte> get_args() {
        return args;
    }
}