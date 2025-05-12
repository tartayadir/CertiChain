import axios from 'axios';
import { Block } from '../types';

const API_BASE_URL = 'http://localhost:8080/api';

export const createCertificate = async (
    ownerName: string,
    courseName: string,
    issueDate: string
): Promise<string> => {
    const response = await axios.post(`${API_BASE_URL}/certificates`, null, {
        params: { ownerName, courseName, issueDate },
    });
    return response.data;
};

export const verifyCertificate = async (
    contractJson: string,
    ownerName: string,
    courseName: string,
    issueDate: string
): Promise<boolean> => {
    const response = await axios.get(`${API_BASE_URL}/certificates/verify`, {
        params: { contractJson, ownerName, courseName, issueDate },
    });
    return response.data;
};

export const getBlockchain = async (): Promise<Block[]> => {
    const response = await axios.get(`${API_BASE_URL}/blocks`);
    return response.data;
};

export const validateChain = async (): Promise<boolean> => {
    const response = await axios.get(`${API_BASE_URL}/blocks/validate`);
    return response.data;
};