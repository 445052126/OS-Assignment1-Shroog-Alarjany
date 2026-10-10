# 📝 MY_WORK: Student Information, Development Log, Reflection & Answers

> This is the **only file** your instructor reads to grade Parts 3 and 4 (documentation and video). Everything you write here must be **in your own words**.

---

## 🛑 STOP: Read This Before You Do Anything Else

> ### 1️⃣ Read the whole `README.md` first
> The `README.md` in this repository contains the full instructions: class descriptions, feature specifications, question prompts and the video script. **If you skip it, you will lose marks.**
>
> ### 2️⃣ Understand the full code before answering any question
> Open `SchedulerSimulation.java` and read it **from top to bottom**. You must be able to explain what `Process`, `run()`, `runToCompletion()`, `addProcessToQueue()`, `Thread.start()`, `Thread.join()` and `Thread.sleep()` do **before** you write a single answer in Parts B and C. Run the program at least once and watch the output.
>
> ### 3️⃣ Commit many times, not once
> A single commit, or all commits made in the last hour, costs you **-0.5 mark**. See the Commit Rules below.

**How to use this file:**
1. Fill in your **Student Information**.
2. Follow the steps in the **Work Roadmap**.
3. Update the **Development Log** every time you work on the assignment.
4. Do not delete any section header.

---

## 👤 Student Information

| Field | Your Answer |
|-------|-------------|
| **Full Name** | Shroog Alarjany |
| **Student ID** | 445052126 |
| **University Email** | 445052126@std.psau.edu.sa |
| **GitHub Username** | 445052126 |
| **Repository Link** | https://github.com/445052126/OS-Assignment1-Shroog-Alarjany |

---

## 🎥 Video Link

**Video Link**: T

> ⚠️ **WARNING:** The video must be publicly accessible ("Anyone with the link can view") on Google Drive, YouTube, or another cloud-sharing service.
>
> 💡 Test the video link in an incognito/private browser window before submitting.
>
> 📌 Name the video file:


---

## 🗺️ Work Roadmap

| Step | What to do | Where | Marks |
|:----:|------------|-------|:-----:|
| 0 | Read `README.md`, then read and run the full code | VS Code | – |
| 1 | Fork, rename, keep repo public, set student ID and commit | GitHub + Code | Part 1 |
| 2 | Feature 1: Process Priority and commit | Code | Part 2 |
| 3 | Feature 2: Context Switch Counter and commit | Code | Part 2 |
| 4 | Feature 3: Waiting Time Tracking and commit | Code | Part 2 |
| 5 | Development Log | MY_WORK.md | Part 3 |
| 6 | Reflection | MY_WORK.md | Part 3 |
| 7 | Technical Answers | MY_WORK.md | Part 3 |
| 8 | Record and upload video | Video + MY_WORK.md | Part 4 |
| 9 | Final check and submit GitHub link | Blackboard | – |

---

## 🔁 Commit Rules

I used separate meaningful commits for the main stages of the assignment, including the student ID, Priority feature, Context Switch feature, corrections, and Waiting Time feature.

Examples from my commit history include:

- `Updated student ID`
- `Feature 1: Added priority field to Process class`
- `Feature 2: Implemented context switch counter`
- `Fix Feature 2: Use static context switch counter`
- `Feature 3: Added waiting time tracking and summary table`

---

# Part A: Development Log (0.5 mark)

## Your Development Log

### Entry 1 - [October 4, 2026, 11:30 PM]

**What I did**: Reviewed the assignment requirements and starter repository.

**Details**:
- Read the assignment requirements.
- Reviewed the README instructions.
- Identified the three required programming features.
- Reviewed the GitHub repository naming requirements.
- Checked the requirements for `MY_WORK.md` and the video demonstration.
- Planned to complete each feature separately and use separate commits.

**Challenges**:

At first, the assignment had many different requirements involving Java, GitHub, documentation, commits, and a video. It was difficult to know which task should be completed first.

**Solution**:

I divided the work into smaller stages: repository setup, student ID, Feature 1, Feature 2, Feature 3, documentation, and video.

**Time spent**: About 25 minutes.

---

### Entry 2 - [October 7, 2026, 7:50 PM]

**What I did**: Forked the starter repository, renamed it, cloned it, and changed my student ID.

