export default interface IFormField {
  readonly name: string;

  readonly labelCode?: string;

  readonly labelParams?: { [key: string]: any };

  readonly placeholderCode?: string;

  readonly placeholderParams?: { [key: string]: any };

  readonly setter: (value: string) => void;
}
