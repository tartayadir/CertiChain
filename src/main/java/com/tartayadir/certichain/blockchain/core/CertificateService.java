package com.tartayadir.certichain.blockchain.core;

import com.tartayadir.certichain.blockchain.model.CertificateSmartContract;
import com.tartayadir.certichain.blockchain.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

/**
 * Service for managing certificate smart contracts.
 */
@Service
public class CertificateService {

    /**
     * Creates a new certificate smart contract and returns its serialized form.
     */
    public String createCertificate(String ownerName, String courseName, Date issueDate) {
        CertificateSmartContract contract = new CertificateSmartContract(ownerName, courseName, issueDate);
        return JsonUtil.toJson(contract);
    }

    /**
     * Verifies a certificate by comparing provided details with the stored contract.
     */
    public boolean verifyCertificate(String contractJson, String ownerName, String courseName, Date issueDate) {
        CertificateSmartContract contract = JsonUtil.fromJson(contractJson, CertificateSmartContract.class);
        return contract.verifyCertificate(ownerName, courseName, issueDate);
    }

    /**
     * Returns the contract ID from the serialized contract.
     */
    public String getContractId(String contractJson) {
        CertificateSmartContract contract = JsonUtil.fromJson(contractJson, CertificateSmartContract.class);
        return contract.getContractId();
    }
}