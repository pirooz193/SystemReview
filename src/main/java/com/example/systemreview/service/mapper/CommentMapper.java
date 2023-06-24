package com.example.systemreview.service.mapper;

import com.example.systemreview.domain.Comment;
import com.example.systemreview.service.dto.CommentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentDTO commentDTO);

    CommentDTO toDTO(Comment comment);

    List<CommentDTO> toDTOList(List<Comment> comments);

    List<Comment> toEntityList(List<CommentDTO> commentDTOs);
}
