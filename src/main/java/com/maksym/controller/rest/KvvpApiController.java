package com.maksym.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maksym.model.DiagnosticGroup;
import com.maksym.model.Kvvvfloat;
import com.maksym.service.DiagnosticGroupService;
import com.maksym.service.KvvpService;
import com.maksym.utils.CustomErrorType;

@RestController
@RequestMapping("/api")
public class KvvpApiController {
	@Autowired
	KvvpService kvvpService;
	
	@Autowired
	DiagnosticGroupService diagnosticGroupService;

    @RequestMapping(value = "/kvvv/group/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<?> getKvvpByGroupId(@PathVariable("groupId") int groupId) {
    	DiagnosticGroup group = diagnosticGroupService.findById(groupId);
    	if(group == null) {
    		return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to show. DiagnosticGroup with id " + groupId + " not found."),
                    HttpStatus.NOT_FOUND);
    	}
    	
    	List<Kvvvfloat> kvvv = kvvpService.getKvvpByGroupId(groupId);
        if (kvvv.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(kvvv, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/kvvv/group/{groupId}", method = RequestMethod.POST)
    public ResponseEntity<Kvvvfloat> saveKvvp(@PathVariable("groupId") int groupId,
    	@RequestBody List<Kvvvfloat> kvvv) {
    	kvvpService.deleteKvvpByGroupId(groupId);
        kvvpService.saveKvvpInGroup(groupId, kvvv);
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
