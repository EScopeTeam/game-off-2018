import { ISettings } from "../models/ISettings";
import env from "./env.conf";

const settings: ISettings = {
  api: {
    websocket: {
      hello: "hello",
    },
    rest: {
      login: env.BASE_PATH + "/login",
    },
  },
};

export default settings;
