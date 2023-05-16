# CPUSimluator
I created a CPU simlutor, that has four different comparators. One takes no parameters, the other takes a comparator,
the next takes an input file and comparator, and the final takes an output file along with a comparator and input file.
Within this I have also created five comparators that the user can use, they are listed as below.

--First-Come-First-Served (FCFS)
Jobs are selected from the ready queue based on when they were first created.
--Round Robin (RR)
Jobs are selected in a round robin fashion. New jobs are added to the end of the ready
queue.
--Shortest Job First (SJF)
When each job is created, it is given the total number of CPU time slices need to
complete the job. In SJF scheduling, the job with the shortest length is executed next.
--Shortest Remaining Time (SRT)
Each job keeps track of how many CPU cycles are remaining to complete that job. In
SRT scheduling, the job with the shortest remaining time is executed next.
--Highest Response Ratio (HRR)
Each job keeps track of its wait time, which is the number of time slices the job has
been in the ready queue, but not executed. The wait time is combined with the job's
length to compute what's called the job's response ratio (see formula below). In HRR
scheduling, the job with the highest response ration is executed next.
