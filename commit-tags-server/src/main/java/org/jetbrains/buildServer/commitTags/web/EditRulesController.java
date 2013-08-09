package org.jetbrains.buildServer.commitTags.web;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import jetbrains.buildServer.web.openapi.WebResourcesManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditRulesController extends BaseController {
  private final PluginDescriptor myPluginDescriptor;

  public EditRulesController(@NotNull WebControllerManager webControllerManager, @NotNull PluginDescriptor pluginDescriptor) {
    webControllerManager.registerController("/admin/" + pluginDescriptor.getPluginName() + "/editRules.html", this);
    myPluginDescriptor = pluginDescriptor;
  }

  @Nullable
  @Override
  protected ModelAndView doHandle(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse) throws Exception {
    return new ModelAndView(myPluginDescriptor.getPluginResourcesPath("editRules.jsp"));
  }
}
