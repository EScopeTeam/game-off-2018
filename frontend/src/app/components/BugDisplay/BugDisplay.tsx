import React from "react";
import { View, Image } from "react-native";
import { Buffer } from "buffer";
import IBug from "../../models/IBug";
import IBugImage from "../../models/IBugImage";
import env from "../../config/env.conf";
import styles from "./styles";

interface IProp {
  bug: string;
  width: number;
}

const RACE_SEPARATOR: string = "/";
const IMAGE_SEPARATOR: string = "|";
const COLOR_SEPARATOR: string = ",";

const W_H_RATION: number = 1;

export default class BugDisplay extends React.Component<IProp> {
  private formatBug(bugHash: string): IBug {
    const data: string = new Buffer(bugHash, "base64").toString("ascii");
    const firstSeparation: number = data.indexOf(RACE_SEPARATOR);
    const bugRaceId: string = data.substr(0, firstSeparation);
    const images: IBugImage[] = this.formatBugImages(
      data.substr(firstSeparation + 1)
    );

    return { bugRaceId, images };
  }

  private formatBugImages(imagesStr: string): IBugImage[] {
    return imagesStr.split(IMAGE_SEPARATOR).map(this.formatBugImage);
  }

  private formatBugImage(imageStr: string, index: number): IBugImage {
    const colorSeparation: number = imageStr.indexOf(COLOR_SEPARATOR);
    const url: string =
      colorSeparation === -1 ? imageStr : imageStr.substr(0, colorSeparation);
    const colorCode: string | undefined =
      colorSeparation === -1 ? undefined : imageStr.substr(colorSeparation + 1);
    const position = index * 10;

    return { url, position, colorCode };
  }

  public render() {
    const { width } = this.props;
    const height: number = width * W_H_RATION;
    const bug: IBug = this.formatBug(this.props.bug);

    return (
      <View style={[{ width, height }, styles.container]}>
        {bug.images.map((image, index) => (
          <Image
            key={index}
            style={[
              {
                zIndex: image.position,
                tintColor: image.colorCode,
              },
              styles.image,
            ]}
            source={{
              uri: env.BUGS_URL + "/" + bug.bugRaceId + "/" + image.url,
            }}
          />
        ))}
      </View>
    );
  }
}
