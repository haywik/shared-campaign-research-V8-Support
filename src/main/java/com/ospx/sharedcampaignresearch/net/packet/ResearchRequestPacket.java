package com.ospx.sharedcampaignresearch.net.packet;

import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.net.Packet;

public final class ResearchRequestPacket extends Packet {

    public String nodeName = "";

    public ResearchRequestPacket() {
    }

    public ResearchRequestPacket(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public void write(Writes write) {
        write.str(nodeName);
    }

    @Override
    public void read(Reads read) {
        nodeName = read.str();
    }
}
