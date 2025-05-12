package com.tartayadir.certichain.blockchain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tartayadir.certichain.blockchain.util.HashUtil;
import lombok.Setter;

/**
 * Represents a block in the blockchain, containing certificate data and Proof-of-Work metadata.
 */
public class Block {
    @JsonProperty
    private int index;
    @JsonProperty
    private String previousHash;
    @JsonProperty
    private long timestamp;
    @JsonProperty
    private String data; // CertificateSmartContract serialized as JSON
    @JsonProperty
    private int nonce;
    @Setter
    @JsonProperty
    private String hash;

    public Block() {
        // Required for Jackson deserialization
    }

    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
        this.nonce = 0;
        this.hash = calculateHash();
    }

    /**
     * Calculates the SHA-256 hash of the block based on its fields.
     */
    public String calculateHash() {
        String input = index + previousHash + timestamp + data + nonce;
        return HashUtil.applySha256(input);
    }

    /**
     * Mines the block by finding a nonce that satisfies the Proof-of-Work difficulty.
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
    }

    // Getters and Setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        this.hash = calculateHash();
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
        this.hash = calculateHash();
    }

    public String getHash() {
        return hash;
    }
}