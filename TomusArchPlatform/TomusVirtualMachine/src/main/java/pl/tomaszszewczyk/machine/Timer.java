package pl.tomaszszewczyk.machine;

public class Timer {
    private ControlPort controlPort = new ControlPort();
    private CounterPort counterPort = new CounterPort();

    public Port getControlPort() {
        return controlPort;
    }

    public Port getCounterPort() {
        return counterPort;
    }

    class ControlPort implements Port {

        public int read() {
            return 0;
        }

        public void write(int value) {
        }
    }

    class CounterPort implements Port {

        public int read() {
            return 0;
        }

        public void write(int value) {

        }
    }
}
