import { useEffect, useState } from 'react';
import { getBlockchain } from '../api/api';
import { Block } from '../types';

const BlockchainView: React.FC = () => {
    const [blocks, setBlocks] = useState<Block[]>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        const fetchBlocks = async () => {
            try {
                const data = await getBlockchain();
                setBlocks(data);
            } catch (err) {
                setError('Error fetching blockchain data.');
            }
        };
        fetchBlocks();
    }, []);

    return (
        <div className="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md">
            <h2 className="text-2xl font-bold mb-4">Blockchain</h2>
            {error && <p className="text-red-600 dark:text-red-400">{error}</p>}
            <div className="overflow-x-auto">
                <table className="w-full border-collapse">
                    <thead>
                    <tr className="bg-gray-200 dark:bg-gray-700">
                        <th className="p-2 border dark:border-gray-600">Index</th>
                        <th className="p-2 border dark:border-gray-600">Hash</th>
                        <th className="p-2 border dark:border-gray-600">Previous Hash</th>
                        <th className="p-2 border dark:border-gray-600">Timestamp</th>
                        <th className="p-2 border dark:border-gray-600">Data</th>
                    </tr>
                    </thead>
                    <tbody>
                    {blocks.map((block) => (
                        <tr key={block.index} className="hover:bg-gray-100 dark:hover:bg-gray-700">
                            <td className="p-2 border dark:border-gray-600">{block.index}</td>
                            <td className="p-2 border dark:border-gray-600 font-mono truncate">{block.hash}</td>
                            <td className="p-2 border dark:border-gray-600 font-mono truncate">{block.previousHash}</td>
                            <td className="p-2 border dark:border-gray-600">
                                {new Date(block.timestamp).toLocaleString()}
                            </td>
                            <td className="p-2 border dark:border-gray-600">{block.data}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default BlockchainView;