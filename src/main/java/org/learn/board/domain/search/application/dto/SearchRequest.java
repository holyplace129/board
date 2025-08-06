package org.learn.board.domain.search.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchRequest {

    private SearchType type;
    private String keyword;

    public enum SearchType {
        TITLE, CONTENT, TITLE_CONTENT, WRITER
    }
}
