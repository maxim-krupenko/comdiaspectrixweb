package discriminantanalysis;

import java.util.ArrayList;

import Jama.Matrix;

public class DiscriminantAnalysis {

    private double X[][][];
    private int g, p, q, n;
    private int[] m;

    private int maxStep;
    private double TOL_threshold;
    private double F_to_enter_threshold;
    private double F_to_remove_threshold;
    private int flag;
    private int step;
    private double W_[][], T_[][];
    private double W[][], T[][];
    private double F_to_enter[], F_to_remove[];

    private int variables[];
    private double b[][], a[];
    private double xmean[][];

    public DiscriminantAnalysis(double values[][][]) {
        g = values.length;
        p = values[0][0].length;
        q = 0;

        m = new int[g];
        n = 0;
        for (int i = 0; i < g; i++) {
            m[i] = values[i].length;
            n += m[i];
        }

        X = values;
        
        System.out.println("g=" + g + " p=" + p + " n=" + n + " m[0]=" + m[0] + " m[1]=" + m[1] + " m[2]=" + m[2]);
    }

    public DiscriminantAnalysis(double b[][], double a[], int vars[]) {
        this.b = b;
        this.a = a;
        this.variables = vars;
        q = vars.length;
        g = b.length;
        p = b[0].length;
    }

    public void startAnalysis() {
        if (X != null) {
            X = deleteZeroColumns(X);
            variables = createVariablesModel();
            xmean = calculateXmean();
            b = calculateB(xmean);
            a = calculateA(b, xmean);
        }
    }

    private int[] createVariablesModel() {
        W = this.createWMatrix();
        T = this.createTMatrix();
        initModel();

        while (q != p && step <= maxStep && flag != 1) {
            F_to_enter = calculateFtoEnter();

            double Fmax = F_to_enter[q];
            int var = q;
            for (int i = q + 1; i < p; i++) {
                if (F_to_enter[i] > Fmax) {
                    Fmax = F_to_enter[i];
                    var = i;
                }
            }

            if (Fmax > F_to_enter_threshold) {
                addVariableToModel(var);

                double minTOL = calculateTOL();

                if (minTOL < TOL_threshold) {
                    this.deleteVariableFromModel(var);
                    flag = 1;
                }

                step++;
            } else {
                F_to_remove = calculateF_to_remove();

                double min = Double.MAX_VALUE;
                int var1 = 0;
                for (int i = 0; i < q; i++) {
                    if (F_to_remove[i] < min) {
                        min = F_to_remove[i];
                        var = i;
                    }
                }

                if (min < F_to_remove_threshold) {
                    deleteVariableFromModel(var1);
                } else {
                    flag = 1;
                }
            }

        }

        int modelRes[] = new int[q];
        for (int i = 0; i < q; i++) {
            modelRes[i] = variables[i];
        }

        return modelRes;
    }

    private double[] calculateF_to_remove() {
        double d[] = new double[q];

        for (int i = 0; i < q; i++) {
            d[i] = (W_[i][i] - T_[i][i]) * (n - q - g + 1) / ((T_[i][i] * (g - 1)));
        }

        return d;
    }

    private double calculateTOL() {

        double minTOL = Double.MAX_VALUE;
        for (int i = 0; i < p; i++) {
            double t;
            if (W[i][i] == 0) {
                t = 0;
            } else if (i < q) {
                t = -1.0 / (W_[i][i] * W[i][i]);
            } else {
                t = W_[i][i] / W[i][i];
            }

            if (t < minTOL) {
                minTOL = t;
            }
        }

        return minTOL;
    }

    private void deleteVariableFromModel(int var) {
        if (var != q) {

            for (int i = 0; i < g; i++) {
                for (int j = 0; j < m[i]; j++) {
                    double t;
                    t = X[i][j][var];
                    X[i][j][var] = X[i][j][q];
                    X[i][j][q] = t;
                }
            }

            W = createWMatrix();

            T = createTMatrix();

            int t = variables[q];
            variables[q] = variables[var];
            variables[var] = t;
        }

        q--;

        W_ = calculateNewMatrix(W);
        T_ = calculateNewMatrix(T);
    }

