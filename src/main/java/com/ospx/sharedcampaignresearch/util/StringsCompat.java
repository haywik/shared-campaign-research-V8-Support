package com.ospx.sharedcampaignresearch.util;

public final class StringsCompat {

    private StringsCompat() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
