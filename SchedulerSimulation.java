import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

// ANSI Color Codes for enhanced terminal output
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
}

// Class representing a process that implements Runnable to be run by a thread
class Process implements Runnable {
    private String name; // Name of the process
    private int burstTime; // Total time the process requires to complete
    private int timeQuantum; // Time slice allowed per CPU access
    private int remainingTime; // Time left for the process to finish
    private int priority; // Feature 1: Process priority

    public int getPriority() {
        return priority;
    }

    // Constructor
    public Process(String name, int burstTime, int timeQuantum, int priority) {
        this.name = name;
        this.burstTime = burstTime;
        this.timeQuantum = timeQuantum;
        this.remainingTime = burstTime;
        this.priority = priority;
    }

    // This method will be called when the thread for this process is started
    @Override
    public void run() {
        // Simulate running for either the time quantum or remaining time
        int runTime = Math.min(timeQuantum, remainingTime);

        // Show quantum execution starting
        String quantumBar = createProgressBar(0, 15);

        System.out.println(Colors.BRIGHT_GREEN + "  ▶ " +
                          Colors.BOLD + Colors.CYAN + name +
                          Colors.RESET + Colors.GREEN + " executing quantum" +
                          Colors.RESET + " [" + runTime + "ms] ");

        try {
            // Simulate quantum execution with progress updates
            int steps = 5;
            int stepTime = runTime / steps;

            for (int i = 1; i <= steps; i++) {
                Thread.sleep(stepTime);

                int quantumProgress = (i * 100) / steps;
                quantumBar = createProgressBar(quantumProgress, 15);

                System.out.print("\r  " +
                                 Colors.YELLOW + "⚡" +
                                 Colors.RESET +
                                 " Quantum progress: " +
                                 quantumBar);
            }

            System.out.println();

        } catch (InterruptedException e) {
            System.out.println(Colors.RED +
                               "\n  ✗ " + name +
                               " was interrupted." +
                               Colors.RESET);
        }

        // Deduct run time from remaining time
        remainingTime -= runTime;

        int overallProgress =
                (int) (((double) (burstTime - remainingTime) / burstTime) * 100);

        String overallProgressBar =
                createProgressBar(overallProgress, 20);

        System.out.println(Colors.YELLOW + "  ⏸ " +
                          Colors.CYAN + name +
                          Colors.RESET +
                          " completed quantum " +
                          Colors.BRIGHT_YELLOW + runTime + "ms" +
                          Colors.RESET +
                          " │ Overall progress: " +
                          overallProgressBar);

        System.out.println(Colors.MAGENTA +
                           "     Remaining time: " +
                           remainingTime + "ms" +
                           Colors.RESET);

        // If the process still has remaining time
        if (remainingTime > 0) {
            System.out.println(Colors.BLUE + "  ↻ " +
                              Colors.CYAN + name +
                              Colors.RESET +
                              " yields CPU for context switch" +
                              Colors.RESET);

        } else {
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " +
                              Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET +
                              Colors.BRIGHT_GREEN +
                              " finished execution!" +
                              Colors.RESET);
        }

        System.out.println();
    }

    // Helper method to create a visual progress bar
    private String createProgressBar(int progress, int width) {
        int filled = (progress * width) / 100;
        StringBuilder bar = new StringBuilder("[");

        for (int i = 0; i < width; i++) {
            if (i < filled) {
                bar.append(Colors.GREEN + "█" + Colors.RESET);
            } else {
                bar.append(Colors.WHITE + "░" + Colors.RESET);
            }
        }

        bar.append("] ").append(progress).append("%");
        return bar.toString();
    }

    // Method to run the last process to completion
    public void runToCompletion() {
        try {
            System.out.println(Colors.BRIGHT_CYAN + "  ⚡ " +
                              Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET +
                              Colors.BRIGHT_CYAN +
                              " is the last process, running to completion" +
                              Colors.RESET +
                              " [" + remainingTime + "ms]");

            Thread.sleep(remainingTime);

            remainingTime = 0;

            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " +
                              Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET +
                              Colors.BRIGHT_GREEN +
                              " finished execution!" +
                              Colors.RESET);

            System.out.println();

        } catch (InterruptedException e) {
            System.out.println(Colors.RED +
                               "  ✗ " + name +
                               " was interrupted." +
                               Colors.RESET);
        }
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    // Check if the process has finished
    public boolean isFinished() {
        return remainingTime <= 0;
    }
}

