package org.jetbrains.buildServer.commitTags;

import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.vcs.VcsModification;
import jetbrains.buildServer.vcs.VcsRoot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class VcsModificationListener extends BuildServerAdapter {
  private final TagsStorage myTagsStorage;
  private final TagsMatcher myTagsMatcher;

  public VcsModificationListener(@NotNull TagsStorage tagsStorage, @NotNull TagsMatcher tagsMatcher) {
    myTagsStorage = tagsStorage;
    myTagsMatcher = tagsMatcher;
  }


  @Override
  public void changeAdded(@NotNull VcsModification modification, @NotNull VcsRoot root, @Nullable Collection<SBuildType> buildTypes) {
    List<String> tags = findMatchedTags(modification);
    if (!tags.isEmpty()) {
      setTags(modification, tags);
    }
  }

  private void setTags(@NotNull VcsModification modification, @NotNull List<String> tags) {
    myTagsStorage.setTags(modification, tags);
  }

  @NotNull
  private List<String> findMatchedTags(@NotNull VcsModification modification) {
    return myTagsMatcher.findMatchedTags(modification);
  }
}