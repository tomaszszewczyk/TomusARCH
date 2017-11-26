package pl.tomaszszewczyk.machine;

public class RandomAccessMemory {
    public static final int memorySize = 65536;

    private byte memory[] = new byte[memorySize];
    private InterruptController int_controller;

    public RandomAccessMemory(InterruptController new_int) {
        int_controller = new_int;
    }

    private boolean isInRange(int address) {
        return 0 <= address && address < memorySize;
    }

    public void setByte(int address, byte value) {
        if(!isInRange(address)) {
            int_controller.report(Interrupt.MemoryRangeExceeded);
            return;
        }

        memory[address] = value;
    }

    public int getByte(int address) {
        if(!isInRange(address)) {
            int_controller.report(Interrupt.MemoryRangeExceeded);
            return 0;
        }

        return memory[address];
    }

}
