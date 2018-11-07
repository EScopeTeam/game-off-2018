export function parseAuthorization(token: string): string {
  return "Bearer " + token;
}
