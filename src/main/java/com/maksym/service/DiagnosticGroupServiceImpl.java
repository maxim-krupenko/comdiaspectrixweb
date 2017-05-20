package com.maksym.service;

import com.maksym.model.DiagnosticGroup;
import com.maksym.repositories.DiagnosticGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("diagnosticGroup")
@Transactional
public class DiagnosticGroupServiceImpl implements DiagnosticGroupService{
    @Autowired
    DiagnosticGroupRepository diagnosticGroupRepository;

    public DiagnosticGroup findById(Integer id) {
        return diagnosticGroupRepository.findOne(id);
    }

    public void saveDiagnosticGroup(DiagnosticGroup diagnosticGroup) {
        diagnosticGroupRepository.save(diagnosticGroup);
    }

    public void updateDiagnosticGroup(DiagnosticGroup diagnosticGroup) {
        saveDiagnosticGroup(diagnosticGroup);
    }

    public void deleteDiagnosticGroupById(Integer id) {
        diagnosticGroupRepository.delete(id);
    }

    public void deleteAllDiagnosticGroups() {
        diagnosticGroupRepository.deleteAll();
    }

    public List<DiagnosticGroup> findAllDiagnosticGroups() {
        return diagnosticGroupRepository.findAll();
    }

    public boolean isDiagnosticGroupExist(DiagnosticGroup diagnosticGroup) {
        return diagnosticGroupRepository.exists(diagnosticGroup.getIdDg());
    }
}
