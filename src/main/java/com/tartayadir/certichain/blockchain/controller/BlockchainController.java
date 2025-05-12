package com.tartayadir.certichain.blockchain.controller;

import com.tartayadir.certichain.blockchain.core.BlockchainService;
import com.tartayadir.certichain.blockchain.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for blockchain-related operations.
 */
@RestController
@RequestMapping("/api/blocks")
public class BlockchainController {

    @Autowired
    private BlockchainService blockchainService;

    /**
     * Retrieves the entire blockchain.
     */
    @GetMapping
    public ResponseEntity<List<Block>> getBlockchain() {
        return ResponseEntity.ok(blockchainService.getChain());
    }

    /**
     * Validates the blockchain.
     */
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateChain() {
        return ResponseEntity.ok(blockchainService.isChainValid());
    }
}