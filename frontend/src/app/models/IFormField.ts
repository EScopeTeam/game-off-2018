import FormFieldError from "./FormFieldError";

export default interface IFormField {
  readonly name: string;

  readonly labelCode?: string;

  readonly value: string;

  readonly placeholderCode: string;

  readonly errors?: FormFieldError[];

  readonly setter: (value: string) => void;
}
