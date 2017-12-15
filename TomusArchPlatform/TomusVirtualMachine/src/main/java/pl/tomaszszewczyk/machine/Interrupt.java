package pl.tomaszszewczyk.machine;

/**
 * Interrupt type enum
 */
public enum Interrupt {
    MemoryRangeExceeded,
    DivideByZero,
    GeneralError,
    TimerInterrupt,
    ConsoleInterrupt,
}
