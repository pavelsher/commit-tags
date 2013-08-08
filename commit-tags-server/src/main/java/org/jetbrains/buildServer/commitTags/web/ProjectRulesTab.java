package org.jetbrains.buildServer.commitTags.web;

import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.PlaceId;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.SimpleCustomTab;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ProjectRulesTab extends SimpleCustomTab {
  private final ProjectManager myProjectManager;

  public ProjectRulesTab(@NotNull PagePlaces pagePlaces, @NotNull PluginDescriptor pluginDescriptor, @NotNull ProjectManager projectManager) {
    super(pagePlaces);
    myProjectManager = projectManager;
    setPlaceId(PlaceId.EDIT_PROJECT_PAGE_TAB);
    setPluginName(pluginDescriptor.getPluginName());
    setTabTitle("Change Tags");
    setIncludeUrl("/admin/commitTags/editRules.html");
    register();
  }

  @Override
  public boolean isAvailable(@NotNull HttpServletRequest request) {
    return getProject(request) != null;
  }

  @Override
  public void fillModel(@NotNull Map<String, Object> model, @NotNull HttpServletRequest request) {
    super.fillModel(model, request);
    model.put("currentProject", getProject(request));
  }

  @Nullable
  private SProject getProject(@NotNull HttpServletRequest request) {
    return myProjectManager.findProjectByExternalId(request.getParameter("projectId"));
  }
}
