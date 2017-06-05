package com.maksym.controller.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import com.maksym.model.DiagnosticGroup;
import com.maksym.model.HospitalStaff;
import com.maksym.model.Kvvvfloat;
import com.maksym.service.DiagnosticGroupService;
import com.maksym.service.KvvpService;
import com.maksym.utils.CustomErrorType;

import discriminantanalysis.DiscriminantAnalysisController;

@RestController
@RequestMapping("/api")
public class DiagnosticGroupApiController {
	@Autowired
	DiagnosticGroupService diagnosticGroupService;
	
	@Autowired
	KvvpService kvvpService;
	
	@Autowired
	DiscriminantAnalysisController discriminantAnalysisController;

    @RequestMapping(value = "/diagnostic-group/", method = RequestMethod.GET)
    public ResponseEntity<List<DiagnosticGroup>> listAllDiagnosticGroups() {
        List<DiagnosticGroup> groups = diagnosticGroupService.findAllDiagnosticGroups();
        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/diagnostic-group/", method = RequestMethod.POST)
    public ResponseEntity<?> createDiagnosticGroup(@RequestBody DiagnosticGroup group, UriComponentsBuilder ucBuilder) {
    	if(!checkGroup(group)) {
    		return new ResponseEntity<>(new CustomErrorType("Unable to create. A Diagnostic Group has invalid " +
            		"group name."), HttpStatus.CONFLICT);
    	}
    	if (diagnosticGroupService.isDiagnosticGroupExist(group)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Diagnostic Group with name " +
            		group.getGroupName() + " already exist."), HttpStatus.CONFLICT);
        }
    	
