package pl.tomaszszewczyk;

import java.util.List;

public interface Instruction {
    void execute();
    byte get_opcode();
    List<Byte> get_args();
}
