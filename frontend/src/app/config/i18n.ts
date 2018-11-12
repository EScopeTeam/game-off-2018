import { Localization } from "expo-localization";
import i18n from "i18next";
import es from "./i18n/es";
import en from "./i18n/en";

export const defaultNamespace = "transalations";

const i18nextModule = {
  init: Function.prototype,
  type: "languageDetector",
  detect: () => Localization.locale,
  cacheUserLanguage: Function.prototype,
};

i18n.use(i18nextModule).init({
  fallbackLng: "en",
  resources: { es, en },
  ns: [defaultNamespace],
  defaultNS: defaultNamespace,
});

export default i18n;

export const t = i18n.getFixedT(i18n.language);