    private void addVariableToModel(int var) {
        if (var != q) {
            for (int i = 0; i < g; i++) {
                for (int j = 0; j < m[i]; j++) {
                    double t;
                    t = X[i][j][var];
                    X[i][j][var] = X[i][j][q];
                    X[i][j][q] = t;
                }
            }

            W = createWMatrix();

            T = createTMatrix();

            int t = variables[q];
            variables[q] = variables[var];
            variables[var] = t;
        }
        q++;

        W_ = calculateNewMatrix(W);
        T_ = calculateNewMatrix(T);
    }

    private double[][] calculateNewMatrix(double W[][]) {
        double W11[][] = new double[q][q];
        double W21[][] = new double[p - q][q];
        double W12[][] = new double[q][p - q];
        double W22[][] = new double[p - q][p - q];

        for (int i = 0; i < q; i++) {
            for (int j = 0; j < q; j++) {
                W11[i][j] = W[i][j];
            }
        }

        for (int i = q; i < p; i++) {
            for (int j = 0; j < q; j++) {
                W21[i - q][j] = W[i][j];
            }
        }

        for (int i = 0; i < q; i++) {
            for (int j = q; j < p; j++) {
                W12[i][j - q] = W[i][j];
            }
        }

        for (int i = q; i < p; i++) {
            for (int j = q; j < p; j++) {
                W22[i - q][j - q] = W[i][j];
            }
        }

        Matrix W11mat = new Matrix(W11);
        W11mat = W11mat.inverse();
        Matrix a = W11mat.copy();
        W11mat = W11mat.times(-1);

        Matrix W12mat = new Matrix(W12);
        Matrix t = a.copy();
        t = t.times(W12mat);
        W12mat = t;

        Matrix W21mat = new Matrix(W21);
        W21mat = W21mat.times(a);

        Matrix W22mat = new Matrix(W22);
        a = new Matrix(W21).times(a);
        a = a.times(new Matrix(W12));
        W22mat = W22mat.minus(a);

        double newW[][] = new double[p][p];
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < q; j++) {
                newW[i][j] = W11mat.get(i, j);
            }
        }

        for (int i = q; i < p; i++) {
            for (int j = 0; j < q; j++) {
                newW[i][j] = W21mat.get(i - q, j);
            }
        }

        for (int i = 0; i < q; i++) {
            for (int j = q; j < p; j++) {
                newW[i][j] = W12mat.get(i, j - q);
            }
        }

        for (int i = q; i < p; i++) {
            for (int j = q; j < p; j++) {
                newW[i][j] = W22mat.get(i - q, j - q);
            }
        }

        return newW;
    }

    private double[] calculateFtoEnter() {
        double f[] = new double[p];

        for (int i = q; i < p; i++) {
            f[i] = (T_[i][i] - W_[i][i]) * (n - q - g) / (W_[i][i] * (g - 1));
        }

        return f;
    }

    private void initModel() {
        W = this.createWMatrix();
        T = this.createTMatrix();
        maxStep = p;
        TOL_threshold = 0.01;
        F_to_enter_threshold = 1;
        F_to_remove_threshold = 0;
        flag = 0;
        step = 1;
        W_ = W.clone();
        T_ = T.clone();
        F_to_enter = new double[p];
        F_to_remove = new double[p];

        variables = new int[p];
        for (int i = 0; i < p; i++) {
            variables[i] = i;
        }
    }

    private double[][] createTMatrix() {
        double T[][] = new double[p][p];

        for (int i = 0; i < p; i++) {
            for (int l = 0; l < p; l++) {
                double sum1 = 0;
                for (int j = 0; j < g; j++) {
                    for (int k = 0; k < m[j]; k++) {
                        sum1 += X[j][k][i] * X[j][k][l];
                    }
                }

                double sum2 = 0, sum3 = 0;
                for (int j = 0; j < g; j++) {
                    for (int k = 0; k < m[j]; k++) {
                        sum2 += X[j][k][i];
                        sum3 += X[j][k][l];
                    }
                }

                double sum4 = sum2 * sum3 / n;

                T[i][l] = sum1 - sum4;
            }
        }

        return T;
    }

    private double[][] createWMatrix() {
        double W[][] = new double[p][p];

        for (int i = 0; i < p; i++) {
            for (int l = 0; l < p; l++) {
                double sum1 = 0;
                for (int j = 0; j < g; j++) {
                    for (int k = 0; k < m[j]; k++) {
                    	sum1 += X[j][k][i] * X[j][k][l];
                    }
                }

                double sum2 = 0;
                for (int j = 0; j < g; j++) {
                    double sum3 = 0, sum4 = 0;
                    for (int k = 0; k < m[j]; k++) {
                        sum3 += X[j][k][i];
                        sum4 += X[j][k][l];
                    }
                    sum2 += sum3 * sum4 / m[j];
                }

                W[i][l] = sum1 - sum2;
            }
        }

        return W;
    }

    private double[][][] deleteZeroColumns(double[][][] values) {
        ArrayList<Integer> zeroColumnNumbers = new ArrayList<>();

        for (int i = 0; i < values[0][0].length; i++) {
            boolean isColumnZero = true;

            for (int k = 0; k < values.length && isColumnZero; k++) {
                for (int j = 0; j < values[k].length; j++) {
                    if (values[k][j][i] != 0.0) {
                        isColumnZero = false;
                        break;
                    }
                }
            }

            if (isColumnZero) {
                zeroColumnNumbers.add(i);
            }
        }
        

        if (zeroColumnNumbers.size() != 0) {
            double matrix[][][] = new double[values.length][][];

            int idx = 0, k = 0;
            for (int m = 0; m < values.length; m++) {
                idx = 0;
                k = 0;
                matrix[m] = new double[values[m].length][32 - zeroColumnNumbers.size()];

                for (int i = 0; i < 32; i++) {
                    if (k < zeroColumnNumbers.size() && zeroColumnNumbers.get(k) == i) {
                        k++;
                        continue;
                    }

                    for (int j = 0; j < values[m].length; j++) {
                        matrix[m][j][idx] = values[m][j][i];
                    }

                    idx++;
                }
            }
            
            p -= zeroColumnNumbers.size();

            return matrix;
        }

        return values;
    }

    private double[][] calculateXmean() {
        double xmean[][] = new double[g][p];

        for (int i = 0; i < g; i++) {
            for (int j = 0; j < p; j++) {
                double sum = 0;
                for (int k = 0; k < m[i]; k++) {
                    sum += X[i][k][j];
                }
                xmean[i][j] = sum / m[i];
            }
        }

        return xmean;
    }

    private double[][] calculateB(double xmean[][]) {
        double b[][] = new double[g][q];

        for (int i = 0; i < g; i++) {
            for (int j = 0; j < q; j++) {
                double sum = 0;
                for (int l = 0; l < q; l++) {
                    sum += W_[j][l] * xmean[i][l];
                }
                sum *= (n - g);
                b[i][j] = -sum;
            }
        }

        return b;
    }

    private double[] calculateA(double b[][], double xmean[][]) {
        double a[] = new double[g];

        for (int i = 0; i < g; i++) {
            double sum = 0;
            for (int j = 0; j < q; j++) {
                sum += b[i][j] * xmean[i][j];
            }
            a[i] = Math.log((double) m[i] / (double) n) - 0.5 * sum;
        }

        return a;
    }

    /*
    public int classify(double b[][], double a[], double vars[]) {
        double res = Double.MIN_VALUE;
        int group = -1;

        for (int i = 0; i < g; i++) {
            double s = 0;
            for (int j = 0; j < q; j++) {
                s += b[i][j] * vars[variables[j]];
            }
            s += a[i];

            if (s > res) {
                res = s;
                group = i;
            }
        }

        return group;
    }
     */
    public int[] getVariables() {
        int v[] = new int[q];
        for (int i = 0; i < q; i++) {
            v[i] = variables[i];
        }
        return v;
    }

    public double[][] getB() {
        return b;
    }

    public double[] getA() {
        return a;
    }

    public int classify(double vector[]) {
        if(a == null || b == null || variables == null) {
            startAnalysis();
        }
        double res[] = new double[g];
        
        for (int i = 0; i < g; i++) {
            double s = 0;
            for (int j = 0; j < q; j++) {
                s += b[i][j] * vector[variables[j]];
            }
            s += a[i];
            
            res[i] = s;
        }
        
        double max = res[0];
        int group = 0;
        for(int i = 1; i < g; i ++) {
            if (max < res[i]) {
                max = res[i];
                group = i;
            }
        }

        return group;
    }
}