**Details**:
- Forked the official starter repository.
- Renamed the repository to `OS-Assignment1-Shroog-Alarjany`.
- Verified that the repository is public.
- Cloned my repository to my computer.
- Opened the project in VS Code.
- Changed the student ID in `SchedulerSimulation.java` to `445052126`.
- Created a separate commit for the student ID.

**Challenges**:

GitHub initially displayed "Couldn't check availability" while I was creating the fork. I also pasted an incorrect `git clone` command that contained extra text, which caused a PowerShell syntax error.

**Solution**:

I retried the fork process and used only the correct HTTPS GitHub URL in the `git clone` command. After that, the repository cloned correctly.

**Time spent**: About 40 minutes.

---

### Entry 3 - [October 7, 2026, 8:10 PM]

**What I did**: Fixed GitHub authentication and implemented Feature 1: Process Priority.

**Details**:
- GitHub originally rejected my push with error 403.
- Found that Git was using credentials for another GitHub account.
- Removed the old GitHub credentials from Windows Credential Manager.
- Signed in again using the correct GitHub account.
- Added a `priority` field to the `Process` class.
- Generated a random priority between 1 and 10.
- Displayed the priority when a process entered the ready queue.
- Kept the original Round-Robin FIFO order unchanged.

**Challenges**:

The first push was rejected because Git was authenticated with the wrong account. I also had a problem with the parameter order in the `Process` constructor while adding the priority value.

**Solution**:

I removed the incorrect GitHub credential, signed in with the correct account, corrected the constructor order, and tested the code until VS Code showed no problems.

**Time spent**: About 55 minutes.

---

### Entry 4 - [October 7, 2026, 8:30 PM]

**What I did**: Implemented Feature 2: Context Switch Counter.

**Details**:
- Added a static `contextSwitchCount` variable.
- Incremented the counter when a process begins execution.
- Displayed the total number of context switches at the end of the simulation.
- Tested the feature successfully.
- One of my test runs showed `Total Context Switches: 24`.
- Created separate commits for the feature and its correction.

**Challenges**:

At first, I placed `currentThread.join()` in the wrong location. I also initially used a local context switch counter instead of the required static counter.

**Solution**:

I restored `Thread.join()` to its correct `try/catch` block. Then I changed the counter into a static class variable and incremented it before `currentThread.start()`.

**Time spent**: About 45 minutes.

---

### Entry 5 - [October 10, 2026, 10:15 PM]

**What I did**: Implemented Feature 3: Waiting Time Tracking.

**Details**:
- Added fields for process timing.
- Used `System.currentTimeMillis()` to record timing information.
- Recorded when each process entered the ready queue.
- Calculated the total waiting time for each process.
- Stored all process objects for the final results.
- Added a summary table containing Process Name, Burst Time, Waiting Time, and Turnaround Time.
- Tested the complete program successfully with no errors.

**Challenges**:

The most difficult part was tracking waiting time because the same process can return to the ready queue several times during Round-Robin scheduling.

**Solution**:

I recorded a new ready time whenever a process entered the ready queue. When the scheduler selected the process again, I calculated the difference between the current time and the ready time and added it to the process's total waiting time.

**Time spent**: About 60 minutes.

---

### Entry 6 - [October 10, 2026, 10:40 PM]

**What I did**: Tested the final program and started completing the documentation.

**Details**:
- Verified that Priority is displayed correctly.
- Verified that Context Switches are counted.
- Verified that the Waiting Time summary table appears.
- Confirmed that the program runs with zero errors.
- Reviewed the commit history.
- Started completing the Development Log, Reflection, and Technical Answers.

**Challenges**:

I needed to make sure my written explanations described my actual code and output instead of only giving general textbook definitions.

**Solution**:

I reviewed my own simulation output and connected each answer to real methods and results from `SchedulerSimulation.java`.

**Time spent**: About 30 minutes.

---

## Development Log Summary

**Total time spent on assignment**: About 4 hours and 15 minutes.

**Most challenging part**:

The most challenging part was tracking the waiting time correctly because processes can enter the ready queue more than once.

**Most interesting learning**:

The most interesting part was seeing how threads, the ready queue, time quantum, context switches, and waiting time work together in a Round-Robin CPU scheduler.

**What I would do differently next time**:

Next time, I would plan the fields and methods required for each feature before editing the code. This would help me avoid small corrections during implementation.

---

# Part B: Reflection (0.5 mark)

