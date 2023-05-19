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

export async function login(email, password) {
  const userStore = useUserStore();
  userStore.setLoading(true);
  await instance.post("/auth/login/", {
    username: email,
    password
  }).then(response => {

    userStore.storeUser(response.data["user"]);
    userStore.storeToken(response.data["token"]);
    userStore.clearError();

  }).catch((error) => {
    userStore.storeError(error);
  });

  userStore.setLoading(false);
}

export async function register(email, fullName, password, passwordRepeat) {
  const userStore = useUserStore();
  userStore.setLoading(true);
  await instance.post("/auth/register/", {
    fullName,
    username: email,
    password,
    passwordRepeat
  }).then(response => {
    userStore.storeUser(response.data["user"]);
    userStore.storeToken(response.data["token"]);
    userStore.clearError();

  }).catch((error) => {
    userStore.storeError(error);
  });

  userStore.setLoading(false);
}
