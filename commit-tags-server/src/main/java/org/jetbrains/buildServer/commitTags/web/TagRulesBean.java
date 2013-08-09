package org.jetbrains.buildServer.commitTags.web;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.buildServer.commitTags.TagRule;

import java.util.List;

public class TagRulesBean {
  private final List<TagRule> myTagRules;

  public TagRulesBean(@NotNull List<TagRule> tagRules) {
    myTagRules = tagRules;
  }

  @NotNull
  public List<TagRule> getTagRules() {
    return myTagRules;
  }
}
