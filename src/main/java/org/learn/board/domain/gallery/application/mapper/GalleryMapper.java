package org.learn.board.domain.gallery.application.mapper;

import org.learn.board.domain.gallery.application.dto.GalleryResponse;
import org.learn.board.domain.gallery.domain.Gallery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GalleryMapper {

    GalleryResponse toResponse(Gallery gallery);
}