public class SchedulerSimulation {

    // Feature 2: Static context switch counter
    private static int contextSwitchCount = 0;

    public static void main(String[] args) {

        // Student ID used to seed random number generator
        int studentID = 445052126;

        Random random = new Random(studentID);

        // Define time quantum
        int timeQuantum = 2000 + random.nextInt(4) * 1000;

        // Generate random number of processes between 10 and 20
        int numProcesses = 10 + random.nextInt(11);

        // Queue to manage processes in FIFO order
        Queue<Thread> processQueue = new LinkedList<>();

        // Map to associate each thread with its process
        Map<Thread, Process> processMap = new HashMap<>();

        // Print simulation header
        System.out.println("\n" +
                          Colors.BOLD +
                          Colors.BRIGHT_CYAN +
                          "╔═══════════════════════════════════════════════════════════════════════════════════════╗" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_CYAN + "║" +
                          Colors.RESET +
                          Colors.BG_BLUE +
                          Colors.BRIGHT_WHITE +
                          Colors.BOLD +
                          "                          CPU SCHEDULER SIMULATION                                " +
                          Colors.RESET +
                          Colors.BOLD +
                          Colors.BRIGHT_CYAN + "║" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_CYAN +
                          "╠═══════════════════════════════════════════════════════════════════════════════════════╣" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_CYAN + "║" +
                          Colors.RESET +
                          Colors.YELLOW +
                          "  ⚙ Processes:     " +
                          Colors.RESET +
                          Colors.BRIGHT_YELLOW +
                          String.format("%-65s", numProcesses) +
                          Colors.BOLD +
                          Colors.BRIGHT_CYAN + "║" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_CYAN + "║" +
                          Colors.RESET +
                          Colors.YELLOW +
                          "  ⏱ Time Quantum:  " +
                          Colors.RESET +
                          Colors.BRIGHT_YELLOW +
                          String.format("%-65s", timeQuantum + "ms") +
                          Colors.BOLD +
                          Colors.BRIGHT_CYAN + "║" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_CYAN + "║" +
                          Colors.RESET +
                          Colors.YELLOW +
                          "  🔑 Student ID:    " +
                          Colors.RESET +
                          Colors.BRIGHT_YELLOW +
                          String.format("%-65s", studentID) +
                          Colors.BOLD +
                          Colors.BRIGHT_CYAN + "║" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_CYAN +
                          "╚═══════════════════════════════════════════════════════════════════════════════════════╝" +
                          Colors.RESET +
                          "\n");

        // Create processes
        for (int i = 1; i <= numProcesses; i++) {

            // Random burst time
            int burstTime =
                    timeQuantum / 2 +
                    random.nextInt(2 * timeQuantum + 1);

            // Feature 1: Generate priority from 1 to 10
            int priority = 1 + random.nextInt(10);

            // Create process
            Process process =
                    new Process(
                            "P" + i,
                            burstTime,
                            timeQuantum,
                            priority
                    );

            // Add process to ready queue
            addProcessToQueue(
                    process,
                    processQueue,
                    processMap
            );
        }

        // Start scheduler simulation
        System.out.println(Colors.BOLD +
                          Colors.GREEN +
                          "╔════════════════════════════════════════════════════════════════════════════════╗" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.GREEN + "║" +
                          Colors.RESET +
                          Colors.BG_GREEN +
                          Colors.WHITE +
                          Colors.BOLD +
                          "                        ▶  SCHEDULER STARTING  ◀                               " +
                          Colors.RESET +
                          Colors.BOLD +
                          Colors.GREEN + "║" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.GREEN +
                          "╚════════════════════════════════════════════════════════════════════════════════╝" +
                          Colors.RESET +
                          "\n");

        // Loop to manage scheduling
        while (!processQueue.isEmpty()) {

            // Get next thread from queue
            Thread currentThread = processQueue.poll();

            // Print current ready queue
            System.out.println(Colors.BOLD +
                              Colors.MAGENTA +
                              "┌─ Ready Queue " +
                              "─".repeat(65) +
                              Colors.RESET);

            System.out.print(Colors.MAGENTA +
                             "│ " +
                             Colors.RESET +
                             Colors.BRIGHT_WHITE +
                             "[" +
                             Colors.RESET);

            int queueCount = 0;

            for (Thread thread : processQueue) {

                Process process =
                        processMap.get(thread);

                if (queueCount > 0) {
                    System.out.print(
                            Colors.WHITE +
                            " → " +
                            Colors.RESET
                    );
                }

                System.out.print(
                        Colors.BRIGHT_CYAN +
                        process.getName() +
                        Colors.RESET
                );

                queueCount++;
            }

            if (queueCount == 0) {
                System.out.print(
                        Colors.YELLOW +
                        "empty" +
                        Colors.RESET
                );
            }

            System.out.println(
                    Colors.BRIGHT_WHITE +
                    "]" +
                    Colors.RESET
            );

            System.out.println(
                    Colors.BOLD +
                    Colors.MAGENTA +
                    "└" +
                    "─".repeat(79) +
                    Colors.RESET +
                    "\n"
            );

            // Feature 2:
            // Count each time a process begins execution
            contextSwitchCount++;

            // Start the thread
            currentThread.start();

            try {
                // Wait for the thread to finish its time quantum
                currentThread.join();

            } catch (InterruptedException e) {
                System.out.println(
                        "Main thread interrupted."
                );
            }

            // Retrieve process associated with thread
            Process process =
                    processMap.get(currentThread);

            // Check if process is not finished
            if (!process.isFinished()) {

                // If there are more processes in the queue
                if (!processQueue.isEmpty()) {

                    // Re-enqueue process
                    addProcessToQueue(
                            process,
                            processQueue,
                            processMap
                    );

                } else {

                    // Last process runs to completion
                    System.out.println(
                            Colors.BRIGHT_YELLOW +
                            "  ⚠ " +
                            Colors.CYAN +
                            process.getName() +
                            Colors.RESET +
                            Colors.YELLOW +
                            " is the last process → running to completion" +
                            Colors.RESET
                    );

                    process.runToCompletion();
                }
            }
        }

        // Feature 2: Display total context switches
        System.out.println(
                Colors.BRIGHT_YELLOW +
                "Total Context Switches: " +
                contextSwitchCount +
                Colors.RESET
        );

        // End of scheduler simulation
        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_GREEN +
                          "╔════════════════════════════════════════════════════════════════════════════════╗" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_GREEN + "║" +
                          Colors.RESET +
                          Colors.BG_GREEN +
                          Colors.WHITE +
                          Colors.BOLD +
                          "                     ✓  ALL PROCESSES COMPLETED  ✓                            " +
                          Colors.RESET +
                          Colors.BOLD +
                          Colors.BRIGHT_GREEN + "║" +
                          Colors.RESET);

        System.out.println(Colors.BOLD +
                          Colors.BRIGHT_GREEN +
                          "╚════════════════════════════════════════════════════════════════════════════════╝" +
                          Colors.RESET +
                          "\n");
    }

    // Add process to queue
    public static void addProcessToQueue(
            Process process,
            Queue<Thread> processQueue,
            Map<Thread, Process> processMap) {

        // Create new thread
        Thread thread = new Thread(process);

        // Add thread to ready queue
        processQueue.add(thread);

        // Map thread to process
        processMap.put(thread, process);

        // Print ready message
        System.out.println(
                Colors.BLUE +
                "  ➕ " +
                Colors.BOLD +
                Colors.CYAN +
                process.getName() +
                Colors.RESET +
                Colors.BLUE +
                " added to ready queue" +
                Colors.RESET +
                " │ Burst time: " +
                Colors.YELLOW +
                process.getBurstTime() +
                "ms" +
                Colors.RESET +
                " │ Priority: " +
                Colors.BRIGHT_YELLOW +
                process.getPriority() +
                Colors.RESET
        );
    }
}