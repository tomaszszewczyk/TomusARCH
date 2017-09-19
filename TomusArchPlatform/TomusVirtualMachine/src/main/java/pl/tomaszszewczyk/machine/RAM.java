package pl.tomaszszewczyk.machine;

public abstract class RAM {
    public abstract int getWord(int address);

    public abstract void writeWord(int address, int word);

    public abstract byte getByte(int source_address);

    public abstract void writeByte(int dst_address, byte data);
}
