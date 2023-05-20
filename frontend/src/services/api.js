import axios from 'axios'
import {API_URL} from "./consts.js";
import {useUserStore} from "../stores/userStore.js";

const instance = axios.create({
  baseURL: API_URL
})

export async function testApi () {
  const response = await instance.get('/test/')
  return response.data
}

instance.interceptors.request.use(function (config) {
  const userStore = useUserStore();
  if (userStore.token) {
    config.headers['Authorization'] = `Bearer ${userStore.token}`;
  }
  return config;
}, function (error) {
  return Promise.reject(error);
});

export async function apiLogin(email, password) {
  const response = await instance.post("/auth/login/", {
    username: email,
    password
  }).catch(defaultApiExceptionHandler);

  return response.data;
}

export async function apiRegister(email, fullName, password, passwordRepeat) {
  const response = await instance.post("/auth/register/", {
    fullName,
    username: email,
    password,
    passwordRepeat
  }).catch(defaultApiExceptionHandler);

  return response.data;
}

function defaultApiExceptionHandler(error) {
  if (error.response) {
    console.error(error.response);
    throw new Error(error.response.data.detail || "Unknown error");
  } else {
    console.error('Unknown error: ', error.message);
    throw new Error("Unknown error, please try again");
  }
}
