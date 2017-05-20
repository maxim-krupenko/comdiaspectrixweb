package com.maksym.service;

import com.maksym.model.DiagnosticGroup;

import java.util.List;

public interface DiagnosticGroupService {
    DiagnosticGroup findById(Integer id);

    void saveDiagnosticGroup(DiagnosticGroup diagnosticGroup);

    void updateDiagnosticGroup(DiagnosticGroup diagnosticGroup);

    void deleteDiagnosticGroupById(Integer id);

    void deleteAllDiagnosticGroups();

    List<DiagnosticGroup> findAllDiagnosticGroups();

    boolean isDiagnosticGroupExist(DiagnosticGroup diagnosticGroup);
}
