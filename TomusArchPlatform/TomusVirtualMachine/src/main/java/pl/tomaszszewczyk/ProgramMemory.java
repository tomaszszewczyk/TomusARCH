package pl.tomaszszewczyk;

interface ProgramMemory
{
    Instruction get_instruction(int address);
    byte get_byte(int address);
}