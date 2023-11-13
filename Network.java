import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Network {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the number of WI-FI Connections?");
        int maxNumOfConnections = scanner.nextInt();
        System.out.println("What is the number of devices Clients want to connect?");
        int totalNumOfDevices = scanner.nextInt();
        Router router = new Router(maxNumOfConnections);
        Device[] devices = new Device[totalNumOfDevices];

        for (int i = 0; i < totalNumOfDevices; i++) {
            String name = scanner.next();
            String type = scanner.next();
            devices[i] = new Device(name, type, router);
        }
        Path outputPath = Paths.get("output.txt");

        try (PrintStream fileOut = new PrintStream(Files.newOutputStream(outputPath))) {
            System.setOut(fileOut);

            for (Device device : devices) {
                device.start();
            }
            for (Device device : devices) {
                try {
                    device.join();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted while waiting for device to finish:" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
