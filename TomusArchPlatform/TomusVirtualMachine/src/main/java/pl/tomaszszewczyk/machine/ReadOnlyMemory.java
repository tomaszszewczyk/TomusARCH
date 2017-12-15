package pl.tomaszszewczyk.machine;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * RAM (Random Access Memory) emulator class
 *
 * @author Tomasz Szewczyk
 */
public class ReadOnlyMemory {

    /**
     * Main memory array
     */
    private byte memory[];

    /**
     * Default and only constructor. Initializes main memory array with data from file
     *
     * @param path  Path to file with program
     */
    public ReadOnlyMemory(String path) {

        Path sourceFile = FileSystems.getDefault().getPath(path);

        try {
            memory = Files.readAllBytes(sourceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get instruction from address
     *
     * @param address   Instruction address
     * @return          Read instruction
     */
    public Instruction getInstruction(int address) {
        return new Instruction(
                memory[address + Instruction.opcodeOffset],
                memory[address + Instruction.rdstOffset],
                memory[address + Instruction.rsrcOffset],
                memory[address + Instruction.immediateOffset]);
    }
}
