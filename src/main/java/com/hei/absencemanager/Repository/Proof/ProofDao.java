package com.hei.absencemanager.Repository.Proof;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.hei.absencemanager.Entity.Proof;

public interface ProofDao {
    public List<Proof> readProofList() throws SQLException;

    public void deleteProofByDetails(String std, String courseName, LocalDateTime date) throws SQLException;

    public Proof getOneProof(String std, String courseName, LocalDateTime date) throws SQLException;
}
