package org.jetbrains.buildServer.commitTags;

import jetbrains.buildServer.serverSide.SProject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectRules {
  private Map<String, List<TagRule>> myProjectRules = new HashMap<String, List<TagRule>>();

  @NotNull
  public synchronized List<TagRule> getRules(@NotNull SProject project) {
    SProject cur = project;
    List<TagRule> allRules = new ArrayList<TagRule>();
    while (cur != null) {
      List<TagRule> rules = myProjectRules.get(cur.getProjectId());
      if (rules != null) {
        allRules.addAll(rules);
      }

      cur = cur.getParentProject();
    }

    return allRules;
  }

  public synchronized void setRules(@NotNull SProject project, @NotNull List<TagRule> rules) {
    myProjectRules.put(project.getProjectId(), rules);
  }
}
