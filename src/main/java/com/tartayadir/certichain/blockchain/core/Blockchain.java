package com.tartayadir.certichain.blockchain.core;

import com.tartayadir.certichain.blockchain.model.Block;
import com.tartayadir.certichain.blockchain.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the blockchain, including block addition, validation, and persistence.
 */
public class Blockchain {
    private List<Block> chain;
    private int difficulty;

    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        // Create genesis block
        Block genesisBlock = new Block(0, "0", "Genesis Block");
        genesisBlock.mineBlock(difficulty);
        chain.add(genesisBlock);
    }

    /**
     * Adds a new block to the chain after mining it.
     */
    public void addBlock(Block block) {
        block.setPreviousHash(getLatestBlock().getHash());
        block.mineBlock(difficulty);
        chain.add(block);
    }

    /**
     * Validates the integrity of the blockchain by checking hashes and links.
     */
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // Verify current block's hash
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            // Verify link to previous block
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the latest block in the chain.
     */
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    /**
     * Saves the blockchain to a JSON file.
     */
    public void saveToFile(String filePath) {
        JsonUtil.writeToFile(chain, filePath);
    }

    /**
     * Loads the blockchain from a JSON file.
     */
    public void loadFromFile(String filePath) {
        List<Block> loadedChain = JsonUtil.readFromFile(filePath);
        if (loadedChain != null && !loadedChain.isEmpty() && isChainValid()) {
            this.chain = loadedChain;
        }
    }

    // Getters and Setters
    public List<Block> getChain() {
        return chain;
    }

    public void setChain(List<Block> chain) {
        this.chain = chain;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}