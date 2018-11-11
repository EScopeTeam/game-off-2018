import validateJs from "validate.js";
import { t } from "../config/i18n";
import IFormField from "../models/IFormField";
import IFormFieldValue from "../models/IFormFieldValue";
import ValidationError from "../errors/ValidationError";
import { AxiosError } from "axios";
import IFormFieldError from "../models/IFormFieldError";

export const requiredMessageGetter = (
  value: string,
  attribute: string,
  validatorOptions: { [key: string]: any }
) => {
  return t("^{javax.validation.constraints.NotEmpty.message}");
};

export const getFieldValuesWithValidationErrors = (
  form: { [key: string]: IFormField },
  error: ValidationError,
  state: any
): any => {
  const formValues: { [key: string]: IFormFieldValue } = {};
  Object.keys(form).forEach(attributeName => {
    formValues[attributeName] = {
      value: state[attributeName].value,
      errors: error.errors[attributeName].map((description: string) => {
        return { description };
      }),
    };
  });

  return formValues;
};

export const getFieldValuesWithHttpErrors = (
  form: { [key: string]: IFormField },
  error: AxiosError,
  state: any
): any => {
  const result: any = {};
  Object.keys(form).forEach((attributeName: string) => {
    result[attributeName] = {
      value: state[attributeName].value,
    };
  });

  if (error.response && error.response.data.errors) {
    error.response.data.errors.forEach((e: any) => {
      const attribute: string = e.attribute;
      if (attribute) {
        const formValue: IFormFieldValue = result[e.attribute];
        if (formValue) {
          const errors = formValue.errors ? formValue.errors : [];
          result[e.attribute] = {
            value: formValue.value,
            errors: errors.concat(e),
          };
        }
      } else {
        const generalErrors: IFormFieldError[] = state.generalErrors
          ? state.generalErrors
          : [];
        result.generalErrors = generalErrors.concat(e);
      }
    });
  }

  return result;
};

export const validate = (attributes: any, constraints: any): Promise<void> => {
  return validateJs.async(attributes, constraints, {
    wrapErrors: ValidationError,
  });
};
