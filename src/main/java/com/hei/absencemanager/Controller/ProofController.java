package com.hei.absencemanager.Controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.hei.absencemanager.Entity.Proof;
import com.hei.absencemanager.Service.ProofServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ProofController {
    private ProofServices proofService;

    public ProofController(ProofServices proofService) {
        this.proofService = proofService;
    }

    @GetMapping("proofs")
    public List<Proof> readProofList() throws SQLException {
        return proofService.readProofList();
    }

    @DeleteMapping("proofs/delete")
    public void deleteProofByDetails(
            @RequestParam String std,
            @RequestParam String courseName,
            @RequestParam LocalDateTime date) throws SQLException {
        proofService.deleteProofByDetails(std, courseName, date);
    }

    @GetMapping("proofs/{std}")
    public Proof getOneProof(@PathVariable String std,
            @RequestParam String courseName,
            @RequestParam LocalDateTime date) throws SQLException {
        return proofService.getOneProof(std, courseName, date);
    }

}
