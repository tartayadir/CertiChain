import { useEffect, useState } from 'react';

const ThemeToggle: React.FC = () => {
    const [isDark, setIsDark] = useState<boolean>(false);

    useEffect(() => {
        if (isDark) {
            document.documentElement.classList.add('dark');
        } else {
            document.documentElement.classList.remove('dark');
        }
    }, [isDark]);

    return (
        <button
            onClick={() => setIsDark(!isDark)}
            className="fixed top-4 right-4 p-2 bg-gray-200 dark:bg-gray-700 rounded-full"
        >
            {isDark ? '☀️ Light' : '🌙 Dark'}
        </button>
    );
};

export default ThemeToggle;