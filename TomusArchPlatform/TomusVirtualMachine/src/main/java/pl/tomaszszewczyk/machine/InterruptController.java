package pl.tomaszszewczyk.machine;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Interrupt Controller emulator class, contains FILO queue
 *
 * @author Tomasz Szewczyk
 */
public class InterruptController {
    /**
     * Main queue containing interrupts
     */
    private Queue<Interrupt> queue = new LinkedBlockingQueue<Interrupt>();

    /**
     * Check if there is any interrupt to be read.
     * Thread safe
     *
     * @return  true/false
     */
    public boolean isAny() {
        return !queue.isEmpty();
    }

    /**
     * Read interrupt
     * Thread safe
     *
     * @return  Interrupt
     */
    public Interrupt getInt() {
        return queue.remove();
    }

    /**
     * Report interrupt
     * Thread safe
     *
     * @param interrupt
     */
    public void report(Interrupt interrupt) {
        queue.add(interrupt);
    }
}
