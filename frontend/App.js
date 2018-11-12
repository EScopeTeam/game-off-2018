"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const react_1 = __importDefault(require("react"));
const Layout_1 = __importDefault(require("./src/app/components/Layout"));
class App extends react_1.default.Component {
    render() {
        return react_1.default.createElement(Layout_1.default, null);
    }
}
exports.default = App;
