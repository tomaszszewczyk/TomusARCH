package pl.tomaszszewczyk.machine;

import java.io.Serializable;

public class Instruction {
    private byte opcode;
    private byte rdst;
    private byte rsrc;
    private int immediate;

    public static final int size = 7;
    public static final int opcodeOffset = 0;
    public static final int rdstOffset = 1;
    public static final int rsrcOffset = 2;
    public static final int immediateOffset = 3;

    public static final byte MOV   = (byte) 0x00;
    public static final byte SET   = (byte) 0x01;
    public static final byte LD    = (byte) 0x02;
    public static final byte ST    = (byte) 0x03;
    public static final byte LBD   = (byte) 0x04;
    public static final byte STB   = (byte) 0x05;
    public static final byte ADD   = (byte) 0x10;
    public static final byte SUB   = (byte) 0x11;
    public static final byte MUL   = (byte) 0x12;
    public static final byte DIV   = (byte) 0x13;
    public static final byte MOD   = (byte) 0x14;
    public static final byte OR    = (byte) 0x15;
    public static final byte AND   = (byte) 0x16;
    public static final byte XOR   = (byte) 0x17;
    public static final byte NOT   = (byte) 0x18;
    public static final byte SHL   = (byte) 0x19;
    public static final byte SHR   = (byte) 0x1A;
    public static final byte CMP   = (byte) 0x20;
    public static final byte JZ    = (byte) 0x21;
    public static final byte JE    = (byte) 0x21;
    public static final byte JNZ   = (byte) 0x22;
    public static final byte JNE   = (byte) 0x22;
    public static final byte JC    = (byte) 0x23;
    public static final byte JB    = (byte) 0x23;
    public static final byte JNC   = (byte) 0x24;
    public static final byte JAE   = (byte) 0x24;
    public static final byte JBE   = (byte) 0x25;
    public static final byte JA    = (byte) 0x26;
    public static final byte PUSH  = (byte) 0x30;
    public static final byte POP   = (byte) 0x31;
    public static final byte JMP   = (byte) 0x40;
    public static final byte JMPR  = (byte) 0x41;
    public static final byte CALL  = (byte) 0x42;
    public static final byte CALLR = (byte) 0x43;
    public static final byte RET   = (byte) 0x44;
    public static final byte CRL   = (byte) 0xF0;
    public static final byte CRS   = (byte) 0xF1;
    public static final byte OUTB  = (byte) 0xF2;
    public static final byte INB   = (byte) 0xF3;
    public static final byte IRET  = (byte) 0xF4;
    public static final byte OFF   = (byte) 0xFF;

    public Instruction(byte aOpcode, byte aRdst, byte aRsrc, int aImmediate) {
        opcode = aOpcode;
        rdst = aRdst;
        rsrc = aRsrc;
        immediate = aImmediate;
    }

    public byte getOpcode() {
        return opcode;
    }

    public byte getRdst() {
        return rdst;
    }

    public byte getRsrc() {
        return rsrc;
    }

    public int getImmediate() {
        return immediate;
    }
}
