import { useState } from 'react';
import { verifyCertificate } from '../api/api';

const CertificateVerifier: React.FC = () => {
    const [contractJson, setContractJson] = useState<string>('');
    const [ownerName, setOwnerName] = useState<string>('');
    const [courseName, setCourseName] = useState<string>('');
    const [issueDate, setIssueDate] = useState<string>('');
    const [message, setMessage] = useState<string>('');

    const handleVerify = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const isValid = await verifyCertificate(contractJson, ownerName, courseName, issueDate);
            setMessage(isValid ? 'Certificate is valid!' : 'Certificate is invalid.');
        } catch (error) {
            setMessage('Error verifying certificate. Please check the input.');
        }
    };

    return (
        <div className="max-w-md mx-auto bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md">
            <h2 className="text-2xl font-bold mb-4">Verify Certificate</h2>
            <form onSubmit={handleVerify} className="space-y-4">
                <div>
                    <label className="block text-sm font-medium">Contract JSON</label>
                    <textarea
                        value={contractJson}
                        onChange={(e) => setContractJson(e.target.value)}
                        className="w-full p-2 border dark:border-gray-600 rounded dark:bg-gray-700"
                        rows={4}
                        required
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium">Owner Name</label>
                    <input
                        type="text"
                        value={ownerName}
                        onChange={(e) => setOwnerName(e.target.value)}
                        className="w-full p-2 border dark:border-gray-600 rounded dark:bg-gray-700"
                        required
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium">Course Name</label>
                    <input
                        type="text"
                        value={courseName}
                        onChange={(e) => setCourseName(e.target.value)}
                        className="w-full p-2 border dark:border-gray-600 rounded dark:bg-gray-700"
                        required
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium">Issue Date (YYYY-MM-DD)</label>
                    <input
                        type="date"
                        value={issueDate}
                        onChange={(e) => setIssueDate(e.target.value)}
                        className="w-full p-2 border dark:border-gray-600 rounded dark:bg-gray-700"
                        required
                    />
                </div>
                <button
                    type="submit"
                    className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
                >
                    Verify Certificate
                </button>
            </form>
            {message && (
                <p
                    className={`mt-4 text-center ${
                        message.includes('valid') ? 'text-green-600 dark:text-green-400' : 'text-red-600 dark:text-red-400'
                    }`}
                >
                    {message}
                </p>
            )}
        </div>
    );
};

export default CertificateVerifier;