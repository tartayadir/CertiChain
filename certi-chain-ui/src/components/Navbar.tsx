import { Link } from 'react-router-dom';

const Navbar: React.FC = () => {
    return (
        <nav className="bg-blue-600 dark:bg-blue-800 p-4 shadow-md">
            <div className="container mx-auto flex justify-between items-center">
                <h1 className="text-white text-2xl font-bold">CertiChain</h1>
                <div className="space-x-4">
                    <Link to="/" className="text-white hover:text-gray-200">Create Certificate</Link>
                    <Link to="/view" className="text-white hover:text-gray-200">View Blockchain</Link>
                    <Link to="/verify" className="text-white hover:text-gray-200">Verify Certificate</Link>
                    <Link to="/validate" className="text-white hover:text-gray-200">Validate Chain</Link>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;