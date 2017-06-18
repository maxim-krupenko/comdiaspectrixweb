package discriminantanalysis;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.maksym.model.DiagnosticGroup;
import com.maksym.model.Kvvvfloat;
import com.maksym.service.DiagnosticGroupService;
import com.maksym.service.KvvpService;

public class DiscriminantAnalysisController {
	@Autowired
	KvvpService kvvpService;
	
	@Autowired
	DiagnosticGroupService diagnosticGroupService;
	
	private double X[][][];
	
	private int groups[];
	
	public synchronized DiagnosticGroup classify(double vector[]) {
		DiscriminantAnalysisFileHandler dafh = new DiscriminantAnalysisFileHandler();
		try {
			dafh.readFunctionData("vars.txt", "groups.txt", "b.txt", "a.txt");
			DiscriminantAnalysis discriminantAnalysis = new DiscriminantAnalysis(
					dafh.getB(), dafh.getA(), dafh.getVariables());
			int groupIdx = discriminantAnalysis.classify(vector);
			int groups[] = dafh.getGroups();
			int groupId = groups[groupIdx];
			DiagnosticGroup group = diagnosticGroupService.findById(groupId);
			return group;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public synchronized void doAnalysis() {
		loadInitialData();
		DiscriminantAnalysis discriminantAnalysis = new DiscriminantAnalysis(X);
		discriminantAnalysis.startAnalysis();
		
		DiscriminantAnalysisFileHandler dafh = new DiscriminantAnalysisFileHandler();
		try {
			/*System.out.println("-------------vars----------------");
			for(int i = 0; i < discriminantAnalysis.getVariables().length; i ++) {
				System.out.println(discriminantAnalysis.getVariables()[i] + " ");
			}
			System.out.println();
			
			System.out.println("-------------b----------------");
			for(int i = 0; i < discriminantAnalysis.getB().length; i ++) {
				for(int j = 0; j < discriminantAnalysis.getB()[i].length; j ++) {
					System.out.println(discriminantAnalysis.getB()[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println(discriminantAnalysis.getB());
			System.out.println();

			System.out.println("-------------a----------------");
			for(int i = 0; i < discriminantAnalysis.getA().length; i ++) {
				System.out.println(discriminantAnalysis.getA()[i] + " ");
			}
			System.out.println();*/
			
			dafh.writeFunctionData(discriminantAnalysis.getVariables(), groups, discriminantAnalysis.getB(), 
				discriminantAnalysis.getA(), "vars.txt", "groups.txt", "b.txt", "a.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		X = null;
		groups = null;
	}
	
	private synchronized void loadInitialData() {
		Map<Integer, List<Kvvvfloat>> initialData = kvvpService.getDiscriminantAnalysisInitialData();
		
		X = new double[initialData.size()][][];
		groups = new int[initialData.size()];
		
		int currentGroupIdx = 0;
		for(Integer groupId : initialData.keySet()) {
			groups[currentGroupIdx] = groupId;
			List<Kvvvfloat> cases = initialData.get(groupId);
			X[currentGroupIdx] = new double[cases.size()][32];
			int currentCaseIdx = 0;
			
			for(Kvvvfloat kvvp : cases) {
				X[currentGroupIdx][currentCaseIdx] = transformKvvvfloatToDoubleArray(kvvp);
				currentCaseIdx++;
			}
			
			currentGroupIdx ++;
		}
		
		/*for(int i = 0; i < X.length; i ++) {
			System.out.println("-------------------GROUP " + i);
			for(int j = 0; j < X[i].length; j ++) {
				for(int k = 0; k < X[i][j].length; k ++) {
					System.out.print(X[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println("-------------------------------");
		}*/
	}
	
	private synchronized double[] transformKvvvfloatToDoubleArray (Kvvvfloat kvvp) {
		double res[] = new double[32];
		res[0] = kvvp.getD2();
		res[1] = kvvp.getD3();
		res[2] = kvvp.getD4();
		res[3] = kvvp.getD5();
		res[4] = kvvp.getD6();
		res[5] = kvvp.getD8();
		res[6] = kvvp.getD11();
		res[7] = kvvp.getD15();
		res[8] = kvvp.getD20();
		res[9] = kvvp.getD26();
		res[10] = kvvp.getD36();
		res[11] = kvvp.getD40();
		res[12] = kvvp.getD65();
		res[13] = kvvp.getD85();
		res[14] = kvvp.getD120();
		res[15] = kvvp.getD150();
		res[16] = kvvp.getD210();
		res[17] = kvvp.getD290();
		res[18] = kvvp.getD300();
		res[19] = kvvp.getD520();
		res[20] = kvvp.getD700();
		res[21] = kvvp.getD950();
		res[22] = kvvp.getD1300();
		res[23] = kvvp.getD1700();
		res[24] = kvvp.getD2300();
		res[25] = kvvp.getD3100();
		res[26] = kvvp.getD4200();
		res[27] = kvvp.getD5600();
		res[28] = kvvp.getD7600();
		res[29] = kvvp.getD10200();
		res[30] = kvvp.getD13800();
		res[31] = kvvp.getD18500();
		return res;
	}
}
