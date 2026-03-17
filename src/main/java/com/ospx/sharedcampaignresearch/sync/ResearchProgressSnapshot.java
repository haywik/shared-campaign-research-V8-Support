package com.ospx.sharedcampaignresearch.sync;

import arc.struct.ObjectIntMap;
import java.util.Objects;

public final class ResearchProgressSnapshot {

    private final String nodeName;
    private final ObjectIntMap<String> finishedAmountsByItem;

    public ResearchProgressSnapshot(String nodeName, ObjectIntMap<String> finishedAmountsByItem) {
        this.nodeName = nodeName;
        this.finishedAmountsByItem = new ObjectIntMap<>(finishedAmountsByItem);
    }

    public String nodeName() {
        return nodeName;
    }

    public ObjectIntMap<String> finishedAmountsByItem() {
        return finishedAmountsByItem;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ResearchProgressSnapshot)) {
            return false;
        }
        ResearchProgressSnapshot that = (ResearchProgressSnapshot) object;
        return Objects.equals(nodeName, that.nodeName)
            && Objects.equals(finishedAmountsByItem, that.finishedAmountsByItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName, finishedAmountsByItem);
    }

    @Override
    public String toString() {
        return "ResearchProgressSnapshot[nodeName=" + nodeName + ", finishedAmountsByItem=" + finishedAmountsByItem + ']';
    }
}
