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

export async function login(email, password) {
  const userStore = useUserStore();
  userStore.loading = true;
  const response = await instance.post("/auth/login/", {
    username: email,
    password
  }).then(response => {

    userStore.user = response.data["user"];
    userStore.token = response.data["token"];
    userStore.errors = [];

  }).catch((error) => {
    console.log(error)
    userStore.errors.push("Error");
  });

  userStore.loading = false;
  return userStore.errors.length === 0
}

export async function register(email, fullName, password, passwordRepeat) {
  const userStore = useUserStore();
  userStore.loading = true;
  const response = instance.post("/auth/register/", {
    fullName,
    username: email,
    password,
    passwordRepeat
  }).then(response => {

    userStore.user = response.data["user"];
    userStore.token = response.data["token"];
    userStore.errors = [];

  }).catch((error) => {
    console.log(error);
    userStore.errors.push("Error");
  });

  userStore.loading = false;
  return userStore.errors.length === 0
}
