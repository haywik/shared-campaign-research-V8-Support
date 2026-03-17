package com.ospx.sharedcampaignresearch.sync;

import arc.struct.ObjectMap;
import com.ospx.sharedcampaignresearch.util.StringsCompat;

public final class ClientResearchState {

    private final ObjectMap<String, ResearchProgressSnapshot> progressByNode = new ObjectMap<>();
    private final ObjectMap<String, ResearchItemSnapshot> itemSnapshotsByRoot = new ObjectMap<>();
    private String lastRequestedRoot;

    public ObjectMap<String, ResearchProgressSnapshot> progressByNode() {
        return progressByNode;
    }

    public ObjectMap<String, ResearchItemSnapshot> itemSnapshotsByRoot() {
        return itemSnapshotsByRoot;
    }

    public String lastRequestedRoot() {
        return lastRequestedRoot;
    }

    public void setLastRequestedRoot(String lastRequestedRoot) {
        this.lastRequestedRoot = lastRequestedRoot;
    }

    public void replaceProgress(ObjectMap<String, ResearchProgressSnapshot> nextProgress) {
        progressByNode.set(nextProgress);
    }

    public void putRootSnapshot(ResearchItemSnapshot snapshot) {
        if (snapshot == null || StringsCompat.isBlank(snapshot.rootName())) {
            return;
        }
        itemSnapshotsByRoot.put(snapshot.rootName(), snapshot);
        lastRequestedRoot = null;
    }
}
