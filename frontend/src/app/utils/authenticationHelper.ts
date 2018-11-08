import { AsyncStorage } from "react-native";
import User from "../models/User";

const TOKEN_KEY = "token";

let token: string | null = null;

export async function loadToken(): Promise<boolean> {
  token = await AsyncStorage.getItem(TOKEN_KEY);

  return token !== null;
}

export async function saveToken(newToken: string): Promise<void> {
  token = newToken;
  await AsyncStorage.setItem(TOKEN_KEY, newToken);
}

export async function removeToken(): Promise<void> {
  token = null;
  AsyncStorage.removeItem(TOKEN_KEY);
}

export function getToken(): string {
  if (token === null) {
    throw new Error("You cannot get a token before load or savet it.");
  }
  return token;
}
