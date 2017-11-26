package pl.tomaszszewczyk.machine;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class InterruptController {
    private Queue<Interrupt> queue = new LinkedBlockingQueue<Interrupt>();

    public boolean isAny() {
        return !queue.isEmpty();
    }

    public Interrupt getInt() {
        return queue.remove();
    }

    public void report(Interrupt interrupt) {
        queue.add(interrupt);
    }
}
