import { useState } from 'react';
import { createCertificate } from '../api/api';

const CertificateForm: React.FC = () => {
    const [ownerName, setOwnerName] = useState<string>('');
    const [courseName, setCourseName] = useState<string>('');
    const [issueDate, setIssueDate] = useState<string>('');
    const [message, setMessage] = useState<string>('');
    const [contractId, setContractId] = useState<string>('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await createCertificate(ownerName, courseName, issueDate);
            setContractId(response);
            setMessage('Certificate created and added to blockchain!');
            setOwnerName('');
            setCourseName('');
            setIssueDate('');
        } catch (error) {
            setMessage('Error creating certificate. Please try again.');
        }
    };

    return (
        <div className="max-w-md mx-auto bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md">
            <h2 className="text-2xl font-bold mb-4">Create Certificate</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
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
                    Create Certificate
                </button>
            </form>
            {message && (
                <p className="mt-4 text-center text-green-600 dark:text-green-400">{message}</p>
            )}
            {contractId && (
                <p className="mt-2 text-center">
                    Contract ID: <span className="font-mono">{contractId}</span>
                </p>
            )}
        </div>
    );
};

export default CertificateForm;