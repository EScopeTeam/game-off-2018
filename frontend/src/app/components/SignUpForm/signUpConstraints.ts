import { requiredMessageGetter } from "../../utils/validationHelper";

export const signUpConstraints = {
  username: {
    presence: {
      message: requiredMessageGetter,
      allowEmpty: false,
    },
  },
  password: {
    presence: {
      message: requiredMessageGetter,
      allowEmpty: false,
    },
  },
};
