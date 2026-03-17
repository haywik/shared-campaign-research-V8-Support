package com.ospx.sharedcampaignresearch.net.packet;

import arc.struct.ObjectIntMap;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import java.util.Objects;
import mindustry.net.Packet;

public final class ResearchSyncPacket extends Packet {

    public String rootName = "";
    public Seq<String> progressNodeNames = new Seq<>();
    public Seq<String> progressItemNames = new Seq<>();
    public Seq<Integer> progressFinishedAmounts = new Seq<>();
    public Seq<String> rootItemNames = new Seq<>();
    public Seq<Integer> rootItemAmounts = new Seq<>();

    public ResearchSyncPacket() {
    }

    public ResearchSyncPacket(
        String rootName,
        Seq<String> progressNodeNames,
        Seq<String> progressItemNames,
        Seq<Integer> progressFinishedAmounts,
        Seq<String> rootItemNames,
        Seq<Integer> rootItemAmounts
    ) {
        this.rootName = rootName;
        this.progressNodeNames = progressNodeNames;
        this.progressItemNames = progressItemNames;
        this.progressFinishedAmounts = progressFinishedAmounts;
        this.rootItemNames = rootItemNames;
        this.rootItemAmounts = rootItemAmounts;
    }

    @Override
    public void write(Writes write) {
        write.str(rootName == null ? "" : rootName);
        write.i(progressNodeNames.size);
        for (int index = 0; index < progressNodeNames.size; index++) {
            write.str(progressNodeNames.get(index));
            write.str(progressItemNames.get(index));
            write.i(progressFinishedAmounts.get(index));
        }

        write.i(rootItemNames.size);
        for (int index = 0; index < rootItemNames.size; index++) {
            write.str(rootItemNames.get(index));
            write.i(rootItemAmounts.get(index));
        }
    }

    @Override
    public void read(Reads read) {
        rootName = read.str();

        int progressSize = read.i();
        progressNodeNames = new Seq<>(progressSize);
        progressItemNames = new Seq<>(progressSize);
        progressFinishedAmounts = new Seq<>(progressSize);
        for (int index = 0; index < progressSize; index++) {
            progressNodeNames.add(read.str());
            progressItemNames.add(read.str());
            progressFinishedAmounts.add(read.i());
        }

        int itemsSize = read.i();
        rootItemNames = new Seq<>(itemsSize);
        rootItemAmounts = new Seq<>(itemsSize);
        for (int index = 0; index < itemsSize; index++) {
            rootItemNames.add(read.str());
            rootItemAmounts.add(read.i());
        }
    }

    public Seq<ProgressEntry> progressEntries() {
        Seq<ProgressEntry> entries = new Seq<>(progressNodeNames.size);
        for (int index = 0; index < progressNodeNames.size; index++) {
            entries.add(new ProgressEntry(
                progressNodeNames.get(index),
                progressItemNames.get(index),
                progressFinishedAmounts.get(index)
            ));
        }
        return entries;
    }

    public ObjectIntMap<String> rootItemEntries() {
        ObjectIntMap<String> entries = new ObjectIntMap<>();
        for (int index = 0; index < rootItemNames.size; index++) {
            entries.put(rootItemNames.get(index), rootItemAmounts.get(index));
        }
        return entries;
    }

    public static final class ProgressEntry {

        private final String nodeName;
        private final String itemName;
        private final int finishedAmount;

        public ProgressEntry(String nodeName, String itemName, int finishedAmount) {
            this.nodeName = nodeName;
            this.itemName = itemName;
            this.finishedAmount = finishedAmount;
        }

        public String nodeName() {
            return nodeName;
        }

        public String itemName() {
            return itemName;
        }

        public int finishedAmount() {
            return finishedAmount;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof ProgressEntry)) {
                return false;
            }
            ProgressEntry that = (ProgressEntry) object;
            return finishedAmount == that.finishedAmount
                && Objects.equals(nodeName, that.nodeName)
                && Objects.equals(itemName, that.itemName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nodeName, itemName, finishedAmount);
        }

        @Override
        public String toString() {
            return "ProgressEntry[nodeName=" + nodeName + ", itemName=" + itemName + ", finishedAmount=" + finishedAmount + ']';
        }
    }
}
