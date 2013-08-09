package org.jetbrains.buildServer.commitTags;

import jetbrains.buildServer.vcs.SVcsModification;
import jetbrains.buildServer.vcs.VcsFileModification;
import jetbrains.buildServer.vcs.VcsModification;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilePathRegexpRule extends TagRule {
  private final Pattern myPattern;

  public FilePathRegexpRule(@NotNull String regexp, @NotNull String tag) {
    super(tag);
    myPattern = Pattern.compile(regexp);
  }

  @Override
  public boolean matches(@NotNull SVcsModification modification) {
    for (VcsFileModification file: modification.getChanges()) {
      Matcher matcher = myPattern.matcher(file.getFileName());
      if (matcher.find()) {
        return true;
      }
    }
    return false;
  }

  @NotNull
  @Override
  public String getDescription() {
    return myPattern.toString();
  }
}
