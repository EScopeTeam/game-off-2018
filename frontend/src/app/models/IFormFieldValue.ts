import IFormFieldError from "./IFormFieldError";

export default interface IFormFieldValue {
  readonly value: string;

  readonly errors?: IFormFieldError[];
}
