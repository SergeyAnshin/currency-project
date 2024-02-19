package org.good.job.currency.project.entity.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProjectSymbol {
    BACKSLASH("\\\\"), DOT("."), UNDERSCORE("_"), WHITESPACE(" ");

    private final String symbol;
}
