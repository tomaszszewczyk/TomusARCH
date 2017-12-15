package pl.tomaszszewczyk.machine;

import java.io.Serializable;

/**
 * RAM (Random Access Memory) emulator class
 *
 * @author Tomasz Szewczyk
 */
public class RandomAccessMemory {
    /**
     * Memory size definition
     */
    public static final int memorySize = 65536;

    /**
     * Main memory array
     */
    private byte memory[] = new byte[memorySize];

    /**
     * Interrupt controller
     */
    private InterruptController int_controller;

    /**
     * Default constructor, requires interrupt controller
     * @param new_int   Interrupt controller
     */
    public RandomAccessMemory(InterruptController new_int) {
        int_controller = new_int;
    }


    /**
     * Write byte to memory
     * @param address   Destination address
     * @param value     Value to be written
     */
    public void writeByte(int address, byte value) {
        if(!isInRange(address)) {
            int_controller.report(Interrupt.MemoryRangeExceeded);
            return;
        }
        memory[address] = value;
    }

    /**
     * Read byte from memory
     * @param address   Source address
     * @return          Read value
     */
    public byte getByte(int address) {
        if(!isInRange(address)) {
            int_controller.report(Interrupt.MemoryRangeExceeded);
            return 0;
        }
        return memory[address];
    }

    /**
     * Check if address is in range of available memory
     * @param address   address to be checked
     * @return          true/false
     */
    private boolean isInRange(int address) {
        return 0 <= address && address < memorySize;
    }
}
