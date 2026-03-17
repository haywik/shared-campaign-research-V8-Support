package com.ospx.sharedcampaignresearch.sync;

import arc.struct.ObjectIntMap;
import java.util.Objects;

public final class ResearchSyncApplier {

    private static final String PLACEHOLDER_ROOT = "core-shard";

    private final ClientResearchState clientResearchState;

    public ResearchSyncApplier(ClientResearchState clientResearchState) {
        this.clientResearchState = Objects.requireNonNull(clientResearchState, "clientResearchState");
    }

    public void applySnapshot(ResearchSyncSnapshot snapshot) {
        if (snapshot == null) {
            return;
        }

        clientResearchState.replaceProgress(snapshot.progressByNode());
        clientResearchState.putRootSnapshot(snapshot.rootItems());
    }

    public void applyPlaceholderSnapshot() {
        clientResearchState.itemSnapshotsByRoot().put(
            PLACEHOLDER_ROOT,
            new ResearchItemSnapshot(PLACEHOLDER_ROOT, new ObjectIntMap<String>())
        );
    }
}
