// src/services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // adjust if needed
  withCredentials: true,
});

export default {
  login(username, password) {
    return api.post('/users/login', { username, password });
  },
  register(username, password) {
      return api.post('/users/register', { username, password });
    },
  getAppliances(user) {
    return api.get(`/appliances/user/${user.id}`);
  },
  updateAppliance(appliance) {
    return api.put(`/appliances/update`, appliance);
  },
};
