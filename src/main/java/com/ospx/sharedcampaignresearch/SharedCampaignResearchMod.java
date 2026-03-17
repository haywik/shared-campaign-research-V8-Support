package com.ospx.sharedcampaignresearch;

import arc.util.Log;
import com.ospx.sharedcampaignresearch.bootstrap.ModBootstrap;
import com.ospx.sharedcampaignresearch.domain.ResearchApplyService;
import com.ospx.sharedcampaignresearch.domain.ResearchDialogAccess;
import com.ospx.sharedcampaignresearch.domain.ResearchRequestValidator;
import com.ospx.sharedcampaignresearch.domain.ResearchRules;
import com.ospx.sharedcampaignresearch.net.NetworkRegistrar;
import com.ospx.sharedcampaignresearch.sync.ClientResearchState;
import com.ospx.sharedcampaignresearch.sync.ResearchSyncApplier;
import com.ospx.sharedcampaignresearch.sync.ResearchSyncService;
import com.ospx.sharedcampaignresearch.ui.ResearchUiRegistrar;
import mindustry.mod.Mod;

public final class SharedCampaignResearchMod extends Mod {

    public static final String MOD_NAME = "Shared Campaign Research";
    public static final String MOD_PREFIX = "[SharedResearchJava]";

    private final ClientResearchState clientResearchState;
    private final ResearchDialogAccess researchDialogAccess;
    private final ResearchRules researchRules;
    private final ResearchRequestValidator requestValidator;
    private final ResearchApplyService applyService;
    private final ResearchSyncApplier syncApplier;
    private final ResearchSyncService syncService;
    private final NetworkRegistrar networkRegistrar;
    private final ResearchUiRegistrar uiRegistrar;
    private final ModBootstrap bootstrap;

    public SharedCampaignResearchMod() {
        this.clientResearchState = new ClientResearchState();
        this.researchDialogAccess = new ResearchDialogAccess();
        this.researchRules = new ResearchRules(researchDialogAccess);
        this.requestValidator = new ResearchRequestValidator(researchRules);
        this.applyService = new ResearchApplyService(researchRules, requestValidator, researchDialogAccess);
        this.syncApplier = new ResearchSyncApplier(clientResearchState);
        this.syncService = new ResearchSyncService(researchRules, researchDialogAccess);
        this.networkRegistrar = new NetworkRegistrar(applyService, requestValidator, syncApplier, syncService);
        this.uiRegistrar = new ResearchUiRegistrar(clientResearchState, requestValidator, networkRegistrar);
        this.bootstrap = new ModBootstrap(networkRegistrar, uiRegistrar);

        log("bootstrap created");
        bootstrap.registerLifecycleHooks();
    }

    @Override
    public void init() {
        log("init");
        bootstrap.initialize();
    }

    public static void log(String message) {
        Log.info(MOD_PREFIX + " " + message);
    }
}
