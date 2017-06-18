package com.maksym.controller.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import com.maksym.model.DiagnosticGroup;
import com.maksym.model.Kvvvfloat;

import discriminantanalysis.DiscriminantAnalysisController;
import discriminantanalysis.DiscriminantAnalysisFileHandler;

@RestController
@RequestMapping("/api")
public class ClassificationController {
	@Autowired
	DiscriminantAnalysisController discriminantAnalysisController;

	@RequestMapping(value = "/classification/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<Kvvvfloat> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		Iterator<String> itr = request.getFileNames();
		if (!itr.hasNext()) {
			return new ResponseEntity<Kvvvfloat>(HttpStatus.NO_CONTENT);
		}

		MultipartFile multipartFile = request.getFile(itr.next());
		if (!checkFile(multipartFile.getOriginalFilename())) {
			return new ResponseEntity<Kvvvfloat>(HttpStatus.NO_CONTENT);
		}

		String filePath = saveFileOnDisk(multipartFile);
		if (filePath == null) {
			return new ResponseEntity<Kvvvfloat>(HttpStatus.NO_CONTENT);
		}

		double[][] kvvpfloat = readXls(filePath);

		Kvvvfloat kvvpResult = getKvvp(kvvpfloat[0]);

		return new ResponseEntity<>(kvvpResult, HttpStatus.OK);
	}

	@RequestMapping(value = "/classification/", method = RequestMethod.POST)
	public ResponseEntity classify(@RequestBody Kvvvfloat kvvv, UriComponentsBuilder ucBuilder) {
		double vector[] = getKvvpArray(kvvv);
		DiagnosticGroup group = discriminantAnalysisController.classify(vector);
		return new ResponseEntity<>(group, HttpStatus.OK);
	}

	private String saveFileOnDisk(MultipartFile multipartFile) {
		try {
			String filePath = "D:\\uploadedFiles\\" + multipartFile.getOriginalFilename();
			multipartFile.transferTo(new File(filePath));
			return filePath;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private double[][] readXls(String file) {
		double kvvpFloat[][] = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;

			int rows = sheet.getPhysicalNumberOfRows() - 1;
			int cols = 32;
			int tmp = 0;

			kvvpFloat = new double[rows][cols];
			for (int r = 0; r < rows; r++) {
				row = sheet.getRow(r + 1);
				if (row != null) {
					for (int c = 0; c < cols; c++) {
						cell = row.getCell((short) c);
						if (cell != null) {
							kvvpFloat[r][c] = cell.getNumericCellValue();
						}
					}
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return kvvpFloat;
	}

	private boolean checkFile(String fileName) {
		String regex = "^.*\\.xls$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(fileName);
		return m.matches();
	}

	private Kvvvfloat getKvvp(double kvvpValues[]) {
		Kvvvfloat kvvp = new Kvvvfloat();
		kvvp.setD2(kvvpValues[0]);
		kvvp.setD3(kvvpValues[1]);
		kvvp.setD4(kvvpValues[2]);
		kvvp.setD5(kvvpValues[3]);
		kvvp.setD6(kvvpValues[4]);
		kvvp.setD8(kvvpValues[5]);
		kvvp.setD11(kvvpValues[6]);
		kvvp.setD15(kvvpValues[7]);
		kvvp.setD20(kvvpValues[8]);
		kvvp.setD26(kvvpValues[9]);
		kvvp.setD36(kvvpValues[10]);
		kvvp.setD40(kvvpValues[11]);
		kvvp.setD65(kvvpValues[12]);
		kvvp.setD85(kvvpValues[13]);
		kvvp.setD120(kvvpValues[14]);
		kvvp.setD150(kvvpValues[15]);
		kvvp.setD210(kvvpValues[16]);
		kvvp.setD290(kvvpValues[17]);
		kvvp.setD300(kvvpValues[18]);
		kvvp.setD520(kvvpValues[19]);
		kvvp.setD700(kvvpValues[20]);
		kvvp.setD950(kvvpValues[21]);
		kvvp.setD1300(kvvpValues[22]);
		kvvp.setD1700(kvvpValues[23]);
		kvvp.setD2300(kvvpValues[24]);
		kvvp.setD3100(kvvpValues[25]);
		kvvp.setD4200(kvvpValues[26]);
		kvvp.setD5600(kvvpValues[27]);
		kvvp.setD7600(kvvpValues[28]);
		kvvp.setD10200(kvvpValues[29]);
		kvvp.setD13800(kvvpValues[30]);
		kvvp.setD18500(kvvpValues[31]);

		return kvvp;
	}

	private double[] getKvvpArray(Kvvvfloat kvvp) {
		double kvvvArray[] = new double[32];
		kvvvArray[0] = kvvp.getD2();
		kvvvArray[1] = kvvp.getD3();
		kvvvArray[2] = kvvp.getD4();
		kvvvArray[3] = kvvp.getD5();
		kvvvArray[4] = kvvp.getD6();
		kvvvArray[5] = kvvp.getD8();
		kvvvArray[6] = kvvp.getD11();
		kvvvArray[7] = kvvp.getD15();
		kvvvArray[8] = kvvp.getD20();
		kvvvArray[9] = kvvp.getD26();
		kvvvArray[10] = kvvp.getD36();
		kvvvArray[11] = kvvp.getD40();
		kvvvArray[12] = kvvp.getD65();
		kvvvArray[13] = kvvp.getD85();
		kvvvArray[14] = kvvp.getD120();
		kvvvArray[15] = kvvp.getD150();
		kvvvArray[16] = kvvp.getD210();
		kvvvArray[17] = kvvp.getD290();
		kvvvArray[18] = kvvp.getD300();
		kvvvArray[19] = kvvp.getD520();
		kvvvArray[20] = kvvp.getD700();
		kvvvArray[21] = kvvp.getD950();
		kvvvArray[22] = kvvp.getD1300();
		kvvvArray[23] = kvvp.getD1700();
		kvvvArray[24] = kvvp.getD2300();
		kvvvArray[25] = kvvp.getD3100();
		kvvvArray[26] = kvvp.getD4200();
		kvvvArray[27] = kvvp.getD5600();
		kvvvArray[28] = kvvp.getD7600();
		kvvvArray[29] = kvvp.getD10200();
		kvvvArray[30] = kvvp.getD13800();
		kvvvArray[31] = kvvp.getD18500();

		return kvvvArray;
	}
}
