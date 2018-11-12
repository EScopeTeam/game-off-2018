import { StyleSheet } from "react-native";

export default StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "rgba(0, 0, 0, 1)",
  },
  textContainer: {
    flex: 1,
    top: 0,
    bottom: 0,
    left: 0,
    right: 0,
    justifyContent: "center",
    alignItems: "center",
    position: "absolute",
  },
  textContent: {
    top: 80,
    height: 50,
    fontSize: 20,
    fontWeight: "bold",
    color: "white",
  },
  activityIndicator: {
    flex: 1,
    color: "white",
  },
});
