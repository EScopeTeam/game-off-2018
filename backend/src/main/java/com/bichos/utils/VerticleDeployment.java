package com.bichos.utils;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class VerticleDeployment {

  private final Class<? extends AbstractVerticle> verticle;

  private final DeploymentOptions options;

}
