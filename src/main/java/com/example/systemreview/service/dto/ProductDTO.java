package com.example.systemreview.service.dto;

import com.example.systemreview.domain.User;
import com.example.systemreview.domain.enums.ReactionRules;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "isPresentable")
    private Boolean presentable;
    @JsonProperty(value = "commentingEnabled")
    private Boolean commentingEnabled;
    @JsonProperty(value = "votingEnabled")
    private Boolean votingEnabled;
    @JsonProperty(value = "reactionRules")
    private ReactionRules reactionRules;
    @JsonProperty(value = "averageScore")
    private float averageScore;
    @JsonProperty(value = "numOfComments")
    private int numOfComments;

    @JsonProperty(value = "buyers")
    @JsonIgnore
    private List<User> buyers;

    @JsonProperty(value = "comments")
//    @JsonIgnore
    private List<CommentDTO> lastThreeCommentDTOS;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPresentable=" + presentable +
                ", commentingEnabled=" + commentingEnabled +
                ", votingEnabled=" + votingEnabled +
                ", reactionRules=" + reactionRules +
                ", buyers=" + buyers +
                ", commentDTOS=" + lastThreeCommentDTOS +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPresentable() {
        return presentable;
    }

    public void setPresentable(Boolean presentable) {
        this.presentable = presentable;
    }

    public Boolean isCommentingEnabled() {
        return commentingEnabled;
    }

    public void setCommentingEnabled(Boolean commentingEnabled) {
        this.commentingEnabled = commentingEnabled;
    }

    public Boolean isVotingEnabled() {
        return votingEnabled;
    }

    public void setVotingEnabled(Boolean votingEnabled) {
        this.votingEnabled = votingEnabled;
    }

    public ReactionRules getReactionRules() {
        return reactionRules;
    }

    public void setReactionRules(ReactionRules reactionRules) {
        this.reactionRules = reactionRules;
    }

    public List<User> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<User> buyers) {
        this.buyers = buyers;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public int getNumOfComments() {
        return numOfComments;
    }

    public void setNumOfComments(int numOfComments) {
        this.numOfComments = numOfComments;
    }

    public List<CommentDTO> getLastThreeCommentDTOS() {
        return lastThreeCommentDTOS;
    }

    public void setLastThreeCommentDTOS(List<CommentDTO> lastThreeCommentDTOS) {
        this.lastThreeCommentDTOS = lastThreeCommentDTOS;
    }
}
