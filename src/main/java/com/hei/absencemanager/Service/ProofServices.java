package com.hei.absencemanager.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hei.absencemanager.Entity.Proof;
import com.hei.absencemanager.Repository.Proof.ProofDaoImpl;

@Service
public class ProofServices {
    private ProofDaoImpl proofRepo;

    public ProofServices(ProofDaoImpl proofRepo) {
        this.proofRepo = proofRepo;
    }

    public List<Proof> readProofList() throws SQLException {
        return proofRepo.readProofList();
    }

    public void deleteProofByDetails(String std, String courseName, LocalDateTime date) throws SQLException {
        proofRepo.deleteProofByDetails(std, courseName, date);
    }
}
