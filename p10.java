import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {

        int bucketRemainder = 0;
        int bucketCapacity = 4;
        int rate = 3;
        int sent = 0;
        int received;

        int packets[] = new int[50];

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of packets: ");
        int n = sc.nextInt();

        System.out.println("Enter packet sizes:");
        for (int i = 1; i <= n; i++) {
            packets[i] = sc.nextInt();
        }

        System.out.println("\nClock\tPacket\tAccept/Drop\tSent\tRemaining");

        for (int i = 1; i <= n; i++) {

            if (packets[i] != 0) {
                if ((bucketRemainder + packets[i]) > bucketCapacity) {
                    received = 1;
                } else {
                    received = packets[i];
                    bucketRemainder += packets[i];
                }
            } else {
                received = 0;
            }

            if (bucketRemainder != 0) {
                if (bucketRemainder < rate) {
                    sent = bucketRemainder;
                    bucketRemainder = 0;
                } else {
                    sent = rate;
                    bucketRemainder -= rate;
                }
            } else {
                sent = 0;
            }

            if (received == 1) {
                System.out.println(i + "\t" + packets[i] + "\tDrop\t\t" + sent + "\t" + bucketRemainder);
            } else {
                System.out.println(i + "\t" + packets[i] + "\t" + received + "\t\t" + sent + "\t" + bucketRemainder);
            }
        }

        sc.close();
    }
}
