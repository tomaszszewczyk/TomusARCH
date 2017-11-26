package pl.tomaszszewczyk.machine;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadOnlyMemory {
    private byte memory[];

    public ReadOnlyMemory(String path) throws IOException {
        Path sourceFile = FileSystems.getDefault().getPath(path);
        memory = Files.readAllBytes(sourceFile);
    }

    public Instruction getInstruction(int address) {
        return new Instruction(
                memory[address + Instruction.opcodeOffset],
                memory[address + Instruction.rdstOffset],
                memory[address + Instruction.rsrcOffset],
                memory[address + Instruction.immediateOffset]);
    }
}
