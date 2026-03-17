package com.ospx.sharedcampaignresearch.domain;

import arc.util.Nullable;
import java.util.Objects;
import java.util.function.Function;
import mindustry.Vars;
import mindustry.content.TechTree.TechNode;
import mindustry.type.ItemSeq;
import mindustry.ui.dialogs.ResearchDialog;

public final class ResearchDialogAccess {

    public @Nullable ItemSeq copyItemsForRoot(TechNode rootNode) {
        return withDialogItems(rootNode, items -> items == null ? null : items.copy());
    }

    public <T> @Nullable T withDialogState(TechNode rootNode, Function<ResearchDialog, T> action) {
        Objects.requireNonNull(action, "action");

        if (rootNode == null || Vars.ui == null || Vars.ui.research == null) {
            return null;
        }

        ResearchDialog dialog = Vars.ui.research;
        if (dialog.isShown() && dialog.lastNode != rootNode) {
            return null;
        }

        TechNode previousRoot = dialog.lastNode;
        boolean switched = previousRoot != rootNode;

        try {
            if (switched) {
                dialog.switchTree(rootNode);
            }

            dialog.rebuildItems();
            return action.apply(dialog);
        } finally {
            if (switched && previousRoot != null) {
                dialog.switchTree(previousRoot);
                dialog.rebuildItems();
            }
        }
    }

    public <T> @Nullable T withDialogItems(TechNode rootNode, Function<ItemSeq, T> action) {
        return withDialogState(rootNode, dialog -> {
            ItemSeq items = dialog.items;
            if (items == null) {
                return null;
            }

            return action.apply(items);
        });
    }
}
