package discriminantanalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DiscriminantAnalysisFileHandler {

    private double b[][], a[];
    private int variables[], groups[];
    
    public double[][][] getMatrix(int p, String files[]) throws FileNotFoundException {
        double values[][][] = new double[files.length][][];
        for (int i = 0; i < files.length; i++) {
            Scanner scan;
            File file = new File(files[i]);

            ArrayList<Double[]> matrix = new ArrayList<>();

            scan = new Scanner(file);

            while (scan.hasNextDouble()) {
                Double[] row = new Double[p];
                for (int j = 0; j < p; j++) {
                    row[j] = scan.nextDouble();
                }
                matrix.add(row);
            }

            values[i] = new double[matrix.size()][];

            values[i] = new double[matrix.size()][];
            for (int j = 0; j < values[i].length; j++) {
                values[i][j] = new double[p];
                for (int k = 0; k < p; k++) {
                    values[i][j][k] = matrix.get(j)[k];
                }
            }
            scan.close();

        }

        return values;
    }

    public void writeFunctionData(int variables[], int groups[], double b[][], double a[],
    		String variablesFileName, String groupsFileName, String bFileName, String aFileName) 
    				throws FileNotFoundException {
        PrintWriter out = new PrintWriter(bFileName);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                String s = b[i][j] + " ";
                s = s.replaceAll("[.]", ",");
                out.print(s);
            }
            out.println();
        }
        out.close();

        out = new PrintWriter(aFileName);
        for (int i = 0; i < a.length; i++) {
            String s = a[i] + " ";
                s = s.replaceAll("[.]", ",");
            out.print(s);
        }
        out.close();

        out = new PrintWriter(variablesFileName);
        for (int i = 0; i < variables.length; i++) {
            out.print(variables[i] + " ");
        }
        out.close();
        
        out = new PrintWriter(groupsFileName);
        for (int i = 0; i < groups.length; i++) {
            out.print(groups[i] + " ");
        }
        out.close();
    }

    public void readFunctionData(String varFileName, String groupFileName, String bFileName, String aFileName) throws FileNotFoundException {
        ArrayList<Integer> var = new ArrayList<>();

        File file = new File(varFileName);
        Scanner scan = new Scanner(file);

        while (scan.hasNextInt()) {
            var.add(scan.nextInt());
        }

        scan.close();
        
        variables = new int[var.size()];
        for(int i = 0; i < var.size(); i ++) {
            variables[i] = var.get(i);
        }
        
        ArrayList<Integer> groupsList = new ArrayList<>();
        file = new File(groupFileName);
        scan = new Scanner(file);
        while (scan.hasNextInt()) {
        	groupsList.add(scan.nextInt());
        }
        scan.close();
        groups = new int[groupsList.size()];
        for(int i = 0; i < groupsList.size(); i ++) {
            groups[i] = groupsList.get(i);
        }

        ArrayList<Double> a1 = new ArrayList<>();
        file = new File(aFileName);
        scan = new Scanner(file);

        while (scan.hasNextDouble()) {
            a1.add(scan.nextDouble());
        }
        scan.close();
        
        a = new double[a1.size()];
        for(int i = 0; i < a1.size(); i ++) {
            a[i] = a1.get(i);
        }

        
        b = new double[a.length][variables.length];
        file = new File(bFileName);
        scan = new Scanner(file);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = scan.nextDouble();
            }
        }
        scan.close();
    }
    
    public double[][] getB() {
        return b;
    }
    
    public double[] getA() {
        return a;
    }
    
    public int[] getVariables() {
        return variables;
    }
    
    public int[] getGroups() {
        return groups;
    }
}

