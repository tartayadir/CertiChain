package com.tartayadir.certichain.blockchain.core;

import com.tartayadir.certichain.blockchain.model.Block;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing blockchain operations, including initialization and persistence.
 */
@Service
public class BlockchainService {
    private Blockchain blockchain;

    @Value("${blockchain.file.path}")
    private String blockchainFilePath;

    @Value("${blockchain.difficulty}")
    private int difficulty;

    @PostConstruct
    public void init() {
        blockchain = new Blockchain(difficulty);
        blockchain.loadFromFile(blockchainFilePath);
    }

    /**
     * Adds a new block with the given data to the blockchain and saves it.
     */
    public void addBlock(String data) {
        Block newBlock = new Block(blockchain.getChain().size(), blockchain.getLatestBlock().getHash(), data);
        blockchain.addBlock(newBlock);
        blockchain.saveToFile(blockchainFilePath);
    }

    /**
     * Returns the entire blockchain.
     */
    public List<Block> getChain() {
        return blockchain.getChain();
    }

    /**
     * Validates the blockchain.
     */
    public boolean isChainValid() {
        return blockchain.isChainValid();
    }
}