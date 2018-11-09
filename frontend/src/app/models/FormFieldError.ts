export default class FormFieldError {
  public readonly code: string;

  public readonly defaultMessage: string;

  public readonly params: { [key: string]: any };

  constructor(
    code: string,
    defaultMessage: string,
    params: { [key: string]: any }
  ) {
    this.code = code;
    this.defaultMessage = defaultMessage;
    this.params = params;
  }

  public getMessage(lang: string): string {
    // TODO try get translation from code
    return this.defaultMessage;
  }
}
