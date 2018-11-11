export default interface IFormFieldError {
  readonly code?: string;

  readonly description?: string;

  readonly params?: { [key: string]: any };
}
