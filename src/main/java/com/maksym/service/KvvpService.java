package com.maksym.service;

import java.util.List;
import java.util.Map;

import com.maksym.model.Kvvvfloat;

public interface KvvpService {
    List<Kvvvfloat> getKvvpByGroupId(int groupId);
    
    void deleteKvvpByGroupId(int groupId);
    
    void saveKvvpInGroup(int groupId, List<Kvvvfloat> kvvv);
    
    Map<Integer, List<Kvvvfloat>> getDiscriminantAnalysisInitialData();
}
