import { defineStore } from 'pinia';
import api from '@/services/api'; // Ensure you have a login function in api.js

export const useAuthStore = defineStore('auth', {
state: () => ({
    user: null,
    error: null,
  }),

  actions: {
    // Modify the login function to handle the user object from the backend
    async login(username, password) {
      try {
        // Send login request to backend and store the response (user data)
        const response = await api.login(username, password);

        // Check if the response has the user data (ensure your api.login returns it)
        if (response.data && response.data.username) {
          // If successful, store the user data
          this.user = response.data; // You can store more than just username if needed
        } else {
          throw new Error('Invalid login response');
        }

        this.error = null; // Reset any previous errors
      } catch (err) {
        // In case of error, set an error message and clear user data
        this.error = 'Invalid credentials';
        this.user = null;
      }
    },

    // Modify the register function similarly if needed
    async register(username, password) {
      try {
        await api.register(username, password);
        this.user = { username }; // Or adjust this based on what the response gives
        this.error = null;
      } catch (err) {
        this.error = 'Failed to register';
        this.user = null;
      }
    },

    // Logout functionality
    logout() {
      this.user = null;
      // Optionally, send logout request to backend if you need
    },
  },
});
