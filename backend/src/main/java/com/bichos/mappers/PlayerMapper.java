package com.bichos.mappers;

import com.bichos.models.ApiPlayerData;
import com.bichos.models.Player;

public final class PlayerMapper {

  private PlayerMapper() {
  }

  public static ApiPlayerData mapPlayerToResponse(final Player player) {
    final ApiPlayerData apiPlayerData = new ApiPlayerData();
    apiPlayerData.setActive(player.getActive());
    apiPlayerData.setCoins(player.getCoins().doubleValue());
    apiPlayerData.setEmail(player.getEmail());
    apiPlayerData.setExperience(player.getExperiencePoints().longValue());
    apiPlayerData.setOnline(player.isOnline());
    apiPlayerData.setUserId(player.getUserId());
    apiPlayerData.setUsername(player.getUsername());

    return apiPlayerData;
  }

}
