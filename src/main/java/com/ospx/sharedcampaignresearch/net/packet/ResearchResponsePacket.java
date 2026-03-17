package com.ospx.sharedcampaignresearch.net.packet;

import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.net.Packet;

public final class ResearchResponsePacket extends Packet {

    public String nodeName = "";
    public String status = "rejected";
    public String message = "";

    public ResearchResponsePacket() {
    }

    public ResearchResponsePacket(String nodeName, String status, String message) {
        this.nodeName = nodeName;
        this.status = status;
        this.message = message;
    }

    @Override
    public void write(Writes write) {
        write.str(nodeName);
        write.str(status);
        write.str(message);
    }

    @Override
    public void read(Reads read) {
        nodeName = read.str();
        status = read.str();
        message = read.str();
    }
}
