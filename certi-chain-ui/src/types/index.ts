export interface Block {
    index: number;
    previousHash: string;
    timestamp: number;
    data: string;
    nonce: number;
    hash: string;
}