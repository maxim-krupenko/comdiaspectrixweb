package com.maksym.service;

import com.maksym.model.KvvvGroupRelation;
import com.maksym.model.Kvvvfloat;
import com.maksym.repositories.KvvpGroupRelationRepository;
import com.maksym.repositories.KvvpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("kvvv")
@Transactional
public class KvvpServiceImpl implements KvvpService{
    @Autowired
    KvvpGroupRelationRepository kvvpGroupRepository;
    
    @Autowired
    KvvpRepository kvvpRepository;

    public List<Kvvvfloat> getKvvpByGroupId(int groupId) {
    	List<KvvvGroupRelation> relation = kvvpGroupRepository.findByGroupId(groupId);
    	return relation.stream().map(r -> r.getKvvvId()).collect(Collectors.toList());
    }
    
    public void deleteKvvpByGroupId(int groupId) {
    	List<KvvvGroupRelation> relation = kvvpGroupRepository.findByGroupId(groupId);
    	for(KvvvGroupRelation r : relation) {
    		kvvpGroupRepository.delete(r.getId());
    		kvvpRepository.delete(r.getKvvvId().getIdKv());
       	}
    }
    
    public void saveKvvpInGroup(int groupId, List<Kvvvfloat> kvvv) {
    	List<Kvvvfloat> savedKvvp = kvvpRepository.save(kvvv);

    	for(Kvvvfloat kvvvfloat : savedKvvp) {
    		KvvvGroupRelation r = new KvvvGroupRelation();
    		r.setGroupId(groupId);
    		r.setKvvvId(kvvvfloat);
    		kvvpGroupRepository.save(r);
    	}
    }
    
    public Map<Integer, List<Kvvvfloat>> getDiscriminantAnalysisInitialData() {
    	List<KvvvGroupRelation> allKvvpsInGroup = kvvpGroupRepository.findAll();
    	
    	Map<Integer, List<Kvvvfloat>> result = new HashMap<>();
    	for(KvvvGroupRelation r : allKvvpsInGroup) {
    		List<Kvvvfloat> kvvpInGroup = result.get(r.getGroupId());
    		if(kvvpInGroup == null) {
    			kvvpInGroup = new ArrayList<>();
    			result.put(r.getGroupId(), kvvpInGroup);
    		}
    		kvvpInGroup.add(r.getKvvvId());
    	}
    	return result;
    }
}
