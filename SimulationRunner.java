/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natemoe
 */
import java.util.Comparator;

public class SimulationRunner {
    public static void main(String[] args) {
        String fileName = "input.txt";
        Comparator<Job> comparator = new HRRComparator();
        CPUSimulator simulator = new CPUSimulator(fileName, comparator);
        simulator.start();
    }
}
