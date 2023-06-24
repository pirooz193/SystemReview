package com.example.systemreview.service.mapper;

import com.example.systemreview.domain.Vote;
import com.example.systemreview.service.dto.VoteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    Vote toEntity(VoteDTO voteDTO);

    VoteDTO toDTO(Vote vote);

    List<Vote> toDTOList(List<VoteDTO> voteDTO);

    List<VoteDTO> toEntityList(List<Vote> vote);
}
