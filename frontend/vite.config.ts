import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5200,
    // Requests to /api are sent on to the Java backend
    proxy: { '/api': 'http://localhost:8080' },
  },
});
