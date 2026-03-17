package com.ospx.sharedcampaignresearch.domain;

import java.util.Objects;
import mindustry.content.TechTree.TechNode;

public final class ResearchRequestValidator {

    private final ResearchRules rules;

    public ResearchRequestValidator(ResearchRules rules) {
        this.rules = Objects.requireNonNull(rules, "rules");
    }

    public TechNode findRequestedNode(String nodeName) {
        return rules.findNodeByContentName(nodeName);
    }

    public boolean canClientRequestResearch(String nodeName) {
        TechNode node = findRequestedNode(nodeName);
        return rules.requiresHostAuthority()
            && !rules.isClientSideUnlockAllowed()
            && rules.canOfferRequestButton(node);
    }

    public boolean canApplyHostSide(String nodeName) {
        TechNode node = findRequestedNode(nodeName);
        return node != null
            && rules.requiresHostAuthority()
            && rules.areParentsUnlocked(node)
            && !rules.hasIncompleteObjectives(node)
            && rules.canSpend(node);
    }
}