## Question 1: What did you learn about multithreading?

**Your Answer:**

I learned that a Java thread can execute a task represented by a class that implements `Runnable`. In this assignment, every simulated process was connected to a real Java thread using `new Thread(process)`. I learned that `Thread.start()` begins the execution of the thread, while `Thread.join()` makes the main scheduler wait until that thread finishes its current execution. I also used `Thread.sleep()` to simulate the CPU time used by each process. Before this assignment, I understood multithreading mainly as a theoretical concept, but the scheduler helped me see how threads actually move through execution. I also learned that managing threads carefully is important because every process should receive CPU time fairly.

---

## Question 2: What was the most challenging part of this assignment?

**Your Answer:**

The most challenging part of this assignment was implementing the waiting time tracking. A process does not wait only one time because Round-Robin scheduling can send the same process back to the ready queue several times. I had to understand exactly when a process starts waiting and when that waiting period should stop. At first, it was easy to confuse waiting time with execution time. I also needed to make sure that my new timing code did not change the original FIFO behavior of the ready queue. After testing the summary table, the relationship between waiting time, burst time, and turnaround time became much clearer to me.

---

## Question 3: How did you overcome the challenges you faced?

**Your Answer:**

I solved the problems by making small changes and testing the program after each step. When an error appeared, I checked the exact location in VS Code instead of changing several parts of the program at once. For example, I corrected the location of `Thread.join()` and later changed the context switch counter from a local variable to a static variable. I also made sure that VS Code showed zero problems before I created a commit. For the waiting time feature, I used `System.currentTimeMillis()` to record when the process entered and left the ready queue. Making separate Git commits also helped me understand which change belonged to each feature.

---

## Question 4: How can you apply multithreading concepts in real-world applications?

**Your Answer:**

Multithreading can be useful in applications that need several tasks to make progress without making the whole program unresponsive. A web browser can use different threads to load network data, display web pages, and respond to user input. A music application can use one thread for playing audio while another thread updates the interface or downloads information. Operating systems also use scheduling concepts to share CPU time between many running programs. The Round-Robin simulation showed me how short time slices can improve fairness and responsiveness. The same ideas of waiting, scheduling, threads, and context switching can be applied to many modern applications.

---

### Optional: What would you like to learn more about?

I would like to learn more about thread synchronization, race conditions, deadlocks, and how real operating systems choose between different CPU scheduling algorithms.

### Optional: How confident do you feel about multithreading concepts now?

Intermediate. I understand thread creation, `Thread.start()`, `Thread.join()`, `Thread.sleep()`, the ready queue, and basic Round-Robin scheduling better now. I still need more practice with synchronization and situations where several threads access the same shared data.

### Optional: Feedback on the assignment

The assignment was useful because it connected Java programming with operating-system scheduling concepts. Implementing and testing each feature separately helped me understand the concepts better.

---

# Part C: Technical Answers (0.5 mark)

## Question 1: Thread vs Process

**Your Answer:**

A process is usually an independent running program with its own memory space, while a thread is a smaller execution unit that normally shares memory and resources with other threads in the same program. Threads usually have lower creation overhead and can communicate through shared memory more easily than separate processes. In this assignment, the class called `Process` is only a simulated process, while the real Java execution happens through a thread created using `new Thread(process)` inside `addProcessToQueue()`. Threads were useful because the scheduler could manage all of the simulated processes inside one Java application while sharing the same ready queue and process map.

---

## Question 2: Ready Queue Behavior

**Your Answer:**

When a process does not finish within its time quantum, the scheduler places it back at the end of the ready queue so that it can receive another CPU turn later. In my run, the time quantum was 4000 ms and P1 had a burst time of 9491 ms. P1 first used 4000 ms, then another 4000 ms, and finally completed the remaining 1491 ms, which means it was re-queued two times before finishing. This re-queueing is important because it prevents one long process from keeping the CPU while the other processes are waiting.

Example from my output:

```text
P1 executing quantum [4000ms]
P1 completed quantum 4000ms
Remaining time: 5491ms
P1 yields CPU for context switch

P1 added to ready queue

P1 executing quantum [4000ms]
P1 completed quantum 4000ms
Remaining time: 1491ms
P1 yields CPU for context switch

P1 added to ready queue

P1 executing quantum [1491ms]
P1 completed quantum 1491ms
Remaining time: 0ms
P1 finished execution!