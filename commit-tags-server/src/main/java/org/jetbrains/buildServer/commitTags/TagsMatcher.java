package org.jetbrains.buildServer.commitTags;

import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.vcs.SVcsModification;
import jetbrains.buildServer.vcs.VcsModification;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TagsMatcher {
  private final ProjectRules myProjectRules;

  public TagsMatcher(@NotNull ProjectRules projectRules) {
    myProjectRules = projectRules;
  }

  @NotNull
  public List<String> findMatchedTags(@NotNull VcsModification modification) {
    Set<String> tags = new HashSet<String>();

    SVcsModification mod = (SVcsModification) modification;
    for (SProject project: mod.getRelatedProjects()) {
      List<TagRule> rules = myProjectRules.getRules(project);
      for (TagRule r: rules) {
        if (r.isMatched(mod)) {
          tags.add(r.getTag());
        }
      }
    }

    List<String> res = new ArrayList<String>(tags);
    Collections.sort(res);
    return res;
  }
}
