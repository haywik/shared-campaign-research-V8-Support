package com.ospx.sharedcampaignresearch.sync;

import arc.struct.ObjectIntMap;
import arc.struct.OrderedMap;
import com.ospx.sharedcampaignresearch.domain.ResearchDialogAccess;
import com.ospx.sharedcampaignresearch.domain.ResearchRules;
import java.util.Objects;
import mindustry.Vars;
import mindustry.content.TechTree;
import mindustry.content.TechTree.TechNode;
import mindustry.type.Item;
import mindustry.type.ItemSeq;
import mindustry.type.ItemStack;

public final class ResearchSyncService {

    private final ResearchRules researchRules;
    private final ResearchDialogAccess dialogAccess;

    public ResearchSyncService(ResearchRules researchRules, ResearchDialogAccess dialogAccess) {
        this.researchRules = Objects.requireNonNull(researchRules, "researchRules");
        this.dialogAccess = Objects.requireNonNull(dialogAccess, "dialogAccess");
    }

    public ResearchSyncSnapshot buildSnapshotForNode(String nodeName) {
        TechNode node = researchRules.findNodeByContentName(nodeName);
        TechNode root = node == null ? null : researchRules.getResearchRoot(node);
        return buildSnapshotForRoot(root);
    }

    public ResearchSyncSnapshot buildSnapshotForRootName(String rootName) {
        TechNode root = researchRules.findNodeByContentName(rootName);
        return buildSnapshotForRoot(root);
    }

    public ResearchSyncSnapshot buildSnapshotForRoot(TechNode rootNode) {
        String rootName = rootNode == null || rootNode.content == null ? "" : rootNode.content.name;
        OrderedMap<String, ResearchProgressSnapshot> progressByNode = new OrderedMap<>();

        for (TechNode node : TechTree.all) {
            if (node.content == null) {
                continue;
            }

            progressByNode.put(node.content.name, serializeProgress(node));
        }

        ItemSeq rootItems = rootNode == null ? null : dialogAccess.copyItemsForRoot(rootNode);
        ResearchItemSnapshot itemSnapshot = new ResearchItemSnapshot(rootName, serializeItems(rootItems));
        return new ResearchSyncSnapshot(rootName, progressByNode, itemSnapshot);
    }

    private ResearchProgressSnapshot serializeProgress(TechNode node) {
        ObjectIntMap<String> finishedAmountsByItem = new ObjectIntMap<>();
        for (ItemStack stack : node.finishedRequirements) {
            if (stack.item != null && stack.amount > 0) {
                finishedAmountsByItem.put(stack.item.name, stack.amount);
            }
        }
        return new ResearchProgressSnapshot(node.content.name, finishedAmountsByItem);
    }

    private ObjectIntMap<String> serializeItems(ItemSeq items) {
        ObjectIntMap<String> serialized = new ObjectIntMap<>();
        if (items == null) {
            return serialized;
        }

        for (Item item : Vars.content.items()) {
            int amount = items.get(item);
            if (amount > 0) {
                serialized.put(item.name, amount);
            }
        }

        return serialized;
    }
}
