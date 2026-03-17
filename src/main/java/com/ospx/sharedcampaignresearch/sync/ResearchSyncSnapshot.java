package com.ospx.sharedcampaignresearch.sync;

import arc.struct.OrderedMap;
import java.util.Objects;

public final class ResearchSyncSnapshot {

    private final String rootName;
    private final OrderedMap<String, ResearchProgressSnapshot> progressByNode;
    private final ResearchItemSnapshot rootItems;

    public ResearchSyncSnapshot(
        String rootName,
        OrderedMap<String, ResearchProgressSnapshot> progressByNode,
        ResearchItemSnapshot rootItems
    ) {
        this.rootName = rootName;
        this.progressByNode = new OrderedMap<>(progressByNode);
        this.rootItems = rootItems;
    }

    public String rootName() {
        return rootName;
    }

    public OrderedMap<String, ResearchProgressSnapshot> progressByNode() {
        return progressByNode;
    }

    public ResearchItemSnapshot rootItems() {
        return rootItems;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ResearchSyncSnapshot)) {
            return false;
        }
        ResearchSyncSnapshot that = (ResearchSyncSnapshot) object;
        return Objects.equals(rootName, that.rootName)
            && Objects.equals(progressByNode, that.progressByNode)
            && Objects.equals(rootItems, that.rootItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rootName, progressByNode, rootItems);
    }

    @Override
    public String toString() {
        return "ResearchSyncSnapshot[rootName=" + rootName + ", progressByNode=" + progressByNode + ", rootItems=" + rootItems + ']';
    }
}
