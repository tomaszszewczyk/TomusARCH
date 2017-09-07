package pl.tomaszszewczyk;

interface RandomAccessMemory
{
    byte get_byte(int address);
    void write_byte(int address, int value);
}