import { useState } from 'react';
import { validateChain } from '../api/api';

const ChainValidator: React.FC = () => {
    const [message, setMessage] = useState<string>('');

    const handleValidate = async () => {
        try {
            const isValid = await validateChain();
            setMessage(isValid ? 'Blockchain is valid!' : 'Blockchain is invalid.');
        } catch (error) {
            setMessage('Error validating blockchain.');
        }
    };

    return (
        <div className="max-w-md mx-auto bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md text-center">
            <h2 className="text-2xl font-bold mb-4">Validate Blockchain</h2>
            <button
                onClick={handleValidate}
                className="bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
            >
                Validate Chain
            </button>
            {message && (
                <p
                    className={`mt-4 ${
                        message.includes('valid') ? 'text-green-600 dark:text-green-400' : 'text-red-600 dark:text-red-400'
                    }`}
                >
                    {message}
                </p>
            )}
        </div>
    );
};

export default ChainValidator;