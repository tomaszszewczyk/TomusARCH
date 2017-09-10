package pl.tomaszszewczyk;

import java.util.HashMap;
import java.util.Map;

public class CPU {
    public enum Register {
        R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15
    }

    private Map<Register, Integer> registerMap;

    public CPU()
    {
        registerMap = new HashMap<Register, Integer>();
        for (Register register: Register.values()) {
            registerMap.put(register, 0);
        }
    }

    public void setRegister(Register dst, int val) {
        registerMap.put(dst, val);
    }


    public int getRegister(Register name) {
        return registerMap.get(name);
    }
}
