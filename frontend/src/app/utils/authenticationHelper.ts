import { AsyncStorage } from "react-native";

const TOKEN_KEY = "token";

export async function loadToken(): Promise<string | null> {
  const token: string | null = await AsyncStorage.getItem(TOKEN_KEY);

  return token;
}

export async function saveToken(newToken: string): Promise<void> {
  await AsyncStorage.setItem(TOKEN_KEY, newToken);
}

export async function removeToken(): Promise<void> {
  AsyncStorage.removeItem(TOKEN_KEY);
}
