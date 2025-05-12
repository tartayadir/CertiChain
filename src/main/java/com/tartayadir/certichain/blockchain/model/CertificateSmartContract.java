package com.tartayadir.certichain.blockchain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * Represents a smart contract for a digital certificate, with creation and verification logic.
 */
public class CertificateSmartContract {
    @JsonProperty
    private String contractId;
    @JsonProperty
    private String ownerName;
    @JsonProperty
    private String courseName;
    @JsonProperty
    private Date issueDate;

    public CertificateSmartContract() {
        // Required for Jackson deserialization
    }

    public CertificateSmartContract(String ownerName, String courseName, Date issueDate) {
        this.contractId = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.courseName = courseName;
        this.issueDate = issueDate;
    }

    /**
     * Verifies if the provided certificate details match this contract.
     */
    public boolean verifyCertificate(String ownerName, String courseName, Date issueDate) {
        return this.ownerName.equals(ownerName) &&
                this.courseName.equals(courseName) &&
                this.issueDate.equals(issueDate);
    }

    // Getters and Setters
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
