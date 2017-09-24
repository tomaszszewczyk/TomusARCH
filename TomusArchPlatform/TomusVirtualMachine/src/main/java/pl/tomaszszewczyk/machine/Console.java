package pl.tomaszszewczyk.machine;

public class Console {
    private ReadWritePort readWritePort = new ReadWritePort();
    private DataAvailablePort dataAvailablePort = new DataAvailablePort();
    private ControlPort controlPort = new ControlPort();

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
        return dataAvailablePort;
    }

    public Port getControlPort() {
        return controlPort;
    }
}
