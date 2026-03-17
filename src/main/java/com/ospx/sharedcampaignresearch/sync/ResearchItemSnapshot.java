package com.ospx.sharedcampaignresearch.sync;

import arc.struct.ObjectIntMap;
import java.util.Objects;

public final class ResearchItemSnapshot {

    private final String rootName;
    private final ObjectIntMap<String> items;

    public ResearchItemSnapshot(String rootName, ObjectIntMap<String> items) {
        this.rootName = rootName;
        this.items = new ObjectIntMap<>(items);
    }

    public String rootName() {
        return rootName;
    }

    public ObjectIntMap<String> items() {
        return items;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ResearchItemSnapshot)) {
            return false;
        }
        ResearchItemSnapshot that = (ResearchItemSnapshot) object;
        return Objects.equals(rootName, that.rootName) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rootName, items);
    }

    @Override
    public String toString() {
        return "ResearchItemSnapshot[rootName=" + rootName + ", items=" + items + ']';
    }
}
