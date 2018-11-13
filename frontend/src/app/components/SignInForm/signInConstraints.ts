import { requiredMessageGetter } from "../../utils/validationHelper";

export const signInConstraints = {
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
