package edu.caltech.cs2.project04;

import edu.caltech.cs2.datastructures.CircularArrayFixedSizeQueue;
import edu.caltech.cs2.interfaces.IFixedSizeQueue;

import java.util.Random;


public class CircularArrayFixedSizeQueueGuitarString {
    private IFixedSizeQueue<Double> caq;
    private static final int SAMPLING_RATE = 44100; //CAPS
    private static final double ENERGY_DECAY_FAC = 0.996;
    private static Random RANDOM = new Random();


    public CircularArrayFixedSizeQueueGuitarString(double frequency) {
        int n = (int) Math.ceil(SAMPLING_RATE / frequency);
        this.caq = new CircularArrayFixedSizeQueue<>(n);
        for (int i = 0; i< n; i++) {
            caq.enqueue(0.0);
        }
    }

    public int length() {
        return caq.size();
    }

    public void pluck() {
        caq.clear();
        for (int i = 0; i < caq.capacity(); i++) {
            Double toEnq = RANDOM.nextDouble() - .5;
            caq.enqueue(toEnq);
        }
    }

    public void tic() {
        Double x = caq.dequeue();
        Double x2 = caq.peek();

        Double toEnq = (((x + x2)*.5)  * ENERGY_DECAY_FAC);
        caq.enqueue(toEnq);
    }

    public double sample() {
        return caq.peek();
    }
}
