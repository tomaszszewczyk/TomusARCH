package pl.tomaszszewczyk;

/**
 * Machine class
 */
public class Machine {

    private CPU cpu;

    public Machine(String source_file_name) {
        cpu = new CPU();
    }

    public CPU getCPU() {
        return cpu;
    }

    public void execute() {

    }
}
