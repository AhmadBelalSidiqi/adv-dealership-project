package com.yearup.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String FILE_Path = "src/main/resources/contracts.csv";

    public static void saveContract(Contract contract) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_Path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(contract.getCsvFormated());
            bufferedWriter.close();

        } catch (IOException e) {
            System.err.println("File not found: " + e);
        }

    }
}
