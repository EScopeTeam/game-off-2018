export default class ValidationError {
  public readonly errors: { [key: string]: string[] };

  constructor(errors: any) {
    this.errors = errors;
  }
}
