// src/services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // adjust if needed
  withCredentials: true,
});

export default {
  login(username, password) {
    return api.post(
      '/login',
      { username, password },
      {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: true // <-- Ensure this is here too!
      }
    );
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
  turnApplianceOff(appliance_id) {
    return api.put(`/appliances/off/${appliance_id}`);
  },
  turnApplianceOn(appliance) {
      return api.put(`/appliances/on/${appliance.id}`, appliance);
  },
  increaseSpeed(appliance) {
      return api.put(`/appliances/increase/${appliance.id}`, appliance);
  },
  decreaseSpeed(appliance) {
        return api.put(`/appliances/decrease/${appliance.id}`, appliance);
    },
  turnAllAppliancesOff(user) {
    return api.post(`/appliances/offAll/${user.id}`);
  }
};
