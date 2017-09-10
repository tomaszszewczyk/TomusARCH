package pl.tomaszszewczyk;

public interface RAM {
    int getWord(int address);

    void writeWord(int address, int word);

    byte getByte(int source_address);

    void writeByte(int dst_address, byte data);
}
