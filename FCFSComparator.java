/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natemoe
 */
import java.util.Comparator;

public class FCFSComparator implements Comparator<Job> {
    @Override
    public int compare(Job job1, Job job2) {
        return job1.createdTime - job2.createdTime;
    }
}
