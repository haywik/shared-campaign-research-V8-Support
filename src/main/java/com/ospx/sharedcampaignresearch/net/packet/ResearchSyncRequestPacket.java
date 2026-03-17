package com.ospx.sharedcampaignresearch.net.packet;

import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.net.Packet;

public final class ResearchSyncRequestPacket extends Packet {

    public String rootName = "";

    public ResearchSyncRequestPacket() {
    }

    public ResearchSyncRequestPacket(String rootName) {
        this.rootName = rootName;
    }

    @Override
    public void write(Writes write) {
        write.str(rootName);
    }

    @Override
    public void read(Reads read) {
        rootName = read.str();
    }
}