        diagnosticGroupService.saveDiagnosticGroup(group);
        return new ResponseEntity<String>("", HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/diagnostic-group/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDiagnosticGroup(@PathVariable("id") int id) {
    	DiagnosticGroup group = diagnosticGroupService.findById(id);
        if (group == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. DiagnosticGroup with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        kvvpService.deleteKvvpByGroupId(id);
        diagnosticGroupService.deleteDiagnosticGroupById(id);
        return new ResponseEntity<HospitalStaff>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/diagnostic-group/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDiagnosticGroup(@PathVariable("id") int id, @RequestBody DiagnosticGroup group) {
    	DiagnosticGroup groupInDatabase = diagnosticGroupService.findById(id);

        if (groupInDatabase == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. DiagnosticGroup with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        if(!checkGroup(group)) {
    		return new ResponseEntity<>(new CustomErrorType("Unable to save. A Diagnostic Group has invalid " +
            		"new group name."), HttpStatus.CONFLICT);
    	}
        List<DiagnosticGroup> groupsWithSameNameList = 
        		diagnosticGroupService.findByGroupName(group.getGroupName());
        for(DiagnosticGroup g : groupsWithSameNameList) {
        	if(g.getIdDg() != id) {
        		return new ResponseEntity<>(new CustomErrorType("Unable to save. Diagnostic Group with name " +
                		group.getGroupName()  +" already exists."), HttpStatus.CONFLICT);
        	}
        }

        groupInDatabase.setGroupName(group.getGroupName());
        groupInDatabase.setDescription(group.getDescription());

        diagnosticGroupService.updateDiagnosticGroup(groupInDatabase);
        return new ResponseEntity<>(groupInDatabase, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/diagnostic-group-startDiscriminantAnalysis", method = RequestMethod.GET)
    public ResponseEntity startDiscriminantAnalysis() {
        discriminantAnalysisController.doAnalysis();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/diagnostic-group/uploadFile", method = RequestMethod.POST)
    public ResponseEntity upload(MultipartHttpServletRequest request,
            HttpServletResponse response) {
    	Iterator<String> itr =  request.getFileNames();
    	if(!itr.hasNext()) {
    		return new ResponseEntity<DiagnosticGroup>(HttpStatus.NO_CONTENT);
    	}
    	
    	MultipartFile multipartFile = request.getFile(itr.next());
    	if(!checkFile(multipartFile.getOriginalFilename())) {
    		return new ResponseEntity<CustomErrorType>(
    				new CustomErrorType("Unable to upload file. Invalid file type (should be .xls)."), HttpStatus.CONFLICT);
    	}
    	
    	String filePath = saveFileOnDisk(multipartFile);
    	if(filePath == null) {
    		return new ResponseEntity<DiagnosticGroup>(HttpStatus.NO_CONTENT);
    	}
    	
    	//try {
    		double[][] kvvpfloat = readXls(filePath);
    		saveKvvp(Integer.parseInt(request.getParameter("data")), kvvpfloat);
    	/*} catch (Exception e) {
    		return new ResponseEntity<CustomErrorType>(
    				new CustomErrorType("Data in file is invalid"), HttpStatus.CONFLICT);
    	}*/
    	
        return new ResponseEntity<DiagnosticGroup>(HttpStatus.NO_CONTENT);
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
    	    for(int r = 0; r < rows; r++) {
    	        row = sheet.getRow(r + 1);
    	        if(row != null) {
    	            for(int c = 0; c < cols; c++) {
    	                cell = row.getCell((short)c);
    	                if(cell != null){
    	                	kvvpFloat[r][c] = cell.getNumericCellValue();
    	                }
    	            }
    	        }
    	    }
    	} catch(Exception ioe) {
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
    
    private void saveKvvp(int groupId, double kvvpValues[][]) {
    	List<Kvvvfloat> kvvvToSave = new ArrayList<>();
    	for(int i = 0; i < kvvpValues.length; i ++) {
    		Kvvvfloat kvvp = new Kvvvfloat();
    		kvvp.setD2(kvvpValues[i][0]);
    		kvvp.setD3(kvvpValues[i][1]);
    		kvvp.setD4(kvvpValues[i][2]);
    		kvvp.setD5(kvvpValues[i][3]);
    		kvvp.setD6(kvvpValues[i][4]);
    		kvvp.setD8(kvvpValues[i][5]);
    		kvvp.setD11(kvvpValues[i][6]);
    		kvvp.setD15(kvvpValues[i][7]);
    		kvvp.setD20(kvvpValues[i][8]);
    		kvvp.setD26(kvvpValues[i][9]);
    		kvvp.setD36(kvvpValues[i][10]);
    		kvvp.setD40(kvvpValues[i][11]);
    		kvvp.setD65(kvvpValues[i][12]);
    		kvvp.setD85(kvvpValues[i][13]);
    		kvvp.setD120(kvvpValues[i][14]);
    		kvvp.setD150(kvvpValues[i][15]);
    		kvvp.setD210(kvvpValues[i][16]);
    		kvvp.setD290(kvvpValues[i][17]);
    		kvvp.setD300(kvvpValues[i][18]);
    		kvvp.setD520(kvvpValues[i][19]);
    		kvvp.setD700(kvvpValues[i][20]);
    		kvvp.setD950(kvvpValues[i][21]);
    		kvvp.setD1300(kvvpValues[i][22]);
    		kvvp.setD1700(kvvpValues[i][23]);
    		kvvp.setD2300(kvvpValues[i][24]);
    		kvvp.setD3100(kvvpValues[i][25]);
    		kvvp.setD4200(kvvpValues[i][26]);
    		kvvp.setD5600(kvvpValues[i][27]);
    		kvvp.setD7600(kvvpValues[i][28]);
    		kvvp.setD10200(kvvpValues[i][29]);
    		kvvp.setD13800(kvvpValues[i][30]);
    		kvvp.setD18500(kvvpValues[i][31]);
    		
    		kvvvToSave.add(kvvp);
    	}
    	this.kvvpService.saveKvvpInGroup(groupId, kvvvToSave);
    }
        
    private boolean checkGroup (DiagnosticGroup group) {
    	if(group.getGroupName() == null || group.getGroupName().equals("")) {
    		return false;
    	}
    	Pattern p = Pattern.compile("^([a-zA-Z])([a-zA-Z0-9\\s\\-])*$");
    	Matcher m = p.matcher(group.getGroupName());
    	if(!m.matches()) {
    		return false;
    	}
    	return true;
    }
}
