import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import CertificateForm from './components/CertificateForm';
import BlockchainView from './components/BlockchainView';
import CertificateVerifier from './components/CertificateVerifier';
import ChainValidator from './components/ChainValidator';
import ThemeToggle from './components/ThemeToggle';

const App: React.FC = () => {
  return (
      <Router>
        <div className="min-h-screen bg-gray-100 dark:bg-gray-900 text-gray-900 dark:text-gray-100 transition-colors duration-300">
          <Navbar />
          <div className="container mx-auto p-4">
            <ThemeToggle />
            <Routes>
              <Route path="/" element={<CertificateForm />} />
              <Route path="/view" element={<BlockchainView />} />
              <Route path="/verify" element={<CertificateVerifier />} />
              <Route path="/validate" element={<ChainValidator />} />
            </Routes>
          </div>
        </div>
      </Router>
  );
};

export default App;