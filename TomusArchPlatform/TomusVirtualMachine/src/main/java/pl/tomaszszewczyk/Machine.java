package pl.tomaszszewczyk;

public class Machine {

    private CPU cpu;
    private RAM ram;

    public Machine(String source_file_name) {
        cpu = new CPU();
    }

    public CPU getCPU() {
        return cpu;
    }

    public RAM getRAM() {
        return ram;
    }

    public void execute() throws Exception {
        throw new Exception("Not implemented");
    }

    public void powerOFF() {

    }
}
