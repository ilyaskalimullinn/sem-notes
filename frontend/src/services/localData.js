export function storeTokenInStorage(token) {
  localStorage.setItem("token", token);
}

export function getTokenFromStorage() {
  return localStorage.getItem("token");
}

export function clearTokenInStorage() {
  localStorage.setItem("token", null);
}
