package pl.tomaszszewczyk.machine;

public class Console {
    private ReadWritePort readWritePort;
    private DataAvailablePort dataAvailablePort;
    private ControlPort controlPort;

    class ReadWritePort implements Port {

        public int read() {
            return 0;
        }

        public void write(int value) {

        }
    }

    class DataAvailablePort implements Port {

        public int read() {
            return 0;
        }

        public void write(int value) {

        }
    }

    class ControlPort implements Port {

        public int read() {
            return 0;
        }

        public void write(int value) {

        }
    }

    public Port getReadWritePort() {
        return readWritePort;
    }

    public Port getDataAvailablePort() {
        return null;
    }

    public Port getControlPort() {
        return null;
    }
}
