package org.jetbrains.buildServer.commitTags.web;

import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.PlaceId;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.SimpleCustomTab;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.buildServer.commitTags.ProjectRules;
import org.jetbrains.buildServer.commitTags.TagsStorage;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ProjectRulesTab extends SimpleCustomTab {
  private final ProjectManager myProjectManager;
  private final ProjectRules myProjectRules;

  public ProjectRulesTab(@NotNull PagePlaces pagePlaces,
                         @NotNull PluginDescriptor pluginDescriptor,
                         @NotNull ProjectManager projectManager,
                         @NotNull ProjectRules projectRules) {
    super(pagePlaces);
    myProjectManager = projectManager;
    myProjectRules = projectRules;
    setPlaceId(PlaceId.EDIT_PROJECT_PAGE_TAB);
    setPluginName(pluginDescriptor.getPluginName());
    setTabTitle("Change Tags");
    setIncludeUrl(pluginDescriptor.getPluginResourcesPath("editRules.jsp"));
    register();
  }

  @Override
  public boolean isAvailable(@NotNull HttpServletRequest request) {
    return getProject(request) != null;
  }

  @Override
  public void fillModel(@NotNull Map<String, Object> model, @NotNull HttpServletRequest request) {
    super.fillModel(model, request);
    SProject project = getProject(request);
    if (project != null) {
      model.put("currentProject", project);
      model.put("rulesBean", new TagRulesBean(myProjectRules.getRules(project)));
    }
  }

  @Nullable
  private SProject getProject(@NotNull HttpServletRequest request) {
    return myProjectManager.findProjectByExternalId(request.getParameter("projectId"));
  }
}
