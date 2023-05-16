/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natemoe
 */
import java.util.Comparator;
import java.util.ArrayList;

public class ReadyQueue extends Heap<Job>
{    
    public ReadyQueue(Comparator<Job> comparator)
    {
        super(comparator);        
    }
    
    public void increaseWaitTime()
    {
        ArrayList<Job> list = new ArrayList<>();
        
        while(!this.isEmpty())
        {
            Job job = this.remove();
            job.waitTime++;
            list.add(job);
        }
        
        while(!list.isEmpty())
        {
            Job job = list.remove(0);
            this.add(job);            
        }
    }
}