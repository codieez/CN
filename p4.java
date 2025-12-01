package CRC;

import java.util.Scanner;

class CRC {
    final String gp = "10001000000100001";

    String computeChecksum(String data) {
        char tempDataCRC[] = data.toCharArray();
        int noOfIteration = data.length() - 17;

        for (int i = 0; i <= noOfIteration; i++) {
            if (tempDataCRC[i] == '1') {
                for (int j = 0; j < gp.length(); j++)
                    if (tempDataCRC[i + j] == gp.charAt(j))
                        tempDataCRC[i + j] = '0';
                    else
                        tempDataCRC[i + j] = '1';
            }
        }

        String crc = new String(tempDataCRC, 4, 17);
        return crc;
    }

    String getDataCrc(String data) {
        String crc = computeChecksum(data + "00000000000000000");
        return data + crc;
    }

    public boolean validate(String dataCrc) {
        String rem = computeChecksum(dataCrc);
        for (int i = 0; i < rem.length(); i++)
            if (rem.charAt(i) == '1')
                return false;
        return true;
    }
}

public class Test {
    public static void main(String[] args) {
        CRC crcObject = new CRC();
        System.out.println("Enter Data bits : ");
        Scanner input = new Scanner(System.in);
        String data = input.next();
        String dataCRC = crcObject.getDataCrc(data);
        System.out.println("CRC : " + dataCRC);
        System.out.println("Enter Received Data bits : ");
        String receivedData = input.next();
        boolean v = crcObject.validate(receivedData);
        if (v)
            System.out.println("Correct Data");
        else
            System.out.println("Invalid Data");
    }
}
