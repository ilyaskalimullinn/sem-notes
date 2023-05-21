export function storeTokenInStorage(token) {
  localStorage.setItem("token", token);
}

export function getTokenFromStorage() {
  return localStorage.getItem("token");
}

export function clearTokenInStorage() {
  localStorage.setItem("token", null);
}

export function storeUserInStorage(user) {
  localStorage.setItem("user", JSON.stringify(user));
}

export function getUserFromStorage() {
  try {
    return JSON.parse(localStorage.getItem("user"));
  } catch (error) {
    return null;
  }
}

export function clearUserInStorage() {
  localStorage.setItem("user", null);
}