package org.jetbrains.buildServer.commitTags;

import jetbrains.buildServer.vcs.SVcsModification;
import jetbrains.buildServer.vcs.VcsModification;
import org.jetbrains.annotations.NotNull;

public abstract class TagRule {
  private final String myTag;

  public TagRule(@NotNull String tag) {
    myTag = tag;
  }

  @NotNull
  public String getTag() {
    return myTag;
  }

  public abstract boolean matches(@NotNull SVcsModification modification);

  @NotNull
  public abstract String getDescription();
}
