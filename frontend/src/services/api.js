import axios from 'axios'
import {API_URL} from "./consts.js";

const instance = axios.create({
  baseURL: API_URL
})

export async function testApi () {
  const response = await instance.get('/test/')
  return response.data
}
