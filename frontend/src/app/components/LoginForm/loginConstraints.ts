import { requiredMessageGetter } from "../../utils/validationHelper";

export const loginConstraints = {
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
