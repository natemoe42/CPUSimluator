/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natemoe
 */
import java.util.Comparator;

public class HRRComparator implements Comparator<Job> {
    @Override
    public int compare(Job job1, Job job2) {
        double responseRatio1 = (double) (job1.waitTime + job1.length) / job1.length;
        double responseRatio2 = (double) (job2.waitTime + job2.length) / job2.length;
        return Double.compare(responseRatio2, responseRatio1);
    }
}
