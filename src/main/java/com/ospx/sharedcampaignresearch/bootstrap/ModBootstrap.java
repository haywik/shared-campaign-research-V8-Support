package com.ospx.sharedcampaignresearch.bootstrap;

import arc.Events;
import com.ospx.sharedcampaignresearch.SharedCampaignResearchMod;
import com.ospx.sharedcampaignresearch.net.NetworkRegistrar;
import com.ospx.sharedcampaignresearch.ui.ResearchUiRegistrar;
import mindustry.game.EventType.ClientLoadEvent;

public final class ModBootstrap {

    private final NetworkRegistrar networkRegistrar;
    private final ResearchUiRegistrar uiRegistrar;

    public ModBootstrap(NetworkRegistrar networkRegistrar, ResearchUiRegistrar uiRegistrar) {
        this.networkRegistrar = networkRegistrar;
        this.uiRegistrar = uiRegistrar;
    }

    public void registerLifecycleHooks() {
        Events.on(ClientLoadEvent.class, event -> {
            SharedCampaignResearchMod.log("client initialized");
            uiRegistrar.registerClientUiHooks();
        });
    }

    public void initialize() {
        networkRegistrar.register();
    }
}
