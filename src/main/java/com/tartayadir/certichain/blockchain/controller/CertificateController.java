package com.tartayadir.certichain.blockchain.controller;

import com.tartayadir.certichain.blockchain.core.BlockchainService;
import com.tartayadir.certichain.blockchain.core.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * REST controller for certificate-related operations.
 */
@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private BlockchainService blockchainService;

    /**
     * Creates a new certificate and adds it to the blockchain.
     */
    @PostMapping
    public ResponseEntity<String> createCertificate(
            @RequestParam String ownerName,
            @RequestParam String courseName,
            @RequestParam String issueDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(issueDate);

        String contractJson = certificateService.createCertificate(ownerName, courseName, date);
        blockchainService.addBlock(contractJson);
        String contractId = certificateService.getContractId(contractJson);
        return ResponseEntity.ok(contractId);
    }

    /**
     * Verifies a certificate by its serialized JSON data.
     */
    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyCertificate(
            @RequestParam String contractJson,
            @RequestParam String ownerName,
            @RequestParam String courseName,
            @RequestParam String issueDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(issueDate);
        boolean isValid = certificateService.verifyCertificate(contractJson, ownerName, courseName, date);
        return ResponseEntity.ok(isValid);
    }
}