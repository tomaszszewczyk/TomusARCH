package pl.tomaszszewczyk.machine;

public interface Port {
    int read();

    void write(int value);
}
