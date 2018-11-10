import { t } from "../config/i18n";
import IFormField from "../models/IFormField";
import IFormFieldValue from "../models/IFormFieldValue";

export const requiredMessageGetter = (
  value: string,
  attribute: string,
  validatorOptions: { [key: string]: any }
) => {
  return t("^{javax.validation.constraints.NotEmpty.message}");
};

export const setStateWithValidationErrors = (
  form: { [key: string]: IFormField },
  error: any,
  state: any,
  setState: (s: any) => void
): void => {
  const formValues: { [key: string]: IFormFieldValue } = {};
  Object.keys(form).forEach(attributeName => {
    formValues[attributeName] = {
      value: state[attributeName].value,
      errors: error[attributeName].map((description: string) => {
        return { description };
      }),
    };
  });

  setState(formValues);
};
